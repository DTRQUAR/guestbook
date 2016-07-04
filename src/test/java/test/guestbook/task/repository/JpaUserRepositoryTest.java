package test.guestbook.task.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import test.guestbook.task.UserTestData;
import test.guestbook.task.model.Role;
import test.guestbook.task.model.User;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by Qouer on 04.07.2016.
 */
public class JpaUserRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreate(){
        User user = new User("testuser@yandex.ru", "testuser", "testpas", EnumSet.of(Role.ROLE_USER));
        userRepository.save(user);
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER1, UserTestData.USER2, UserTestData.USER3, user).toString(),
                userRepository.getAll().toString());
}
}
