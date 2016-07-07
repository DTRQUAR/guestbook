package test.guestbook.task.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.UserTestData;
import test.guestbook.task.model.Role;
import test.guestbook.task.model.User;
import test.guestbook.task.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.EnumSet;

/**
 * Created by Qouer on 07.07.2016.
 */
@Transactional
public class UserServiceTest extends AbstractTest{

    @Autowired
    protected UserService userService;

    @Test
    public void testCreate(){
        User user = new User("testuser@yandex.ru", "testuser", "testpas", EnumSet.of(Role.ROLE_USER));
        userService.save(user);
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER_1, UserTestData.USER_2, UserTestData.USER_3, user).toString(),
                userService.getAll().toString()
        );
    }

    //Тест на получение несуществующего пользователя
    @Test(expected = NotFoundException.class)
    public void testNotFoundGet(){
        userService.get(4);
    }

    //Тест на получение пользователя с несуществующим email'ом
    @Test(expected = NotFoundException.class)
    public void testNotFoundGetByEmail(){
        userService.getByEmail("tonystark@ironman.com");
    }

    //Тест на получение всех пользователей
    @Test
    public void testGetAllUsers() {
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER_1, UserTestData.USER_2, UserTestData.USER_3).toString(),
                userService.getAll().toString()
        );
    }

}
