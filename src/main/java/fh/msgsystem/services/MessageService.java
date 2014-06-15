package fh.msgsystem.services;

import fh.msgsystem.entities.MessageDTO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 *
 * @author Filip
 */
public interface MessageService {
    void createMessage(MessageDTO msg) throws DataAccessException;
    MessageDTO readMessage(Long id) throws DataAccessException;
    void updateMessage(MessageDTO msg) throws DataAccessException;
    void deleteMessage(MessageDTO msg) throws DataAccessException;
    List<MessageDTO> readAllMessages();
}
