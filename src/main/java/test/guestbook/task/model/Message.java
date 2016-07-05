package test.guestbook.task.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Qouer on 30.06.2016.
 */
@NamedQueries({
        @NamedQuery(name = Message.GET, query = "SELECT m FROM Message m WHERE m.id=:id"),
        @NamedQuery(name = Message.ALL_SORTED, query = "SELECT m FROM Message m " +
                "LEFT JOIN m.user ORDER BY m.dateTime DESC, m.id DESC"),
//        @NamedQuery(name = Message.GET_LAST, query = "SELECT m FROM Message m ORDER by m.id DESC LIMIT 1")

//        @NamedQuery(name = Message.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
//        @NamedQuery(name = Message.GET_BETWEEN,
//                query = "SELECT m from UserMeal m WHERE m.user.id=:userId " +
//                        " AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC"),

})
@Entity
@Table(name = "messages")
//@Access(AccessType.FIELD)
public class Message {
    public static final String GET = "Messages.get";
//    public static final String GET_LAST = "Messages.getLast";
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
//    @OrderBy("dateTime DESC")
//    @JsonIgnore
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

    public Message(String text, LocalDateTime dateTime) {
        this.text = text;
        this.dateTime = dateTime;
    }

    public Message(String defaultName, String text, LocalDateTime dateTime) {
        this.defaultName = defaultName;
        this.text = text;
        this.dateTime = dateTime;
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
}
