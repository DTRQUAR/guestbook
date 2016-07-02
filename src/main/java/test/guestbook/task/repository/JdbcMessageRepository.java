package test.guestbook.task.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.model.Message;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PostPersist;
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

    @PostPersist
    public void initDB(){
        Message dgfadswg = new Message("dgfadswg", "dgsd@ya.ru", LocalDateTime.now());
        em.persist(dgfadswg);
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
