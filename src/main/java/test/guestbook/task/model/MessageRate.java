package test.guestbook.task.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Qouer on 03.07.2016.
 */
@NamedQueries({
        @NamedQuery(name = MessageRate.GET_BY_MESSAGE_AND_USER,
                query = "SELECT r FROM MessageRate r " +
                        "LEFT JOIN Message m " +
                        "LEFT JOIN User u " +
                        "WHERE m.id=:message_id AND u.id=:user_id"),
        @NamedQuery(name = MessageRate.DELETE_BY_ID,
                query = "DELETE FROM MessageRate r WHERE r.id=:id"),
        @NamedQuery(name = MessageRate.GET_ALL,
                query = "SELECT r FROM MessageRate r ORDER BY r.id"),
})
@Entity
@Table(name = "message_rates")
public class MessageRate {

    public static final String GET_ALL = "MessageRate.GetAll";
    public static final String GET_BY_MESSAGE_AND_USER = "MessageRate.GetByMessageAndUser";
    public static final String DELETE_BY_ID = "MessageRate.DeleteById";

    @Id
    @SequenceGenerator(name = "rate_seq", sequenceName = "rate_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rate_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @Column(name = "rate")
    private boolean rate;

    public MessageRate() {
    }

    public MessageRate(User user, Message message, boolean rate) {
        this.user = user;
        this.message = message;
        this.rate = rate;
    }

    public MessageRate(Integer id, User user, Message message, boolean rate) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean getRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "MessageRate{" +
                "id=" + id +
                ", userId=" + user.getId() +
                ", messageId=" + message.getId() +
                ", rate=" + rate +
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

        MessageRate that = (MessageRate) o;
        return Objects.equals(this.id, that.id)
                && Objects.equals(this.user.getId(), that.user.getId())
                && Objects.equals(this.message.getId(), that.message.getId())
                && Objects.equals(this.rate, that.rate);
    }

}
