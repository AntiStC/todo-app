package ru.todo.app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.todo.app.entity.Task;
import ru.todo.app.utils.HibernateUtil;

import java.util.List;

public class TaskDao {


    public void saveTask(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(task);
        transaction.commit();
        session.close();
    }

    public void resolveTask(long chatId, String actions) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE Task SET done = true" +
                " where chatId = : chatId and body = : body");
        query.setParameter("chatId", chatId).
                setParameter("body", actions);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteAllTask(long chatId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "delete from Task where chatId = : chatId");
        query.setParameter("chatId", chatId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public void deleteTask(long chatId, String actions) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery(
                "delete from Task where chatId = : chatId " +
                        "and body = : body");
        query.setParameter("chatId", chatId).
                setParameter("body", actions);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    public List getAll(long chatId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Task where chatId = : chatId");
        query.setParameter("chatId", chatId);
        List list = query.list();
        return list;
    }
}
