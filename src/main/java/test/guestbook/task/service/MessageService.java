package test.guestbook.task.service;

import test.guestbook.task.model.Message;
import test.guestbook.task.model.User;
import test.guestbook.task.to.MessageTo;

import java.util.List;

/**
 * Created by Qouer on 03.07.2016.
 */
public interface MessageService {
    void create(Message message);

    List<MessageTo> getAllMessages();

    void rateMessage(String action, Integer message_id, User user);
}
