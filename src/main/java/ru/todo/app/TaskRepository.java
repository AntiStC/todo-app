package ru.todo.app;

import java.sql.*;

public class TaskRepository {

    static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/todo-app",
                    "postgres", "admin");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    static void createDbTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DTASK("
                + "ChatID NUMBER (50) NOT NULL, "
                + "Id VARCHAR(20) NOT NULL, "
                + "Name VARCHAR(5000) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (ChatID) "
                + ")";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Table \"db\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
