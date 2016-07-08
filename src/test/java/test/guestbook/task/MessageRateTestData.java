package test.guestbook.task;

import test.guestbook.task.model.MessageRate;

/**
 * Created by Qouer on 05.07.2016.
 */
public class MessageRateTestData {

//    INSERT INTO message_rates (id, rate, message_id, user_id) VALUES
//    (1, TRUE, 1, 1),
//    (2, TRUE, 1, 2),
//    (3, TRUE, 1, 2);

    public static final MessageRate MESSAGE_RATE_1
            = new MessageRate(1, UserTestData.USER_1, MessageTestData.MESSAGE_1, true);
    public static final MessageRate MESSAGE_RATE_2
            = new MessageRate(2, UserTestData.USER_2, MessageTestData.MESSAGE_1, true);
    public static final MessageRate MESSAGE_RATE_3
            = new MessageRate(3, UserTestData.USER_3, MessageTestData.MESSAGE_1, true);

}
