package fh.msgsystem.managers;

import fh.msgsystem.entities.Message;
import fh.msgsystem.exceptions.NonExistingEntityException;
import java.util.List;

public interface MessageManager {
    void createMessage(Message msg);
    Message readMessage(Long id);
    void updateMessage(Message msg) throws NonExistingEntityException;
    void deleteMessage(Message msg) throws NonExistingEntityException;
    List<Message> readAllMessages();
}
