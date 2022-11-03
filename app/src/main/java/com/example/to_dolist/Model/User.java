package com.example.to_dolist.Model;

import java.io.Serializable;

public class User implements Serializable {
    private int idUser;
    private String nameUser;
    private String taskUser;
    private boolean taskStatus;

    public User(int idUser, String nameUser, String taskUser, boolean taskStatus) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.taskUser = taskUser;
        this.taskStatus = taskStatus;
    }

    public User(String nameUser, String taskUser, boolean taskStatus) {
        this.nameUser = nameUser;
        this.taskUser = taskUser;
        this.taskStatus = taskStatus;
    }

    public User(String taskUser, boolean taskStatus) {
        this.taskUser = taskUser;
        this.taskStatus = taskStatus;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(String taskUser) {
        this.taskUser = taskUser;
    }

    public boolean isTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(boolean taskStatus) {
        this.taskStatus = taskStatus;
    }
}
