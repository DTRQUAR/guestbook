package test.guestbook.task.repository;

import test.guestbook.task.model.MessageRate;

import java.util.List;

/**
 * Created by Qouer on 05.07.2016.
 */
public interface MessageRateRepository {

    MessageRate get(Integer messageRate_id);

    List<MessageRate> getAll();

    MessageRate updateOrCreate(MessageRate messageRate);

    boolean delete(Integer messageRate_id);

}
