package ru.yandex.zhmyd.entity.dao.impl;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import ru.yandex.zhmyd.HibernateUtil;
import ru.yandex.zhmyd.dao.UserDao;
import ru.yandex.zhmyd.dao.impl.UserDaoImpl;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.service.UserService;
import ru.yandex.zhmyd.service.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;



public class UserServiceImplTest {

    private UserService userService = new UserServiceImpl();

    @Test
    public void getAllTest() {
        List<User> users = new ArrayList<>();
        assertEquals(users, userService.getAll());
    }
}
