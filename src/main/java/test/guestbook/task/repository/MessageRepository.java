package test.guestbook.task.repository;

import test.guestbook.task.model.Message;

/**
 * Created by Qouer on 30.06.2016.
 */
public interface MessageRepository {
    Message create(Message message);
}
