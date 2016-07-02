package test.guestbook.task;

import test.guestbook.task.model.User;

/**
 * Created by Qouer on 02.07.2016.
 */
public class LoggedUser extends org.springframework.security.core.userdetails.User{
    static final long serialVersionUID = 1L;

    public LoggedUser(User user) {
        super(user.getEmail(), user.getPassword(),  true, true, true, true, user.getRoles());
    }
}
