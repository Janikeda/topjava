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
    private MealDao mealDao = new MealDaoImpl();

    private static String MEAL_LIST = "mealList.jsp";
    private static String INSERT_OR_EDIT = "meal.jsp";


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jspPage = "";
        String action = req.getParameter("action");
        switch (action) {
            case "delete":
                log.debug("Delete meal");
                int userId = Integer.parseInt(req.getParameter("userId"));
                jspPage = MEAL_LIST;
                mealDao.delete(userId);
                req.setAttribute("mealList", mealDao.findAll());
                break;
            case "edit":
                log.debug("Edit meal");
                jspPage = INSERT_OR_EDIT;
                int userIdMeal = Integer.parseInt(req.getParameter("userId"));
                Meal meal = mealDao.getById(userIdMeal);
                req.setAttribute("meal", meal);
                break;
            case "showMeals":
                log.debug("Show list Meals");
                jspPage = MEAL_LIST;
                req.setAttribute("mealList", mealDao.findAll());
                break;
            default:
                log.debug("Show list Meals");
                jspPage = MEAL_LIST;
                req.setAttribute("mealList", mealDao.findAll());
        }

        req.getRequestDispatcher(jspPage).forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Add meal");
        req.setCharacterEncoding("UTF-8");
        Meal meal = new Meal(mealDao.generateId(), LocalDateTime.parse(req.getParameter("datetime")), req.getParameter("description"), Integer.parseInt(req.getParameter("calories")));

        mealDao.add(meal);

        req.setAttribute("mealList", mealDao.findAll());
        req.getRequestDispatcher(MEAL_LIST).forward(req, resp);
    }
}