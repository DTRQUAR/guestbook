package test.guestbook.task.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.UserTestData;
import test.guestbook.task.model.Role;
import test.guestbook.task.model.User;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by Qouer on 05.07.2016.
 */
@Transactional
public class JpaUserRepositoryTest extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    //Тест на создание нового пользователя
    @Test
    public void testCreate() {
        User user = new User("testuser@yandex.ru", "testuser", "testpas", EnumSet.of(Role.ROLE_USER));
        userRepository.create(user);
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER_1, UserTestData.USER_2, UserTestData.USER_3, user).toString(),
                userRepository.getAll().toString()
        );
    }

    //Тест на доставание из базы всех пользователей
    @Test
    public void testGetAllUsers() {
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER_1, UserTestData.USER_2, UserTestData.USER_3).toString(),
                userRepository.getAll().toString()
        );
    }

    //Тест на получение пользователя по id
    @Test
    public void testGetUser() {
        User user = userRepository.get(1);
        Assert.assertEquals(user, UserTestData.USER_1);
    }

    //Тест на получение пользователя по email
    @Test
    public void textGetByEmail(){
        User user = userRepository.getByEmail("user2@ya.ru");
        Assert.assertEquals(user, UserTestData.USER_2);
    }
}