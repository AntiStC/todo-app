package ru.todo.app;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import ru.todo.app.TaskRepository;

public class Main {
    private static List<Task> taskList;


    public static void main(String[] args) throws SQLException {

        TaskRepository.getDBConnection();


        taskList = new ArrayList<>();

        TelegramBot bot = new TelegramBot("2097883864:AAEptnYscfJeqb7b0DsHISrr__ROrqMixyE");
        bot.setUpdatesListener(updates -> {
            Update update = updates.get(0);
            String action = update.message().text();
            long chatId = update.message().chat().id();
            String response = "";
            switch (action) {
                case "/get-all":
                    response = getAll(chatId);
                    break;
                default:
                    response = createTask(action, chatId);
                    break;
            }
            bot.execute(new SendMessage(chatId, response));
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });

    }

//    private static void inspection(String action) {
//        if (list.contain(action)) {
//            throw ToDoAppBadWordException("w");
//        }
//    }

    private static String createTask(String action, long chatId) {
        taskList.add(new Task(action, chatId));
            return "Задача создана.";
        }

        private static String getAll ( long chatId){
            String response = "";
            for (Task task : taskList) {
                if (task.getChatId() == chatId) {
                    response += task;
                }
            }
            return response;
        }
    }
