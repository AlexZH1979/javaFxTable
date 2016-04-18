package ru.yandex.zhmyd.dao.impl;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import ru.yandex.zhmyd.HibernateUtil;
import ru.yandex.zhmyd.dao.UserDao;
import ru.yandex.zhmyd.entity.User;

import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public User getById(Integer id) {
        Session session = getSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> getAll() {
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        List<User> users = criteria.list();
        session.close();
        return users;
    }

    @Override
    public void save(User user) {
        Session session = getSession();
        session.getTransaction().begin();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void save(List<User> users) {
        Session session = getSession();
        session.getTransaction().begin();
        for (User user:users) {
            session.saveOrUpdate(user);
        }
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getByCriterion(Criterion criterion) {
        Session session = getSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(criterion);
        List<User> users = criteria.list();
        session.close();
        return users;
    }

    private Session getSession(){
        return  HibernateUtil.getSessionFactory().openSession();
    }
}
