package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import test.guestbook.task.LoggedUser;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultURL(){
        return "redirect:main";
    }

    @RequestMapping(value = "/main/auth", method = RequestMethod.GET)
    public String getAllAsAuth(){
        System.out.println("getAllAsAuth invoke");
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAll(Model model) {
        System.out.println("getALL invoke");
        List<Message> allMessages = messageRepository.getAllMessages();
        Iterator<Message> iterator = allMessages.iterator();
        while(iterator.hasNext()) System.out.println(iterator.next());
        model.addAttribute("allMessages", allMessages);
        return "main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String createMessage(
                    @RequestParam(value = "name", required = false) String defaultName,
                    @RequestParam(value = "text", required = false) String text) {
        System.out.println("createMessage invoke");
        if (defaultName == null) defaultName = LoggedUser.safeGet().getAuthUser().getName();
        if (text != null) {
            System.out.println(defaultName + " " + text);
            Message message = new Message(defaultName, text, LocalDateTime.now());
            messageRepository.create(message);
            return "redirect:/main";
        }
        return "redirect:/main";

    }
}
