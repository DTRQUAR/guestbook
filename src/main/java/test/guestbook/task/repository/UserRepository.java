package test.guestbook.task.repository;

import test.guestbook.task.model.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by Qouer on 01.07.2016.
 */
public interface UserRepository {

    User get(Integer user_id);

    User getByEmail(String email);

    List<User> getAll();

    User create(User user);

}
