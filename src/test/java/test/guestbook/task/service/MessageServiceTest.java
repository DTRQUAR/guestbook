package test.guestbook.task.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.MessageTestData;
import test.guestbook.task.model.Message;
import test.guestbook.task.to.MessageTo;
import test.guestbook.task.util.MessageUtil;
import test.guestbook.task.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Qouer on 07.07.2016.
 */
@Transactional
public class MessageServiceTest extends AbstractTest{

    @Autowired
    protected MessageService messageService;

    //Тест на получение несуществующего сообщения
    @Test(expected = NotFoundException.class)
    public void testNotFoundGet(){
        messageService.get(10);
    }

    //Тест на получение всех сообщений
    @Test
    public void testGetAll(){
        List<MessageTo> allMessageTo = messageService.getAllMessages();
        Assert.assertEquals(Arrays.asList(
                MessageTestData.MESSAGE_TO_3,
                MessageTestData.MESSAGE_TO_2,
                MessageTestData.MESSAGE_TO_1).toString(),
                allMessageTo.toString());
    }

    //Тест на получение сообщения по id
    @Test
    public void testGetMessage(){
        MessageTo messageTo = messageService.get(3);
        Assert.assertEquals(messageTo, MessageTestData.MESSAGE_TO_3);
    }

}
