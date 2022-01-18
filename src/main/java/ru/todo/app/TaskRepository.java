package ru.todo.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TaskRepository {

    public void getAllTasks() throws SQLException, ClassNotFoundException {


        Class.forName("jdbc:postgresql://localhost:5432/todo-app");
        System.out.println("Драйвер подключен");

        String url = "jdbc:postgresql://localhost:5432/todo-app";
        Properties props = new Properties();
        props.setProperty("user", "fred");
        props.setProperty("password", "secret");
        props.setProperty("ssl", "false");

        Connection conn = DriverManager.getConnection(url, props);


    }
}
