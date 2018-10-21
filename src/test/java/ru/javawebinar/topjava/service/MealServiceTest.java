package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void create() throws Exception {
//        Meal newMeal = new Meal(null, "New", "new@gmail.com", "newPass", 1555, false, new Date());
//        Meal created = service.create(newMeal);
//        newMeal.setId(created.getId());
//        assertMatch(service.getAll(), newMeal);
    }

//    @Test(expected = DataAccessException.class)
//    public void duplicateMailCreate() throws Exception {
//        service.create(new User(null, "Duplicate", "user@yandex.ru", "newPass", Role.ROLE_USER));
//    }
//
//    @Test
//    public void delete() throws Exception {
//        service.delete(USER_ID);
//        assertMatch(service.getAll(), ADMIN);
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void deletedNotFound() throws Exception {
//        service.delete(1);
//    }
//
//    @Test
//    public void get() throws Exception {
//        User user = service.get(USER_ID);
//        assertMatch(user, USER);
//    }
//
//    @Test(expected = NotFoundException.class)
//    public void getNotFound() throws Exception {
//        service.get(1);
//    }


//    @Test
//    public void update() throws Exception {
//        User updated = new User(USER);
//        updated.setName("UpdatedName");
//        updated.setCaloriesPerDay(330);
//        service.update(updated);
//        assertMatch(service.get(USER_ID), updated);
//    }
//
//    @Test
//    public void getAll() throws Exception {
//        List<User> all = service.getAll();
//        assertMatch(all, ADMIN, USER);
//    }
}
