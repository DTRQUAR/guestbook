package test.guestbook.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.guestbook.task.model.MessageRate;
import test.guestbook.task.repository.MessageRateRepository;
import test.guestbook.task.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Created by Qouer on 07.07.2016.
 */
@Service
public class MessageRateServiceImpl implements MessageRateService{

    @Autowired
    private MessageRateRepository repository;

    @Override
    public MessageRate get(Integer messageRate_id) {
        return ExceptionUtil.check(repository.get(messageRate_id), messageRate_id);
    }

    @Override
    public List<MessageRate> getAll() {
        return repository.getAll();
    }

    @Override
    public MessageRate updateOrCreate(MessageRate messageRate) {
        return repository.updateOrCreate(messageRate);
    }

    @Override
    public void delete(int messageRate_id) {
        ExceptionUtil.check(repository.delete(messageRate_id), messageRate_id);
    }
}
