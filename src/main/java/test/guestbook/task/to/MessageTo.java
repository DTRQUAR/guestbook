package test.guestbook.task.to;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageTo {

    private Integer id;

    private String defaultName;

    private String text;

    private LocalDateTime dateTime;

    public MessageTo(@JsonProperty("id") Integer id,
                     @JsonProperty("defaultName") String defaultName,
                     @JsonProperty("text") String text,
                     @JsonProperty("dateTime") LocalDateTime dateTime) {
        this.id = id;
        this.defaultName = defaultName;
        this.text = text;
        this.dateTime = dateTime;
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
}
