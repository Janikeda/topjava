package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoImpl;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static MealDao mealDao = new MealDaoImpl();

    private static String MEAL_LIST = "mealList.jsp";
    private static String INSERT_OR_EDIT = "meal.jsp";

    public MealServlet() {
        super();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String forward = "";
        String action = req.getParameter("action");

        if (action != null && action.equalsIgnoreCase("delete")) {
            log.debug("Delete meal");
            int userId = Integer.parseInt(req.getParameter("userId"));
            forward = MEAL_LIST;
            req.setAttribute("mealList", mealDao.delete(userId));
        } else if (action != null && action.equalsIgnoreCase("edit")) {
            log.debug("Edit meal");
            forward = INSERT_OR_EDIT;
            int userId = Integer.parseInt(req.getParameter("userId"));
            Meal meal = mealDao.getById(userId);
            req.setAttribute("meal", meal);
        } else if (action != null && action.equalsIgnoreCase("showMeals")) {
            log.debug("Show list Meals");
            forward = MEAL_LIST;
            req.setAttribute("mealList", mealDao.findAll());
        } else {
            log.debug("Make Insert/Edit");
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = req.getRequestDispatcher(forward);
        view.forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Add meal");
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(LocalDateTime.parse(req.getParameter("datetime")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")), mealDao.generateId());

        mealDao.add(meal);

        RequestDispatcher view = req.getRequestDispatcher(MEAL_LIST);
        req.setAttribute("mealList", mealDao.findAll());
        view.forward(req, resp);
    }
}