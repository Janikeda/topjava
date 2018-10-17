package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealRepository {
    Meal save(Meal meal);

    boolean delete(int id);

    Meal get(int id);

    Meal update(Meal meal);

    List<Meal> getAll(int id);

    List<Meal> getAllFilteredDate(int userId, LocalDate start, LocalDate finish);

    List<Meal> getAllFilteredTime(List<Meal> meals, LocalTime start, LocalTime finish);
}
