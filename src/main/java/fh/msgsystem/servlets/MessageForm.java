package fh.msgsystem.servlets;

import fh.msgsystem.entities.MessageDTO;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Filip
 */
public class MessageForm {
    private Long id;
    private String author;
    private String header;
    private String body;

    public static MessageForm extractFromRequest(HttpServletRequest request) {
        MessageForm messageForm = new MessageForm();
        String idString = request.getParameter("id");  
        messageForm.setId(idString == null?null:Long.parseLong(idString));
        messageForm.setAuthor(request.getParameter("author"));
        messageForm.setHeader(request.getParameter("header"));
        messageForm.setBody(request.getParameter("body"));
        return messageForm;
    }
    
    public MessageDTO validateAndToMessageDTO(StringBuilder errors) {
        MessageDTO msg = new MessageDTO();
        if (getAuthor() == null || getAuthor().isEmpty()) {
            errors.append("Field '").append("Author").append("' is not filled").append(". <br />");
            msg.setAuthor(null);            
        } else {
            msg.setAuthor(getAuthor());                                    
        }
        if (getHeader() == null || getHeader().isEmpty()) {
            errors.append("Field '").append("Header").append("' is not filled").append(". <br />");
            msg.setHeader(null);
        } else {
            msg.setHeader(getHeader());
        }
        if (getBody()== null || getBody().isEmpty()) {
            errors.append("Field '").append("Body").append("' is not filled").append(". <br />");
            msg.setBody(null);
        } else {
            msg.setBody(getBody());                                    
        }
        if (errors.length() > 0) {
            return null;
        } else {
            return msg;
        }
    }

    public static Long extractIdFromRequest(HttpServletRequest request) {
        if (request.getParameter("id") == null || request.getParameter("id").isEmpty()) {
            return null;          
        } else {
            return(Long.parseLong(request.getParameter("id")));                                   
        }
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
    
}
