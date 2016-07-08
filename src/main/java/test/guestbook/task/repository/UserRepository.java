package test.guestbook.task.repository;

import test.guestbook.task.model.User;

import java.util.List;

/**
 * Created by Qouer on 01.07.2016.
 */
public interface UserRepository {

    User get(Integer user_id);

    User getByEmail(String email);

    List<User> getForMail();

    List<User> getAll();

    User createOrUpdate(User user);

}
