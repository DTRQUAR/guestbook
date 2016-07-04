package test.guestbook.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import test.guestbook.task.model.Message;
import test.guestbook.task.service.MessageService;
import test.guestbook.task.service.UserService;
import test.guestbook.task.to.MessageTo;

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

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<MessageTo> getAll() {
        return messageService.getAllMessages();
    }
}
