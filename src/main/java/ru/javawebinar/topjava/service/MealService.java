package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface MealService {
    Meal create(Meal user);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    void update(Meal user);

    List<Meal> getAll(int userId);

    List<Meal> getAllFilteredDate(int userId, LocalDate start, LocalDate finish);

    List<Meal> getAllFilteredTime(List<Meal> meals, LocalTime start, LocalTime finish);
}