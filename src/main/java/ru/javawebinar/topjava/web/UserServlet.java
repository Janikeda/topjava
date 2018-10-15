package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.web.user.AdminRestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);

    private AdminRestController adminRestController;

    @Override
    public void init() throws ServletException {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        adminRestController = appCtx.getBean(AdminRestController.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        log.debug("forward to users");
        switch (action == null ? "all" : action) {
            case "showMealList":
                String userId = request.getParameter("id");
                response.sendRedirect("/topjava/meals?userId="+userId);
                break;
            case "all":
            default:
                request.setAttribute("users", adminRestController.getAll());
                request.getRequestDispatcher("/users.jsp").forward(request, response);
                break;
        }
    }
}

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String userId = req.getParameter("userId");
//        if (Integer.parseInt(userId) == SecurityUtil.authUserId()) {
//            resp.sendRedirect("meals");
//        }  else {
//            throw new NotFoundException("Sorry, there is no food for you :(");
//        }
//    }
