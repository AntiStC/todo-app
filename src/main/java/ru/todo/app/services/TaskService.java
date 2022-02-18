package ru.todo.app.services;

import ru.todo.app.dao.TaskDao;
import ru.todo.app.entity.Task;

import java.util.List;

public class TaskService {

    private TaskDao taskDao = new TaskDao();

    public TaskService() {

    }

    public void saveTask(Task task) {
        taskDao.saveTask(task);
    }

    public void deleteAllTask(long chatId) {
        taskDao.deleteAllTask(chatId);
    }

    public void deleteTask(long chatId, String actions) {
        taskDao.deleteTask(chatId, actions);
    }

    public void resolveTask(long chatId, String actions) {
        taskDao.resolveTask(chatId, actions);
    }

    public List<Task> getAllTasks(long chatId) {
        return taskDao.getAll(chatId);
    }
}
