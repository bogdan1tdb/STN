package com.stn.pojo;

public class Facultate {

    private int idFacultate;
    private String nume;

    public Facultate(int idFacultate, String nume) {
        this.idFacultate = idFacultate;
        this.nume = nume;
    }

    public void setIdFacultate(int idFacultate) {
        this.idFacultate = idFacultate;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdFacultate() {
        return idFacultate;
    }

    public String getNume() {
        return nume;
    }
}
