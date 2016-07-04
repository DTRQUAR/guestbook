package test.guestbook.task.repository;

import test.guestbook.task.model.MessageRate;

import java.util.List;

/**
 * Created by Qouer on 04.07.2016.
 */
public interface MessageRateRepository {

    void updateOrCreateMessageRate(MessageRate messageRate);

    void deleteMessageRate(Integer messageRate_id);

    List<MessageRate> getAllMessageRate();

}
