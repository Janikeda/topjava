package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;

public class MealWithExceed {
    private int id;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final boolean exceed;


    public MealWithExceed(int id, LocalDateTime dateTime, String description, int calories, boolean exceed) {
        this.id = id;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
    }

    @Override
    public String toString() {
        return "MealWithExceed{" +
                "dateTime = " + dateTime.format(TimeUtil.formatter) +
                ", description = '" + description + '\'' +
                ", calories = " + calories +
                ", exceed = " + exceed +
                '}';
    }

    public String getDateTime() {
        return dateTime.format(TimeUtil.formatter);
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public int getId() {
        return id;
    }

    public boolean isExceed() {
        return exceed;
    }
}