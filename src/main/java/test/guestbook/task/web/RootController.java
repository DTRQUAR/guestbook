package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import test.guestbook.task.LoggedUser;
import test.guestbook.task.model.Message;
import test.guestbook.task.model.Role;
import test.guestbook.task.model.User;
import test.guestbook.task.repository.MessageRepository;
import test.guestbook.task.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */

@Controller
//@RequestMapping(value = "/guestbook")
public class RootController {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultURL(){
        System.err.println("defaultURL");
        return "redirect:main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.POST)
    public String createMessage(
            @RequestParam(value = "name", required = false) String defaultName,
            @RequestParam(value = "text", required = true) String text) {
        System.err.println("createMessage invoke");
        Message message;
        if (defaultName != null && text != null) {
            message = new Message(defaultName, text, LocalDateTime.now());
        }else{
            message = new Message(null, text, LocalDateTime.now());
            message.setUser(LoggedUser.safeGet().getAuthUser());
        }
        messageRepository.create(message);
        return "redirect:/main";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAll(Model model) {
//        System.err.println("getALL invoke");
        List<Message> allMessages = messageRepository.getAllMessages();
//        Iterator<Message> iterator = allMessages.iterator();
//        while(iterator.hasNext()) System.out.println(iterator.next());
        model.addAttribute("allMessages", allMessages);
        return "main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(){
        System.err.println("registration");
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitRegistration(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("name") String name,
                                     HttpServletRequest request){
        userService.save(new User(email, name, password, EnumSet.of(Role.ROLE_USER)));
        try {
            request.logout();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "redirect:/main";
    }


}
