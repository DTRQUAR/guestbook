package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import test.guestbook.task.model.Message;
import test.guestbook.task.repository.MessageRepository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */

@Controller
public class RootController {

    @Autowired
    MessageRepository messageRepository;

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Message> allMessages = messageRepository.getAllMessages();
        model.addAttribute("allMessages", allMessages);
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String createMessage(
                    @RequestParam("name") String name,
                    @RequestParam("text") String text) {
        System.out.println(text);
        Message message = new Message(text, LocalDateTime.now());
        messageRepository.create(message);
        return "redirect:main";
    }
}
