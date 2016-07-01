package test.guestbook.task.model;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Qouer on 30.06.2016.
 */
@NamedQueries({
        @NamedQuery(name = Message.GET, query = "SELECT m FROM Message m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Message.ALL_SORTED, query = "SELECT m FROM Message m " +
                "JOIN m.user ORDER BY m.dateTime DESC"),
//        @NamedQuery(name = Message.DELETE, query = "DELETE FROM UserMeal m WHERE m.id=:id AND m.user.id=:userId"),
//        @NamedQuery(name = Message.GET_BETWEEN,
//                query = "SELECT m from UserMeal m WHERE m.user.id=:userId " +
//                        " AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC"),

})
@Entity
@Table(name = "messages")
@Access(AccessType.FIELD)
public class Message {
    public static final String GET = "Messages.get";
    public static final String ALL_SORTED = "Messages.getAll";
//    public static final String DELETE = "UserMeal.delete";
//    public static final String GET_BETWEEN = "UserMeal.getBetween";

    @Id
    @SequenceGenerator(name = "messages_seq", sequenceName = "messages_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "messages_seq")
    protected Integer id;

    @Column(name = "text", nullable = false)
    @NotEmpty
    @Length(min = 1)
    private String text;

    @Column(name = "datetime", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Message() {
    }

    public Message(String text, LocalDateTime dateTime) {
        this.text = text;
        this.dateTime = dateTime;
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

    public String getFormattedDate() {
        return getDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public String getFormattedTime() {
        return getDateTime().toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
