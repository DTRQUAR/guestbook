package test.guestbook.task.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
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
    private UserRepository repository;

    //Тест на создание нового пользователя
    @Test
    public void testCreate() {
        User user = new User("testuser@yandex.ru", "testuser", "testpas", EnumSet.of(Role.ROLE_USER), false);
        repository.createOrUpdate(user);
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER_1, UserTestData.USER_2, UserTestData.USER_3, user).toString(),
                repository.getAll().toString()
        );
    }

    //Тест на создание пользователя с уже существующим email'ом
    @Transactional(propagation= Propagation.NEVER)
    @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        repository.createOrUpdate(new User("user1@ya.ru", "Duplicate", "newPass", EnumSet.of(Role.ROLE_USER), false));
    }

    //Тест на получение всех пользователей
    @Test
    public void testGetAllUsers() {
        Assert.assertEquals(
                Arrays.asList(UserTestData.USER_1, UserTestData.USER_2, UserTestData.USER_3).toString(),
                repository.getAll().toString()
        );
    }

    //Тест на получение пользователя по id
    @Test
    public void testGetUser() {
        User user = repository.get(1);
        Assert.assertEquals(user, UserTestData.USER_1);
    }

    //Тест на получение пользователя по email
    @Test
    public void textGetByEmail(){
        User user = repository.getByEmail("user2@ya.ru");
        Assert.assertEquals(user, UserTestData.USER_2);
    }

    //тест на изменение существующего пользователя
    @Test
    public void testUpdateUser(){
        User newUser = new User(1, "newmail@ya.ru", "newName", "111111", EnumSet.of(Role.ROLE_USER), false);
        repository.createOrUpdate(newUser);
        User user = repository.get(1);
        Assert.assertEquals("newName", user.getName());
    }
}