package ru.todo.app;

import java.util.Date;

public class Task {
    private Date creationDate;
    private String name;
    private boolean done;
    private Long chatId;

    Task(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
        creationDate = new Date();
        done = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String toString() {
        return name + (done ? " [+] " : " [-] ") + "\n";
    }
}
