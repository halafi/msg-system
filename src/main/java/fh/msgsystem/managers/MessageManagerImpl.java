package fh.msgsystem.managers;

import fh.msgsystem.entities.Message;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Filip
 */
@Repository
public class MessageManagerImpl implements MessageManager {
    static final Logger LOG = LoggerFactory.getLogger(MessageManagerImpl.class);
    
    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void createMessage(Message msg) {
        if(msg == null) {
            throw new IllegalArgumentException("message is null");
        }
        em.persist(msg);
        LOG.info("message id="+msg.getId()+"persisted");
    }
    
    @Transactional
    @Override
    public Message readMessage(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("id is null");
        }
        Message msg = em.find(Message.class, id);
        return msg;
    }

    @Transactional
    @Override
    public void deleteMessage(Message msg) throws NonExistingEntityException {
        if(msg == null) {
            throw new IllegalArgumentException("message is null");
        }
        Message msgFromDb = em.find(Message.class, msg.getId());
        if (msgFromDb == null) {
            LOG.error("tried to remove msg that does not exist");
            throw new NonExistingEntityException("tried to remove msg that does not exist");
        } else {
            em.remove(msgFromDb);
            LOG.info("message id="+msg.getId()+"removed");
        }
    }

    @Transactional
    @Override
    public void updateMessage(Message msg) throws NonExistingEntityException {
        if(msg == null) {
            throw new IllegalArgumentException("message is null");
        }
        Message msgFromDb = em.find(Message.class, msg.getId());
        if (msgFromDb == null) {
            LOG.error("tried to update msg that does not exist");
            throw new NonExistingEntityException("tried to update msg that does not exist");
        } else {
            em.merge(msg);
            LOG.info("message id="+msg.getId()+"updated");
        }
    }

    @Transactional
    @Override
    public List<Message> readAllMessages() {
        TypedQuery<Message> query = em.createQuery(
                "SELECT m FROM Message m", Message.class);
        List<Message> l = query.getResultList();
        return l;
    }
    
}
