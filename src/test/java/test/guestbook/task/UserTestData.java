package test.guestbook.task;

import test.guestbook.task.model.Role;
import test.guestbook.task.model.User;

import java.util.Collections;

/**
 * Created by Qouer on 05.07.2016.
 */
public class UserTestData {

//            (1, 'user1@ya.ru', 'Timur', '111111'),
//            (2, 'user2@ya.ru', 'Dante', '222222'),
//            (3, 'user3@ya.ru', 'Wolverine', '333333');
    public static final User USER_1 = new User(1, "user1@ya.ru", "Timur", "111111Qw", Collections.singleton(Role.ROLE_USER), false);
    public static final User USER_2 = new User(2, "user2@ya.ru", "Dante", "222222Qw", Collections.singleton(Role.ROLE_USER), false);
    public static final User USER_3 = new User(3, "user3@ya.ru", "Wolverine", "333333Qw", Collections.singleton(Role.ROLE_USER), false);
}
