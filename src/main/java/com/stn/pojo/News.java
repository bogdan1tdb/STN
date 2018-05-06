package com.stn.pojo;

import java.sql.Time;
import java.sql.Timestamp;

public class News {

    int idNews;
    String title;
    String body;
    Timestamp date;
    int idUser;
    int idSerie;
    int userClass;
    String username;
    Timestamp lastEdit;

    public void setIdNews(int idNews) {
        this.idNews = idNews;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLastEdit(Timestamp lastEdit) {
        this.lastEdit = lastEdit;
    }

    public int getIdNews() {
        return idNews;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public int getUserClass() {
        return userClass;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getLastEdit() {
        return lastEdit;
    }
}
