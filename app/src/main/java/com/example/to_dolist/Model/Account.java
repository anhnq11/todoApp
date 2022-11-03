package com.example.to_dolist.Model;

import java.io.Serializable;

public class Account implements Serializable {
    private int idAcc;
    private String nameAcc;
    private String emailAcc;
    private String passAcc;

    public Account(int idAcc, String nameAcc, String emailAcc, String passAcc) {
        this.idAcc = idAcc;
        this.nameAcc = nameAcc;
        this.emailAcc = emailAcc;
        this.passAcc = passAcc;
    }

    public Account(String nameAcc, String emailAcc, String passAcc) {
        this.nameAcc = nameAcc;
        this.emailAcc = emailAcc;
        this.passAcc = passAcc;
    }

    public Account(String nameAcc, String emailAcc) {
        this.nameAcc = nameAcc;
        this.emailAcc = emailAcc;
    }

    public int getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(int idAcc) {
        this.idAcc = idAcc;
    }

    public String getNameAcc() {
        return nameAcc;
    }

    public void setNameAcc(String nameAcc) {
        this.nameAcc = nameAcc;
    }

    public String getEmailAcc() {
        return emailAcc;
    }

    public void setEmailAcc(String emailAcc) {
        this.emailAcc = emailAcc;
    }

    public String getPassAcc() {
        return passAcc;
    }

    public void setPassAcc(String passAcc) {
        this.passAcc = passAcc;
    }
}
