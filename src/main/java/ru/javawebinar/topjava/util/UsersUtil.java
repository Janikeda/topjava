package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(null, "Вася", "werw@email.com", "password1", Role.ROLE_ADMIN),
            new User( null,"Петя", "qwer@email.com", "password2", Role.ROLE_USER),
            new User( null,"Маша", "hjk@email.com", "password3", Role.ROLE_USER),
            new User( null,"Коля", "rtyjj@email.com", "password4", Role.ROLE_USER),
            new User( null,"Света", "dgdfhse@email.com", "password5", Role.ROLE_USER)
    );
}