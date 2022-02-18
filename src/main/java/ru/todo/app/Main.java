package ru.todo.app;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import ru.todo.app.entity.Task;
import ru.todo.app.services.TaskService;

import java.util.List;


public class Main {
    private static final String TELEGRAM_TOKEN = "";
    private static int offset = 0;
    private static Long counter = 0L;


    public static void main(String[] args) {

        //create bot
        TelegramBot bot = new TelegramBot(TELEGRAM_TOKEN);
        while (true) {

            //receiving incoming messages
            GetUpdates getUpdates = new GetUpdates().limit(1).offset(offset).timeout(0); //at once only 1 message
            GetUpdatesResponse updatesResponse = bot.execute(getUpdates);
            List<Update> updates = updatesResponse.updates();

            if (!updates.isEmpty()) {
                Update update = updates.get(0);
                Message message = update.message();
                String action = message.text();
                long chatId = update.message().chat().id();


                String response = "";

                String[] actions = action.split(": ");
                if ("/resolve".equals(actions[0])) {
                    response = resolveTask(chatId, actions[1]);
                } else if ("/delete".equals(actions[0])) {
                    response = deleteTask(chatId, actions[1]);
                } else if (action.equals("/delete-all")) {
                    response = deleteAllTask(chatId);
                } else if (action.equals("/start")) {
                    response = " Начните вводить задачу, при созданной задаче отобразится надпись " +
                            "'Задача создана.'\n '/get-all' - отобразит все сохраненные задачи";
                } else if (action.equals("/get-all")) {
                    response = getAll(chatId);
                } else {
                    response = createTask(action, chatId);
                }
                SendMessage sendMessage = new SendMessage(message.chat().id(), response);
                bot.execute(sendMessage);

                offset = update.updateId() + 1; //mark incoming message as readen
            }
        }
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
        String response = "";
        response += taskService.getAllTasks(chatId);
        return response;
    }

    private static String resolveTask(long chatId, String actions) {
        TaskService taskService = new TaskService();
        taskService.resolveTask(chatId, actions);
        return "Задача выполнена!";
    }

    private static String deleteTask(long chatId, String actions) {
        TaskService taskService = new TaskService();
        taskService.deleteTask(chatId, actions);
        return "Задача удалена!";
    }

    private static String deleteAllTask(long chatId) {
        TaskService taskService = new TaskService();
        taskService.deleteAllTask(chatId);
        return "Все задачи удалены!";
    }
}

