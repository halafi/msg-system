package fh.msgsystem.services.impl;

import fh.msgsystem.entities.MessageDTO;
import fh.msgsystem.managers.MessageManager;
import fh.msgsystem.services.MessageService;
import fh.msgsystem.util.DTOConverter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Filip
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageManager mm;
    
    @Override
    public void createMessage(MessageDTO msg) throws DataAccessException {
        mm.createMessage(DTOConverter.getMessage(msg));
    }

    @Override
    public MessageDTO readMessage(Long id) throws DataAccessException {
        return DTOConverter.getDTO(mm.readMessage(id));
    }

    @Override
    public void updateMessage(MessageDTO msg) throws DataAccessException {
        mm.updateMessage(DTOConverter.getMessage(msg));
    }

    @Override
    public void deleteMessage(MessageDTO msg) throws DataAccessException {
        mm.deleteMessage(DTOConverter.getMessage(msg));
    }

    @Override
    public List<MessageDTO> readAllMessages() {
        return DTOConverter.getDTOs(mm.readAllMessages());
    }
}
