package test.guestbook.task.repository;

import test.guestbook.task.model.MessageRate;

/**
 * Created by Qouer on 04.07.2016.
 */
public interface MessageRateRepository {

    void updateMessageRate(MessageRate messageRate);

    void deleteMessageRate(Integer messageRate_id);

}
