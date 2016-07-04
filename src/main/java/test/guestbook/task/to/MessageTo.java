package test.guestbook.task.to;

import test.guestbook.task.model.MessageRate;
import test.guestbook.task.model.User;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageTo {

    private Integer id;

    private String defaultName;

    private String text;

    private LocalDateTime dateTime;

    private User user;

    protected List<MessageRate> messageRates;

    public MessageTo(Integer id, String defaultName, String text, LocalDateTime dateTime, User user, List<MessageRate> messageRates) {
        this.id = id;
        this.defaultName = defaultName;
        this.text = text;
        this.dateTime = dateTime;
        this.user = user;
        this.messageRates = messageRates;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<MessageRate> getMessageRates() {
        return messageRates;
    }

    public void setMessageRates(List<MessageRate> messageRates) {
        this.messageRates = messageRates;
    }

    public String getFormattedDate() {
        return getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedTime() {
        return getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public Integer getPlus(){
        return getMessageRates()
                .stream()
                .filter(r -> r.getRate() == true)
                .collect(Collectors.toList()).size();
    }

    public Integer getMinus(){
        return getMessageRates()
                .stream()
                .filter(r -> r.getRate() == false)
                .collect(Collectors.toList()).size();
    }

    public String getRateFromAuthUser(User user){
        List<MessageRate> messageRates = getMessageRates()
                .stream()
                .filter(r -> user.getId() == r.getUser().getId())
                .collect(Collectors.toList());
        return messageRates.size() != 0 ?
                messageRates.get(0).getRate() == true ? "1" : "2"
                : "0";
    }
}
