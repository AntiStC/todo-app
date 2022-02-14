package ru.todo.app.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.todo.app.entity.Task;
import ru.todo.app.utils.HibernateUtil;

import java.util.List;

public class TaskDao {

    public Task findByName(String name) {
        return HibernateUtil.getSessionFactory().openSession().get(Task.class, name);
    }

    public void save(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(task);
        transaction.commit();
        session.close();
    }

    public void update(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(task);
        transaction.commit();
        session.close();
    }

    public void delete(Task task) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(task);
        transaction.commit();
        session.close();
    }

    public List<Task> getAll(){
        List<Task> tasks= (List<Task>) HibernateUtil.getSessionFactory().
                openSession().createQuery("From Task").list();
        return tasks;
    }
}
