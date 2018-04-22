package com.stn.pojo;

public class Serie {

    private int idSerie;
    private String nume;
    private int idFacultate;

    public Serie(int idSerie, String nume, int idFacultate) {
        this.idSerie = idSerie;
        this.nume = nume;
        this.idFacultate = idFacultate;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setIdFacultate(int idFacultate) {
        this.idFacultate = idFacultate;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public String getNume() {
        return nume;
    }

    public int getIdFacultate() {
        return idFacultate;
    }
}
