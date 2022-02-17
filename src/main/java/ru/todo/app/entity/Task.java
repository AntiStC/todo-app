package ru.todo.app.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task",schema = "public",catalog = "todo-app")
public class Task {

    @Column(name = "creationDate")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Date creationDate;

    @Column(name = "name")
    private String name;

    @Column(name = "done")
    private boolean done;

    @Column(name = "chatid")
    private Long chatId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    public Task(String name, Long chatId) {
        this.name = name;
        this.chatId = chatId;
        creationDate = new Date();
        done = false;
    }

    public Task() {

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return name + (done ? " [+] " : " [-] ") + "\n";
    }


}
