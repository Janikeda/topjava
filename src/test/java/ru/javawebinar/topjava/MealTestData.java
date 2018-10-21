package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class MealTestData {

/*    public static final Meal MEAL_1 = new Meal(null, DateTimeUtil.toDateTime("2015-05-30 10:00"), "Завтрак", 500);
    public static final Meal MEAL_2 = new Meal(null, "2015-05-30 13:00", "Обед", 1000, 100000);
    public static final Meal MEAL_3 = new Meal(null, "2015-05-30 20:00", "Ужин", 500, 100000);
    public static final Meal MEAL_4 = new Meal(null, "2015-05-31 10:00", "Завтрак", 1000, 100000);
    public static final Meal MEAL_5 = new Meal(null, "2015-05-31 13:00", "Обед", 500, 100000);
    public static final Meal MEAL_6 = new Meal(null, "2015-05-31 20:00", "Ужин", 510, 100000);
    public static final Meal MEAL_7 = new Meal(null, "2015-06-01 14:00", "Админ ланч", 510, 100001);
    public static final Meal MEAL_8 = new Meal(null, "2015-06-01 21:00", "Админ ужин", 1500, 100001);*/


    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}


