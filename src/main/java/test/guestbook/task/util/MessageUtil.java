package test.guestbook.task.util;

import test.guestbook.task.model.Message;
import test.guestbook.task.to.MessageTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageUtil {

    public static List<MessageTo> getMessagesTo(Collection<Message> messages){
        return messages
                .stream()
                .map(m -> new MessageTo(m.getId(), m.getDefaultName(), m.getText(), m.getDateTime()))
                .collect(Collectors.toList());
    }
}
