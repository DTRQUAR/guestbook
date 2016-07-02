package test.guestbook.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import test.guestbook.task.model.User;
import test.guestbook.task.repository.UserRepository;

import java.util.List;

/**
 * Created by Qouer on 02.07.2016.
 */
public interface UserService {
    List<User> getAll();

}
