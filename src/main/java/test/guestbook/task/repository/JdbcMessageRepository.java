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

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */

@Repository
@Transactional
public class JdbcMessageRepository implements MessageRepository {

    private static final RowMapper<Message> ROW_MAPPER =
            (rs, rowNum) -> new Message(rs.getString("name"), rs.getString("text"),
                            rs.getTimestamp("datetime").toLocalDateTime());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcInsert insertMessage;

    public JdbcMessageRepository() {
    }

    @Autowired
    public JdbcMessageRepository(DataSource dataSource) {
        this.insertMessage = new SimpleJdbcInsert(dataSource)
                .withTableName("MESSAGES")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public void create(Message message) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("name", message.getName())
                .addValue("text", message.getText())
                .addValue("datetime", Timestamp.valueOf(message.getDateTime()));
        insertMessage.executeAndReturnKey(map);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> getAllMessages() {
        return jdbcTemplate.query("SELECT * FROM messages ORDER BY datetime DESC", ROW_MAPPER);
    }

}
