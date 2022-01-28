package ru.todo.app;

import java.sql.*;

public class TaskRepository {
    private static final String URL = "jdbc:postgresql://localhost:5432/todo-app";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "admin";

    static Connection getDBConnection() {
        Connection dbConection = null;
        Statement statement = null;
        try {
            Driver driver = new org.postgresql.Driver();
            DriverManager.registerDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            dbConection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = dbConection.createStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConection;
    }
}
