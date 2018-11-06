package ru.javawebinar.topjava.service.datajpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;

import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractUserServiceTest {
    @Test
    public void testGetWithMeals() throws Exception {
        User admin = service.getWithMeals(ADMIN_ID);
        assertMatch(admin, ADMIN);
        MealTestData.assertMatch(admin.getMeals(), MealTestData.ADMIN_MEAL2, MealTestData.ADMIN_MEAL1);
    }
}