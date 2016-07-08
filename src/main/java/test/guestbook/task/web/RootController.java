package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import test.guestbook.task.model.Role;
import test.guestbook.task.model.User;
import test.guestbook.task.service.MessageService;
import test.guestbook.task.service.UserService;
import test.guestbook.task.to.MessageTo;
import test.guestbook.task.util.exception.NotFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by Qouer on 30.06.2016.
 */

@Controller
//@RequestMapping("/gb")
public class RootController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String defaultURL(){
        return "redirect:main";
    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getAll(Model model) {
        return "main";
    }

    @RequestMapping(value = "/main/auth_error", method = RequestMethod.GET)
    public String failLogin(Model model) {
        model.addAttribute("auth_error", true);
        return "main";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registration(){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String submitRegistration(@RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("name") String name,
                                     HttpServletRequest request,
                                     Model model){
        try{
            userService.getByEmail(email);
            model.addAttribute("email", "Пользователь с таким email'ом уже существует");
            model.addAttribute("password", password);
            model.addAttribute("name", name);
            return "register";
        }catch(NotFoundException e){
            System.err.println("I not catch you!");
            userService.save(new User(email, name, password, EnumSet.of(Role.ROLE_USER)));
            try {
                request.logout();
            } catch (ServletException e1) {
                e1.printStackTrace();
            }
        }

        return "redirect:/main";
    }


}
