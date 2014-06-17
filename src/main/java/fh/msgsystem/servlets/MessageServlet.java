package fh.msgsystem.servlets;

import fh.msgsystem.entities.MessageDTO;
import fh.msgsystem.services.MessageService;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

@WebServlet(name = "MessageServlet", urlPatterns = {
    MessageServlet.ACTION_LIST,
    MessageServlet.ACTION_REMOVE_MESSAGE,
    MessageServlet.ACTION_EDIT_MESSAGE})
public class MessageServlet extends HttpServlet {
    
    static final String ACTION_LIST = "/List"; 
    static final String ACTION_REMOVE_MESSAGE = "/RemoveMessage";
    static final String ACTION_EDIT_MESSAGE = "/EditMessage";
    
    static final String ATTRIBUTE_MESSAGES = "messages";
    static final String ATTRIBUTE_MESSAGE_FORM = "messageForm";    
    static final String ATTRIBUTE_ERROR = "error";    

    static final String JSP_LIST = "/index.jsp";
    
    @Autowired
    MessageService ms;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
    }
    
    private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*MessageDTO msg = new MessageDTO();
        msg.setAuthor("AUTO");
        msg.setBody("hello there");
        msg.setHeader("hello");
        ms.createMessage(msg);*/
        request.setCharacterEncoding("utf-8");
        request.setAttribute(ATTRIBUTE_MESSAGES, ms.readAllMessages());
        request.getRequestDispatcher(JSP_LIST).forward(request, response);
    }
    
    private void postMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            request.setCharacterEncoding("utf-8");
            MessageForm messageForm = MessageForm.extractFromRequest(request);
            StringBuilder errors = new StringBuilder();
            MessageDTO message = messageForm.validateAndToMessageDTO(errors);
            
            if (message == null) {
                request.setAttribute(ATTRIBUTE_ERROR, errors.toString());
                request.setAttribute(ATTRIBUTE_MESSAGE_FORM, messageForm);
                request.setAttribute(ATTRIBUTE_MESSAGES, ms.readAllMessages());
                request.getRequestDispatcher(JSP_LIST).forward(request, response);
            } 
            else {
                ms.createMessage(message);
                request.setAttribute(ATTRIBUTE_MESSAGES, ms.readAllMessages());
                request.getRequestDispatcher(JSP_LIST).forward(request, response);
            }
        } 

    private void editMessage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        MessageForm messageForm = MessageForm.extractFromRequest(request);
        StringBuilder errors = new StringBuilder();
        MessageDTO msg = messageForm.validateAndToMessageDTO(errors);
        
        if (msg == null) {
                request.setAttribute(ATTRIBUTE_ERROR, errors.toString());
                request.setAttribute(ATTRIBUTE_MESSAGE_FORM, messageForm);
                request.setAttribute(ATTRIBUTE_MESSAGES, ms.readAllMessages());
                request.getRequestDispatcher(JSP_LIST).forward(request, response);
        } 
        else {
            msg.setId(MessageForm.extractIdFromRequest(request));
            ms.updateMessage(msg);
            request.setAttribute(ATTRIBUTE_MESSAGES, ms.readAllMessages());
            request.getRequestDispatcher(JSP_LIST).forward(request, response);
        }
    }
        
    private void removeMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ms.deleteMessage(ms.readMessage(MessageForm.extractIdFromRequest(request)));
        request.setAttribute(ATTRIBUTE_MESSAGES, ms.readAllMessages());
        request.getRequestDispatcher(JSP_LIST).forward(request, response);
    }
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {response.setContentType("text/html;charset=UTF-8");
        switch (request.getServletPath()) {
            case ACTION_LIST:
                list(request, response);
                break;
            case ACTION_REMOVE_MESSAGE:
                removeMessage(request, response);
                break;
            case ACTION_EDIT_MESSAGE:
                editMessage(request, response);
                break;        
            default:
                throw new RuntimeException("Unknown operation: " + request.getServletPath());
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "MessageServlet"; // Short description
    }// </editor-fold>
}
