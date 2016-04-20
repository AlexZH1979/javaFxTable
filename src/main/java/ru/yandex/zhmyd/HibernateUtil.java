package ru.yandex.zhmyd;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import ru.yandex.zhmyd.entity.Department;
import ru.yandex.zhmyd.entity.User;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
                sessionFactory = new AnnotationConfiguration().addPackage("ru.yandex.zhmyd.entity")
                        .addAnnotatedClass(User.class).addAnnotatedClass(Department.class)
                        .configure().buildSessionFactory();
            } catch (Throwable ex) {
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close(){
        sessionFactory.close();
    }
}