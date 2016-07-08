package test.guestbook.task.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by Qouer on 30.06.2016.
 */
@NamedQueries({
        @NamedQuery(name = Message.GET, query = "SELECT m FROM Message m WHERE m.id=:id"),
        @NamedQuery(name = Message.ALL_SORTED, query = "SELECT m FROM Message m " +
                "LEFT JOIN m.user ORDER BY m.dateTime DESC, m.id DESC"),
})
@Entity
@Table(name = "messages")
public class Message {
    public static final String GET = "Messages.get";
    public static final String ALL_SORTED = "Messages.getAll";

    @Id
    @SequenceGenerator(name = "message_seq", sequenceName = "message_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "message_seq")
    @Column(name = "id")
//    @Column(name = "id", columnDefinition = "default nextval('message_seq')")
    private Integer id;

    @Column(name = "default_name", nullable = true)
    @Type(type = "text")
    @Length(min = 1, max = 800)
    private String defaultName;

    @Column(name = "text", nullable = false)
    @Type(type = "text")
    @NotEmpty
    @Length(min = 1, max = 2500)
    private String text;

    @Column(name = "datetime", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER, mappedBy = "message")
    protected List<MessageRate> messageRates;
//
    public List<MessageRate> getMessageRates() {
        return messageRates;
    }

    public void setMessageRates(List<MessageRate> messageRates) {
        this.messageRates = messageRates;
    }

    public Message() {
    }

    public Message(String defaultName,String text, LocalDateTime dateTime) {
        this.defaultName = defaultName;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Message(String defaultName, String text) {
        this.defaultName = defaultName;
        this.text = text;
    }

    public Message(Integer id, String defaultName, String text, LocalDateTime dateTime, User user) {
        this.id = id;
        this.defaultName = defaultName;
        this.text = text;
        this.dateTime = dateTime;
        this.user = user;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Message{" +
                "dateTime=" + dateTime +
                ", text='" + text + '\'' +
                ", defaultName='" + defaultName + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Message that = (Message) o;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.dateTime, that.dateTime)
                && Objects.equals(this.text, that.text)
                && Objects.equals(this.defaultName, that.defaultName)
                && Objects.equals(this.user.getId(), that.user.getId())
                && Objects.equals(this.user.getEmail(), that.user.getEmail());
    }
}
