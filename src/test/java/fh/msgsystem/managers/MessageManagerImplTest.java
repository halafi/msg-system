package fh.msgsystem.managers;

import fh.msgsystem.entities.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Filip
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/resources/TestingApplicationContext.xml"})
public class MessageManagerImplTest {
    
    @Autowired
    private MessageManager mm;
    
    @Autowired
    private EntityManagerFactory emf;
    private EntityManager em;
    
    @Before
    public void setUp() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("DELETE FROM Message m").executeUpdate();
        em.getTransaction().commit();
        em.clear();
        em.close();
    }
    
    @Test
    public void createMessageTest() {
        Message msg = newMessage("admin", "no_title", "body");
        mm.createMessage(msg);
        assertNotNull(msg.getId());
        
        Message msgFromDb = mm.readMessage(msg.getId());
        
        assertEquals(msg,msgFromDb);
        assertDeepEquals(msg,msgFromDb);
    }
    
    @Test
    public void udpateMessageTest() {
        Message msg = newMessage("admin", "no_title", "body");
        mm.createMessage(msg);
        
        Message msg2 = mm.readMessage(msg.getId());
        assertEquals(msg, msg2);
        assertDeepEquals(msg, msg2);
        
        msg2.setAuthor("");
        msg2.setBody("");
        msg2.setHeader("");
        assertNotSame(msg2, msg);
        mm.updateMessage(msg2);
        assertNotSame(msg2, msg);
        
        if (msg2.equals(msg)) {
            fail();
        }
    }
    
    @Test
    public void deleteMessageTest() {
        Message msg = newMessage("admin", "no_title", "body");
        mm.createMessage(msg);
        assertNotNull(mm.readMessage(msg.getId()));
        mm.deleteMessage(msg);
        assertNull(mm.readMessage(msg.getId()));
    }
    
    @Test
    public void readAllMessagesTest() {
        Message msg = newMessage("admin", "no_title", "body");
        Message msg2 = newMessage("user", "title", "bodybody");
        Message msg3 = newMessage("superadmin", "tit", "bodybodybody");
        List<Message> expected = new ArrayList<Message>();
        mm.createMessage(msg);
        expected.add(msg);
        mm.createMessage(msg2);
        expected.add(msg2);
        mm.createMessage(msg3);
        expected.add(msg3);
        List<Message> actual = mm.readAllMessages();
        Collections.sort(expected, idComparator);
        Collections.sort(actual, idComparator);
        assertEquals(expected.size(), actual.size());
        assertDeepEquals(actual, expected);
    }

    private Message newMessage(String author, String header, String body){
    	Message msg = new Message();
    	msg.setAuthor(author);
        msg.setHeader(header);
        msg.setBody(body);
    	return msg;
    }
    
    private void assertDeepEquals(Message m1, Message m2) {
        assertEquals(m1.getId(), m2.getId());
        assertEquals(m1.getAuthor(), m2.getAuthor());
        assertEquals(m1.getBody(), m2.getBody());
        assertEquals(m1.getHeader(), m2.getHeader());
    }
    
    private void assertDeepEquals(List<Message> l1, List<Message> l2) {
        for (int i = 0; i < l1.size(); i++) {
            Message expected = l1.get(i);
            Message actual = l2.get(i);
            assertDeepEquals(expected, actual);
        }
    }
    
    private static final Comparator<Message> idComparator = new Comparator<Message>() {
        @Override
        public int compare(Message m1, Message m2) {
            return m1.getId().compareTo(m2.getId());
        }
    };
}
