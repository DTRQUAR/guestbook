package test.guestbook.task.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.guestbook.task.model.Message;
import test.guestbook.task.model.MessageRate;
import test.guestbook.task.model.User;
import test.guestbook.task.repository.MessageRateRepository;
import test.guestbook.task.repository.MessageRepository;
import test.guestbook.task.to.MessageTo;
import test.guestbook.task.util.MessageUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Qouer on 03.07.2016.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MessageRateRepository messageRateRepository;

    @Override
    public void create(Message message) {
        messageRepository.create(message);
    }

    @Override
    public List<MessageTo> getAllMessages() {
        return MessageUtil.getMessageToList(messageRepository.getAllMessages());
    }

    @Override
    public void rateMessage(String action, Integer message_id, User user) {
        Integer user_id = user.getId();
        boolean newRate = action.equals("like") ? true : false;

        Message message = messageRepository.get(message_id);

        List<MessageRate> messageRates = message.getMessageRates()
                .stream()
                .filter(r -> r.getUser().getId() == user_id)
                .collect(Collectors.toList());

        if (messageRates.size() != 0) {
            MessageRate messageRateForUserId = messageRates.get(0);
            boolean currentRate = messageRateForUserId.getRate();

            if (newRate != currentRate) {
                messageRateForUserId.setRate(!currentRate);
                messageRateRepository.updateMessageRate(messageRateForUserId);
            } else if (newRate == currentRate) {

                messageRateRepository.deleteMessageRate(messageRateForUserId.getId());
            }
        }else {
            messageRateRepository.updateMessageRate(new MessageRate(user, message, newRate));
        }
    }
}
