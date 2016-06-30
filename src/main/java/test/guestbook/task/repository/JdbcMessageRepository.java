package test.guestbook.task.repository;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import test.guestbook.task.model.Message;

/**
 * Created by Qouer on 30.06.2016.
 */
@Repository
public class JdbcMessageRepository implements MessageRepository{

    private SimpleJdbcInsert insertMessage;

    @Override
    public Message create(Message message) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("text", message.getText())
                .addValue("datetime", message.getDateTime());

        Number id = insertMessage.executeAndReturnKey(map);
        message.setId(id.intValue());
        return message;
    }
}
