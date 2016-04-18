package ru.yandex.zhmyd.service.impl;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import ru.yandex.zhmyd.dao.UserDao;
import ru.yandex.zhmyd.dao.impl.UserDaoImpl;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDao dao = new UserDaoImpl();

    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(List<User> data) {
        dao.save(data);
    }

    @Override
    public List<User> getByParameter(String key, String value) {
        Criterion criterion = Restrictions.eq(key, value);
        return dao.getByCriterion(criterion);
    }


}
