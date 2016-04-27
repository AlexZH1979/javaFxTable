package ru.yandex.zhmyd.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import ru.yandex.zhmyd.HibernateUtil;
import ru.yandex.zhmyd.dao.AbstractHibernateDao;
import ru.yandex.zhmyd.dao.UserDao;
import ru.yandex.zhmyd.entity.User;

import java.util.List;

public class UserDaoImpl extends AbstractHibernateDao<User,Integer> implements UserDao {
}
