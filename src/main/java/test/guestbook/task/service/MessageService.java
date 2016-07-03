package test.guestbook.task.service;

import test.guestbook.task.model.Message;

import java.util.List;

/**
 * Created by Qouer on 03.07.2016.
 */
public interface MessageService {
    void create(Message message);

    List<Message> getAllMessages();

    void rateMessage(String action, Integer message_id, Integer user_id);
}
