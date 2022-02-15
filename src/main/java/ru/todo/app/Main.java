package ru.todo.app;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.jetbrains.annotations.NotNull;
import ru.todo.app.entity.Task;
import ru.todo.app.services.TaskService;

import java.sql.SQLException;
import java.util.*;

public class Main {

    public static void main(String[] args) {

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
        TaskService taskService = new TaskService();
        Task task = new Task(action, chatId);
        taskService.saveTask(task);
        return "Задача создана.";
    }

    private static String getAll(long chatId) {
        TaskService taskService = new TaskService();
        Task task = new Task();
        String response = "";
        if (task.getChatId() == chatId) {
            response += taskService.getAllTasks();
        }
        return response;
    }

}

