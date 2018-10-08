package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    CopyOnWriteArrayList<Meal> meals = new CopyOnWriteArrayList<>(Arrays.asList(
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
    ));


    @Override
    public void add(Meal meal) {
        meals.add(meal);
    }

    @Override
    public Meal getById(int id) {
        return meals.stream().filter(meal -> meal.getId() == id).findFirst().get();
    }

    @Override
    public List<MealWithExceed> findAll() {
        return MealsUtil.getFilteredWithExceeded(meals, LocalTime.MIN, LocalTime.MAX, 2000);
    }

    @Override
    public void update(MealWithExceed mealWithExceed) {

    }

    @Override
    public void delete(int id) {
        meals.remove(meals.stream().filter(meal -> meal.getId() == id).findFirst().get());
    }

    @Override
    public int generateId() {
        return atomicInteger.incrementAndGet();
    }
}