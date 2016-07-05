package test.guestbook.task.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import test.guestbook.task.AbstractTest;
import test.guestbook.task.MessageRateTestData;
import test.guestbook.task.MessageTestData;
import test.guestbook.task.UserTestData;
import test.guestbook.task.model.MessageRate;

import java.util.Arrays;

/**
 * Created by Qouer on 05.07.2016.
 */

@Transactional
public class JpaMessageRateRepositoryTest extends AbstractTest {

    @Autowired
    private MessageRateRepository messageRateRepository;

    //Тест на создание оценки сообщения пользовталем
    @Test
    public void testCreateMessageRate(){
        MessageRate messageRate = new MessageRate(UserTestData.USER_1, MessageTestData.MESSAGE_2, false);
        messageRateRepository.updateOrCreateMessageRate(messageRate);
        Assert.assertEquals(
                Arrays.asList(
                        MessageRateTestData.MESSAGE_RATE_1,
                        MessageRateTestData.MESSAGE_RATE_2,
                        MessageRateTestData.MESSAGE_RATE_3,
                        messageRate),
                messageRateRepository.getAllMessageRate()
        );
    }

    @Test
    public void testUpdateMessageRate(){
        MessageRate messageRate = new MessageRate(1, UserTestData.USER_1, MessageTestData.MESSAGE_1, false);
        messageRateRepository.updateOrCreateMessageRate(messageRate);
        Assert.assertEquals(
                Arrays.asList(
                        messageRate,
                        MessageRateTestData.MESSAGE_RATE_2,
                        MessageRateTestData.MESSAGE_RATE_3),
                messageRateRepository.getAllMessageRate()
        );
    }

    @Test
    public void testDeleteMessageRate() {
        messageRateRepository.deleteMessageRate(2);
        Assert.assertEquals(
                Arrays.asList(
                        MessageRateTestData.MESSAGE_RATE_1,
                        MessageRateTestData.MESSAGE_RATE_3),
                messageRateRepository.getAllMessageRate()
        );
    }

}
