package com.stn.pojo;

import java.sql.Timestamp;

public class News {

    int idNews;
    String title;
    String body;
    Timestamp date;
    int idUser;
    int idSerie;

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
}
