package fh.msgsystem.services;

import fh.msgsystem.entities.MessageDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *
 * @author Filip
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/TestingApplicationContext.xml"})
public class MessageServiceImplTest {
    
    @Autowired
    MessageService ms;
    
    @Test (expected=DataAccessException.class)
    public void createMessageTest() {
        ms.createMessage(null);
    }
    
    @Test (expected=DataAccessException.class)
    public void readMessageTest() {
        MessageDTO msg = ms.readMessage(null);
    }
    
    @Test (expected=DataAccessException.class)
    public void updateMessageTest() {
        ms.updateMessage(null);
    }
    
    @Test (expected=DataAccessException.class)
    public void deleteMessageTest() {
        ms.deleteMessage(null);
    }

}
