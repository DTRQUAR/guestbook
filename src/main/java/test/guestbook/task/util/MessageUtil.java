package test.guestbook.task.util;

import test.guestbook.task.model.Message;
import test.guestbook.task.to.MessageTo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageUtil {
    public static List<MessageTo> getMessageToList(Collection<Message> messages) {
        return messages
                .stream()
                .map(m -> new MessageTo(m.getId(), m.getDefaultName(), m.getText(),
                        m.getDateTime(), m.getUser(), m.getMessageRates()))
                .collect(Collectors.toList());
    }

    public MessageTo getMessageToList(Message message) {
        return new MessageTo(message.getId(), message.getDefaultName(), message.getText(),
                message.getDateTime(), message.getUser(), message.getMessageRates());
    }
    
}
