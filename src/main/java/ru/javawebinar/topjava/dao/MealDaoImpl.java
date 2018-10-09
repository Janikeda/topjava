package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoImpl implements MealDao {
    private static AtomicInteger uniqueId = new AtomicInteger(0);

    private List<Meal> mealDataList = new CopyOnWriteArrayList<>(Arrays.asList(
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
            new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)));

    private Map<Integer, Meal> createMapForSearchingByID() {
        Map<Integer, Meal> meals = new HashMap<>();

        mealDataList.forEach(meal -> {
            meals.put(meal.getId(), meal);
        });

        return meals;
    }

    @Override
    public Meal add(Meal meal) {
        Meal mealForDb = new Meal(generateId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mealDataList.add(mealForDb);
        return meal;
    }

    @Override
    public Meal getById(int id) {
        Map<Integer, Meal> map = createMapForSearchingByID();
        return map.get(id);
    }

    @Override
    public List<Meal> findAll() {
        return mealDataList;
    }

    @Override
    public Meal update(Meal meal) {
        delete(meal.getId());
        Meal mealForDb = new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mealDataList.add(mealForDb);
        return mealForDb;
    }

    @Override
    public void delete(int id) {
        Map<Integer, Meal> map = createMapForSearchingByID();
        mealDataList.remove(map.get(id));
    }

    private int generateId() {
        return uniqueId.incrementAndGet();
    }
}