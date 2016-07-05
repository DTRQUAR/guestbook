package test.guestbook.task.service;

import test.guestbook.task.model.User;

import java.util.List;

/**
 * Created by Qouer on 02.07.2016.
 */
public interface UserService {

    void save(User u);

    List<User> getAll();

}
