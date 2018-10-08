package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.util.List;

public interface MealDao {

    void add(Meal meal);

    Meal getById(int id);

    List<MealWithExceed> findAll();

    void update(MealWithExceed mealWithExceed);

    void delete(int id);

    int generateId();
}