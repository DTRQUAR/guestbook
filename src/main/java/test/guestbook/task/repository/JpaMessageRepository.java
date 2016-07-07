package test.guestbook.task.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        return em.find(Message.class, message_id);
    }

    @Override
    public Message getLast() {
        return em.createNamedQuery(Message.ALL_SORTED, Message.class)
                .setMaxResults(1)
                .getSingleResult();
    }

    @Override
    public Message create(Message message) {
        em.persist(message);
        return message;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAll() {
        return em.createNamedQuery(Message.ALL_SORTED, Message.class)
                .getResultList();
    }

}
