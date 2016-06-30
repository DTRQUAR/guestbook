package test.guestbook.task.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Qouer on 30.06.2016.
 */

@Controller
public class RootController extends HttpServlet{

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getMain() {
        System.out.println("lala");
        return "main";
    }


//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getRequestDispatcher("/main.jsp").forward(req, resp);
//    }

}
