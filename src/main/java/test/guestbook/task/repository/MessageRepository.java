package test.guestbook.task.repository;

import test.guestbook.task.model.Message;

import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */
public interface MessageRepository {
    void create(Message message);

    List<Message> getAllMessages();
}
