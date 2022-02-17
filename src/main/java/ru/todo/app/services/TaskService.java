package ru.todo.app.services;

import ru.todo.app.dao.TaskDao;
import ru.todo.app.entity.Task;

import java.util.List;

public class TaskService {

    private TaskDao taskDao=new TaskDao();

    public TaskService(){

    }

    public Task findTask(String name){
        return taskDao.findByName(name);
    }

    public void saveTask(Task task){
        taskDao.save(task);
    }

    public void deleteTask(Task task){
        taskDao.delete(task);
    }

    public void updateTask(Task task){
        taskDao.update(task);
    }

    public List<Task> getAllTasks(long chatId){
        return taskDao.getAll(chatId);
    }
}
