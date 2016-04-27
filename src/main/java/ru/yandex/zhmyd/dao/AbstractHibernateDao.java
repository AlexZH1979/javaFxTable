package ru.yandex.zhmyd.dao;

import org.hibernate.Criteria;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import ru.yandex.zhmyd.HibernateUtil;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractHibernateDao<T, PK extends Serializable> implements GenericDao<T, PK> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
    protected Class<T> getGenericEntityClass() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    @SuppressWarnings("unchecked")
    @Override
    public PK save(T obj) {
        Session session=getSession();
        session.getTransaction().begin();
        PK pk = (PK) session.save(obj);
        session.getTransaction().commit();
        return pk;
    }

    @Override
    public void update(T o) {
        Session session=getSession();
        session.getTransaction().begin();
        session.update(o);
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        Session session=getSession();
        session.getTransaction().begin();
        Criteria cr = session.createCriteria(this.getGenericEntityClass());
        List<T> list =(List<T>) cr.list();
        session.getTransaction().commit();
        return list;
    }
    @Override
    public Long getLength(){
        return (Long) getSession().createCriteria(this.getGenericEntityClass()).setProjection( Projections.rowCount() ).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getByCriteria(Criterion criterion) {
        Session session=getSession();
        session.getTransaction().begin();
        Criteria criteria =session.createCriteria(this.getGenericEntityClass());
        criteria.add(criterion);
        return (List<T>) criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Long getLength(Criterion criterion) {
        Criteria criteria = getSession().createCriteria(this.getGenericEntityClass());
        criteria.add(criterion);
        return (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> getByCriteria(Criterion criterion, int begin, int count) {
        Session session=getSession();
        session.getTransaction().begin();
        if(begin< 0 ||count<0){
            return new ArrayList<T>();
        }
        Criteria criteria = getSession().createCriteria(this.getGenericEntityClass());
        if(criterion!=null){
            criteria.add(criterion);
        }
        criteria.setFirstResult(begin).setMaxResults(count);

        return (List<T>)criteria.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public T getById(PK id) {
        return (T) getSession().get(this.getGenericEntityClass(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(PK id) {
        Session session=getSession();
        session.getTransaction().begin();
        T persistentObject = (T) session.load(this.getGenericEntityClass(), id);
        try {
            session.delete(persistentObject);
        } catch (NonUniqueObjectException e) {
            // in a case of detached object
            T instance = (T) getSession().merge(persistentObject);
            session.delete(instance);
        }
        session.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void delete(T persistentObject) {
        Session session=getSession();
        session.getTransaction().begin();
        try {
            session.delete(persistentObject);
        } catch (NonUniqueObjectException e) {
            // in a case of detached object
            T instance = (T) session.merge(persistentObject);
            session.delete(instance);
        }
        session.getTransaction().commit();
    }
}