package test.guestbook.task.repository;

import test.guestbook.task.model.User;

/**
 * Created by Qouer on 01.07.2016.
 */
public interface UserRepository {
    void save(User user);

    User getByEmail(String email);
}
