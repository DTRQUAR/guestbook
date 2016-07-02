package test.guestbook.task;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import test.guestbook.task.model.User;

import static java.util.Objects.requireNonNull;

/**
 * Created by Qouer on 02.07.2016.
 */
public class LoggedUser extends org.springframework.security.core.userdetails.User{
    static final long serialVersionUID = 1L;

    private User authUser;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(),  true, true, true, true, user.getRoles());
        this.authUser = user;
    }

    public static LoggedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object user = auth.getPrincipal();
        return (user instanceof LoggedUser) ? (LoggedUser) user : null;
    }

    public static LoggedUser get() {
        LoggedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public User getAuthUser() {
        return authUser;
    }
}
