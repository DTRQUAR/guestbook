package test.guestbook.task.util;

import test.guestbook.task.model.Message;
import test.guestbook.task.model.MessageRate;
import test.guestbook.task.model.User;
import test.guestbook.task.to.MessageTo;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageUtil {

    public static MessageTo getMessageTo(Message message, User authUser) {
        return new MessageTo(message.getId(), message.getDefaultName(), message.getText(),
                message.getUser() == null ? null : message.getUser().getName(),
                getPlusCount(message), getMinusCount(message),
                getRateFromAuthUser(message, authUser),
                getFormattedDate(message), getFormattedTime(message));
    }

    public static List<MessageTo> getMessagesToList(Collection<Message> messages, User authUser){
        return messages
                .stream()
                .map(m -> new MessageTo(
                        m.getId(), m.getDefaultName(),
                        m.getText(), m.getUser() == null ? null : m.getUser().getName(),
                        getPlusCount(m), getMinusCount(m),
                        getRateFromAuthUser(m, authUser),
                        getFormattedDate(m), getFormattedTime(m)
                ))
                .collect(Collectors.toList());
    }

    public static Integer getPlusCount(Message message) {
        return message.getMessageRates() != null ?
                message.getMessageRates()
                .stream()
                .filter(r -> r.getRate() == true)
                .collect(Collectors.toList()).size()
                : 0;
    }

    public static Integer getMinusCount(Message message) {
        return message.getMessageRates() != null ?
                message.getMessageRates()
                .stream()
                .filter(r -> r.getRate() == false)
                .collect(Collectors.toList()).size()
                : 0;
    }

    public static String getRateFromAuthUser(Message message, User authUser){
        if (authUser == null) {
            return "-1";
        } else{
            List<MessageRate> messageRates = message.getMessageRates()
                    .stream()
                    .filter(r -> authUser.getId() == r.getUser().getId())
                    .collect(Collectors.toList());
            return messageRates.size() != 0 ?
                    messageRates.get(0).getRate() == true ? "1" : "2"
                    : "0";
        }
    }

    public static String getFormattedDate(Message message) {
        return message.getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public static String getFormattedTime(Message message) {
        return message.getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
