package test.guestbook.task.service;

import test.guestbook.task.model.MessageRate;

import java.util.List;

/**
 * Created by Qouer on 07.07.2016.
 */
public interface MessageRateService {

    MessageRate get(Integer messageRate_id);

    List<MessageRate> getAll();

    MessageRate updateOrCreate(MessageRate messageRate);

    void delete(int messageRate_id);

}
