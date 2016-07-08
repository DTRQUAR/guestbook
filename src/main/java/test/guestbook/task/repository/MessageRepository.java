package test.guestbook.task.repository;

import test.guestbook.task.model.Message;

import java.util.Collection;

/**
 * Created by Qouer on 30.06.2016.
 */
public interface MessageRepository {

    Message get(Integer message_id);

    Message getLast();

    Collection<Message> getAll();

    Message create(Message message);

}
