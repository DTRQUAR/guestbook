package test.guestbook.task.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.LoggedUser;
import test.guestbook.task.model.Message;
import test.guestbook.task.model.MessageRate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */

@Repository
@Transactional
public class JdbcMessageRepository implements MessageRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Message get(Integer message_id) {
        return em.createNamedQuery(Message.GET, Message.class).setParameter("id", message_id).getSingleResult();
    }

    @Override
    public void create(Message message) {
//        message.setId(LoggedUser.safeGet().getAuthUser().getId());
        em.persist(message);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return em.createNamedQuery(Message.ALL_SORTED, Message.class)
                .getResultList();
    }

    @Override
    public MessageRate getMessageRate(Integer message_id, Integer user_id) {
//        return em.createNamedQuery(MessageRate.GET_BY_MESSAGE_AND_USER, MessageRate.class)
//                .setParameter(1, message_id).setParameter(2, user_id).getSingleResult();
        return null;
    }

    @Override
    public void updateMessageRate(MessageRate messageRate) {
        if (messageRate.getId() == null){
            em.persist(messageRate);
        }else{
            em.merge(messageRate);
        }
    }

    @Override
    public void deleteMessageRate(Integer messageRate_id) {
        em.createNamedQuery(MessageRate.DELETE_BY_ID)
                .setParameter("id", messageRate_id).executeUpdate();

//        em.createQuery("DELETE FROM MessageRate r where r.id=:id").setParameter("id", messageRate_id).executeUpdate();
        System.err.println("DELETED");
//        em.remove(new MessageRate(rate.getId(), rate.getUser(), rate.getMessage(), rate.getRate()));
    }


}
