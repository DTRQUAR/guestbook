package test.guestbook.task.repository;

import test.guestbook.task.model.User;

import java.util.List;

/**
 * Created by Qouer on 01.07.2016.
 */
public interface UserRepository {

    User create(User user);

    User getByEmail(String email);

    List<User> getAll();

    User get(Integer user_id);

}
