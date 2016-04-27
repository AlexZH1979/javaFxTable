package ru.yandex.zhmyd.service.impl;


import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import ru.yandex.zhmyd.dao.UserDao;
import ru.yandex.zhmyd.dao.impl.UserDaoImpl;
import ru.yandex.zhmyd.entity.User;
import ru.yandex.zhmyd.service.UserService;

import javax.inject.Inject;
import java.util.List;

public class UserServiceImpl implements UserService{

    @Inject
    public UserDao dao;



    @Override
    public List<User> getAll() {
        return dao.getAll();
    }

    @Override
    public void save(List<User> data) {
        for(User user:data) {
            dao.save(user);
        }
    }

    @Override
    public List<User> getByParameter(String key, String value) {
        Criterion criterion = Restrictions.eq(key, value);
        return dao.getByCriteria(criterion);
    }


}
