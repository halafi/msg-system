package fh.msgsystem.util;

import fh.msgsystem.entities.Message;
import fh.msgsystem.entities.MessageDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class DTOConverter {
    
    public static MessageDTO getDTO(Message msg) {
        if(msg == null) {
            return null;
        }
        MessageDTO toReturn = new MessageDTO();
        toReturn.setAuthor(msg.getAuthor());
        toReturn.setBody(msg.getBody());
        toReturn.setHeader(msg.getHeader());
        toReturn.setId(msg.getId());
        return toReturn;
    }
    
    public static List<MessageDTO> getDTOs(List<Message> msgs) {
        if(msgs == null) {
            return null;
        }
        List<MessageDTO> toReturn = new ArrayList(msgs.size());
        for(Message m: msgs) {
            toReturn.add(getDTO(m));
        }
        return toReturn;
    }
    
    public static Message getMessage(MessageDTO msg) {
        if(msg == null) {
            return null;
        }
        Message toReturn = new Message();
        toReturn.setAuthor(msg.getAuthor());
        toReturn.setBody(msg.getBody());
        toReturn.setHeader(msg.getHeader());
        toReturn.setId(msg.getId());
        return toReturn;
    }
    
    public static List<Message> getMessages(List<MessageDTO> msgs) {
        if(msgs == null) {
            return null;
        }
        List<Message> toReturn = new ArrayList(msgs.size());
        for(MessageDTO m: msgs) {
            toReturn.add(getMessage(m));
        }
        return toReturn;
    }
    

}
