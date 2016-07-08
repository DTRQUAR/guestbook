package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import test.guestbook.task.LoggedUser;
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
    public String submitRegistration(@RequestParam("id") Integer id,
                                     @RequestParam("email") String email,
                                     @RequestParam("password") String password,
                                     @RequestParam("name") String name,
                                     @RequestParam(value = "isEmailNotif", required = false) String isEmailNotif,
                                     Model model, ModelAndView modelAndView){
        boolean isEmailNotifValue = isEmailNotif != null ? true : false;
        System.err.println(isEmailNotifValue);
        if (id == null) {
            try{
                userService.getByEmail(email);
                model.addAttribute("email", "Пользователь с таким email'ом уже существует");
                model.addAttribute("password", password);
                model.addAttribute("name", name);
                model.addAttribute("isEmailNotif", isEmailNotifValue);
                return "register";
            }catch(NotFoundException e){

                userService.save(new User(null, email, name, password, EnumSet.of(Role.ROLE_USER), isEmailNotifValue));
            }
        }else{
            User savedUser = userService.save(new User(id, email, name, password, EnumSet.of(Role.ROLE_USER), isEmailNotifValue));
            LoggedUser loggedUser = LoggedUser.safeGet();
            loggedUser.setAuthUser(savedUser);
            modelAndView.getModelMap().addAttribute("authUser", loggedUser.getAuthUser());
        }
        return "redirect:/main";
    }


}
