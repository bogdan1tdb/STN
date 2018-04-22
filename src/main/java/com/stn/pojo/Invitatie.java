package com.stn.pojo;

import java.sql.Timestamp;

public class Invitatie {

    private int idInvitatie;
    private String token;
    private String email;
    private int idGrupa;
    private int idSerie;
    private int idFacultate;
    private int userClass;
    private Timestamp expDate;

    public Invitatie(int idInvitatie, String token, String email, int idGrupa, int idSerie, int idFacultate, int userClass, Timestamp expDate) {
        this.idInvitatie = idInvitatie;
        this.token = token;
        this.email = email;
        this.idGrupa = idGrupa;
        this.idSerie = idSerie;
        this.idFacultate = idFacultate;
        this.userClass = userClass;
        this.expDate = expDate;
    }

    public void setIdInvitatie(int idInvitatie) {
        this.idInvitatie = idInvitatie;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public void setIdFacultate(int idFacultate) {
        this.idFacultate = idFacultate;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
    }

    public int getIdInvitatie() {
        return idInvitatie;
    }

    public String getToken() {
        return token;
    }

    public String getEmail() {
        return email;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public int getIdFacultate() {
        return idFacultate;
    }

    public int getUserClass() {
        return userClass;
    }

    public Timestamp getExpDate() {
        return expDate;
    }
}
