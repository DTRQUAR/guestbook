package test.guestbook.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import test.guestbook.task.LoggedUser;
import test.guestbook.task.model.User;
import test.guestbook.task.repository.UserRepository;
import test.guestbook.task.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Created by Qouer on 02.07.2016.
 */
@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public User get(Integer user_id) {
        return ExceptionUtil.check(repository.get(user_id), user_id);
    }

    @Override
    public User getByEmail(String email) {
        return ExceptionUtil.check(repository.getByEmail(email), "email=" + email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public void save(User u) {
        repository.create(u);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User u = repository.getByEmail(email.toLowerCase());
        if (u == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new LoggedUser(u);
    }
}
