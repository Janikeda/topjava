package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(1, "Вася", "werw@email.com", "password1", Role.ROLE_ADMIN),
            new User( 2,"Петя", "qwer@email.com", "password2", Role.ROLE_USER)
    );
}