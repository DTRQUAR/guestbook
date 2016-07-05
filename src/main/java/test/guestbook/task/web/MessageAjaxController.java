package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.guestbook.task.LoggedUser;
import test.guestbook.task.model.Message;
import test.guestbook.task.service.MessageService;
import test.guestbook.task.service.UserService;
import test.guestbook.task.to.MessageTo;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Qouer on 04.07.2016.
 */
@RestController
@RequestMapping(value = "/ajax/messages")
public class MessageAjaxController {

    @Autowired
    MessageService messageService;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MessageTo> getAll() {
        return messageService.getAllMessages();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody MessageTo createMessage(
                        @RequestParam(value = "name", required = false) String defaultName,
                        @RequestParam(value = "text", required = true) String text) {
        Message message;
        System.err.println(defaultName + " " + text);
        if (defaultName != null && text != null) {
            message = new Message(defaultName, text);
        }else{
            message = new Message(null, text);
            message.setUser(LoggedUser.safeGet().getAuthUser());
        }
        return messageService.create(message);
    }

    @RequestMapping(value = "/rate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageTo rateMessage(@RequestParam(value = "action", required = true) String action,
                              @RequestParam(value = "message", required = true) Integer message_id) {

        messageService.rateMessage(action, message_id, LoggedUser.safeGet().getAuthUser());
        return messageService.get(message_id);
    }


}
