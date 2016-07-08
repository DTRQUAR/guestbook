package test.guestbook.task.model;

import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Qouer on 01.07.2016.
 */
@NamedQueries({
        @NamedQuery(name = User.GET, query = "SELECT u FROM User u WHERE u.id=:id"),
        @NamedQuery(name = User.BY_EMAIL, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email=?1"),
        @NamedQuery(name = User.ALL_SORTED, query = "SELECT u FROM User u LEFT JOIN FETCH u.roles ORDER BY u.id"),
        @NamedQuery(name = User.GET_FOR_MAIL, query = "SELECT u FROM User u WHERE u.emailNotif=TRUE ORDER BY u.id")
})
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email", name = "users_unique_email_idx")})
public class User {
    public static final String GET = "User.get";
    public static final String ALL_SORTED = "User.getAllSorted";
    public static final String BY_EMAIL = "User.getByEmail";
    public static final String GET_FOR_MAIL = "User.getForMail";

    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//    @Column(name = "id", columnDefinition = "default nextval('user_seq')")
    protected Integer id;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotEmpty
    private String email;

    @Column(name = "name", nullable = false)
    @Type(type = "text")
    @NotEmpty
    @Length(min = 1, max = 800)
    private String name;

    @Column(name = "password", nullable = false)
    @NotEmpty
    @Length(min = 5)
    private String password;

    @Column(name = "email_notif", nullable = false)
    private boolean emailNotif;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @ElementCollection(fetch = FetchType.EAGER)
    protected Set<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    @OrderBy("dateTime DESC")
    protected List<Message> messages;

    public User() {
    }

    public User(String email, String name, String password, Set<Role> roles, boolean emailNotif) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.emailNotif = emailNotif;
    }

    public User(Integer id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public User(Integer id, String email, String name, String password, Set<Role> roles) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public User(Integer id, String email, String name, String password, Set<Role> roles, boolean emailNotif) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.emailNotif = emailNotif;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public boolean isEmailNotif() {
        return emailNotif;
    }

    public void setEmailNotif(boolean emailNotif) {
        this.emailNotif = emailNotif;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "User{" +
                "emailNotif=" + emailNotif +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
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

        User that = (User) o;
        return Objects.equals(this.password, that.password)
                && Objects.equals(this.id, that.id)
                && Objects.equals(this.name, that.name)
                && Objects.equals(this.email, that.email)
                && Objects.equals(this.roles, that.roles)
                && Objects.equals(this.emailNotif, that.emailNotif);
    }
}
