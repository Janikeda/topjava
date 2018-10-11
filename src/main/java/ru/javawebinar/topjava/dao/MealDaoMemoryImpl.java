package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDaoMemoryImpl implements MealDao {
    private AtomicInteger uniqueId = new AtomicInteger(0);

    private Map<Integer, Meal> mapMealData = createMapForSearchingByID();


    private Map<Integer, Meal> createMapForSearchingByID() {
        Map<Integer, Meal> map = new ConcurrentHashMap<>();

        Meal meal_1 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal meal_2 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000);
        Meal meal_3 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500);
        Meal meal_4 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000);
        Meal meal_5 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500);
        Meal meal_6 = new Meal(generateId(), LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510);

        map.put(meal_1.getId(), meal_1);
        map.put(meal_2.getId(), meal_2);
        map.put(meal_3.getId(), meal_3);
        map.put(meal_4.getId(), meal_4);
        map.put(meal_5.getId(), meal_5);
        map.put(meal_6.getId(), meal_6);

        return map;
    }

    @Override
    public Meal add(Meal meal) {
        Meal mealForDb = new Meal(generateId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mapMealData.put(mealForDb.getId(), mealForDb);
        return mealForDb;
    }

    @Override
    public Meal getById(int id) {
        return mapMealData.get(id);
    }

    @Override
    public List<Meal> findAll() {
        return new ArrayList<>(mapMealData.values());
    }

    @Override
    public Meal update(Meal meal) {
        //delete(meal.getId());
        Meal mealForDb = new Meal(meal.getId(), meal.getDateTime(), meal.getDescription(), meal.getCalories());
        mapMealData.replace(meal.getId(), meal, mealForDb);
        return mealForDb;
    }

    @Override
    public void delete(int id) {
        mapMealData.remove(id);
    }

    private int generateId() {
        return uniqueId.incrementAndGet();
    }
}