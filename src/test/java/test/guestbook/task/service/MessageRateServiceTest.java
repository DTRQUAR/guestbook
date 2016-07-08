package test.guestbook.task.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.MessageRateTestData;
import test.guestbook.task.MessageTestData;
import test.guestbook.task.UserTestData;
import test.guestbook.task.model.MessageRate;
import test.guestbook.task.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by Qouer on 07.07.2016.
 */
@Transactional
public class MessageRateServiceTest extends AbstractTest{

    @Autowired
    protected MessageRateService service;

    //Тест на получение несуществующего рейтинга
    @Test(expected = NotFoundException.class)
    public void testNotFoundGet(){
        service.get(10);
    }

    //Тест на получение всех рейтингов
    @Test
    public void testGetAll(){
        Collection<MessageRate> MessageRates = service.getAll();
        Assert.assertEquals(Arrays.asList(
                MessageRateTestData.MESSAGE_RATE_1,
                MessageRateTestData.MESSAGE_RATE_2,
                MessageRateTestData.MESSAGE_RATE_3).toString(),
                MessageRates.toString()
        );
    }

    //Тест на создание новой оценки
    @Test
    public void testCreateMessageRate(){
        MessageRate messageRate = new MessageRate(UserTestData.USER_2, MessageTestData.MESSAGE_2, true);
        service.updateOrCreate(messageRate);
        messageRate.setId(4);
        Assert.assertEquals(Arrays.asList(
                MessageRateTestData.MESSAGE_RATE_1,
                MessageRateTestData.MESSAGE_RATE_2,
                MessageRateTestData.MESSAGE_RATE_3,
                messageRate).toString(),
                service.getAll().toString()
        );
    }

    //Тест на изменение существующей оценки
    @Test
    public void testUpdateMessageRate(){
        List<MessageRate> messageRates = service.getAll();
        MessageRate messageRate = new MessageRate(1, UserTestData.USER_1, MessageTestData.MESSAGE_1, false);
        service.updateOrCreate(messageRate);
        Assert.assertEquals(Arrays.asList(
                messageRate,
                MessageRateTestData.MESSAGE_RATE_2,
                MessageRateTestData.MESSAGE_RATE_3).toString(),
                messageRates.toString()
        );
    }

    //Тест на удалние существующей оценки
    @Test
    public void testDeleteMessageRate(){
        service.delete(3);
        Assert.assertEquals(Arrays.asList(
                MessageRateTestData.MESSAGE_RATE_1,
                MessageRateTestData.MESSAGE_RATE_2).toString(),
                service.getAll().toString()
        );
    }

}
