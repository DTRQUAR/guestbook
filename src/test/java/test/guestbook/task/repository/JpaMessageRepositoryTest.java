package test.guestbook.task.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.MessageTestData;
import test.guestbook.task.model.Message;

import java.util.Arrays;

/**
 * Created by Qouer on 05.07.2016.
 */
@Transactional
public class JpaMessageRepositoryTest extends AbstractTest {

    @Autowired
    private MessageRepository messageRepository;

    //Тест на создание нового сообщения
    @Test
    public void testCreateMessage(){
        Message message = new Message("Anonymous", "Hello!!!");
        messageRepository.create(message);
        Assert.assertEquals(Arrays.asList(
                message, MessageTestData.MESSAGE_3, MessageTestData.MESSAGE_2, MessageTestData.MESSAGE_1).toString(),
                messageRepository.getAll().toString()
        );
    }

    //Тест на получение сообщения по id
    @Test
    public void testGetMessage(){
        Message message = messageRepository.get(2);
        Assert.assertEquals(message, MessageTestData.MESSAGE_2);
    }

    //Тест на получение всех сообщений
    @Test
    public void testGetAllMessages(){
        Assert.assertEquals(
                Arrays.asList(MessageTestData.MESSAGE_3, MessageTestData.MESSAGE_2, MessageTestData.MESSAGE_1).toString(),
                messageRepository.getAll().toString()
        );
    }

    //Тест на получения последнего добавленного сообщения
    @Test
    public void testGetLastMessage(){
        Message message = messageRepository.getLast();
        Assert.assertEquals(message, MessageTestData.MESSAGE_3);
    }
}
