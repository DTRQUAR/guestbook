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
public class JpaMessageRepository implements MessageRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Message get(Integer message_id) {
        return em.createNamedQuery(Message.GET, Message.class).setParameter("id", message_id).getSingleResult();
    }

    @Override
    public void create(Message message) {
        em.persist(message);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return em.createNamedQuery(Message.ALL_SORTED, Message.class)
                .getResultList();
    }

}
