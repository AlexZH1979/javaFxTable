package ru.yandex.zhmyd.dao;


import org.hibernate.criterion.Criterion;
import ru.yandex.zhmyd.entity.User;

import java.util.List;

public interface UserDao {

    User getById(Integer id);

    List<User> getAll();

    void save(User user);

    void save(List<User> users);

    List<User> getByCriterion(Criterion criterion);
}
