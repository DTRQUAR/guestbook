package test.guestbook.task.repository;

import test.guestbook.task.model.Message;
import test.guestbook.task.model.MessageRate;

import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */
public interface MessageRepository {
    Message get (Integer message_id);

    Message getLast();

    Message create(Message message);

    List<Message> getAllMessages();

    MessageRate getMessageRate(Integer message_id, Integer user_id);

    void updateMessageRate(MessageRate messageRate);

    void deleteMessageRate(Integer messageRate_id);
}
