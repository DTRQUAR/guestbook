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
import java.util.Iterator;
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
        Iterator<Message> iterator = allMessages.iterator();
        while(iterator.hasNext()) System.out.println(iterator.next());
        model.addAttribute("allMessages", allMessages);
        System.out.println("allMessages put in model");
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String createMessage(
                    @RequestParam("name") String defaultName,
                    @RequestParam("text") String text) {
        System.out.println(defaultName + " " + text);
        Message message = new Message(defaultName, text, LocalDateTime.now());
        messageRepository.create(message);
        return "redirect:main";
    }
}
