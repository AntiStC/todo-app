package ru.todo.app.utils;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory(); // Настройка и работа с сессиями (фабрика сессий)

    // фабрика для создания сессий
    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure(new File("src\\main\\resources\\hibernate.cfg.xml")).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }


    // метод вызывается когда потребуется SessionFactory
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // закрыть все соединения с помощью SessionFactory
    public static void shutdown() {
        getSessionFactory().close();
    }
}
