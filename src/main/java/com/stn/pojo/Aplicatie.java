package com.stn.pojo;

import java.sql.Timestamp;

public class Aplicatie {

    private int appId;
    private String nume;
    private String prenume;
    private String facultate;
    private String serie;
    private String grupa;
    private String email;
    private String document;
    private Timestamp date;
    private int type;

    public Aplicatie(int appId, String nume, String prenume, String facultate, String serie, String grupa, String email, String document, Timestamp date, int type) {
        this.appId = appId;
        this.nume = nume;
        this.prenume = prenume;
        this.facultate = facultate;
        this.serie = serie;
        this.grupa = grupa;
        this.email = email;
        this.document = document;
        this.date = date;
        this.type = type;
    }

    public void setAppId(int appId) {
        this.appId = appId;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAppId() {
        return appId;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getFacultate() {
        return facultate;
    }

    public String getSerie() {
        return serie;
    }

    public String getGrupa() {
        return grupa;
    }

    public String getEmail() {
        return email;
    }

    public String getDocument() {
        return document;
    }

    public Timestamp getDate() {
        return date;
    }

    public int getType() {
        return type;
    }

}
