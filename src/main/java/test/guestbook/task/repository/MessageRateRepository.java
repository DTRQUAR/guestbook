package test.guestbook.task.repository;

import test.guestbook.task.model.MessageRate;

import java.util.List;

/**
 * Created by Qouer on 05.07.2016.
 */
public interface MessageRateRepository {

    void updateOrCreate(MessageRate messageRate);

    void delete(Integer messageRate_id);

    List<MessageRate> getAll();

}
