package test.guestbook.task.repository;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.model.Message;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
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
