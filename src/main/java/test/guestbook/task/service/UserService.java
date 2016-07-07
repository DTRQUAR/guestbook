package test.guestbook.task.service;

import test.guestbook.task.model.User;

import java.util.List;

/**
 * Created by Qouer on 02.07.2016.
 */
public interface UserService {

    User get(Integer user_id);

    User getByEmail(String email);

    List<User> getAll();

    void save(User u);

}
