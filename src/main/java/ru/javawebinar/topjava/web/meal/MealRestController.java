package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.transfer_objects.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    public List<MealWithExceed> getAll() {
        log.info("getAll");
        return MealsUtil.getWithExceeded(service.getAll(SecurityUtil.getAuthUserId()),SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealWithExceed> getAllFiltered(LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd) {
        log.info("getAllFiltered");
        return MealsUtil.getFilteredWithExceeded(service.getAll(SecurityUtil.getAuthUserId()),SecurityUtil.authUserCaloriesPerDay(), dateTimeStart.toLocalTime(), dateTimeEnd.toLocalTime());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        if (service.get(id).getUserId() == SecurityUtil.getAuthUserId()) {
            return service.get(id);
        } else {
            throw new NotFoundException("There is no meal for you :(");
        }
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        service.update(meal);
    }
}