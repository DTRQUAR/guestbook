package test.guestbook.task.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Qouer on 30.06.2016.
 */
public class Message {
    private String name;

    private String text;

    private LocalDateTime dateTime;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Message() {
    }

    public Message(String name, String text, LocalDateTime dateTime) {
        this.name = name;
        this.text = text;
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getFormattedDate() {
        return getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedTime() {
        return getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
