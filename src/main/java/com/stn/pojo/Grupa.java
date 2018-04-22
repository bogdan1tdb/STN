package com.stn.pojo;

public class Grupa {

    private int idGrupa;
    private String nume;
    private int idSerie;

    public Grupa(int idGrupa, String nume, int idSerie) {
        this.idGrupa = idGrupa;
        this.nume = nume;
        this.idSerie = idSerie;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public String getNume() {
        return nume;
    }

    public int getIdSerie() {
        return idSerie;
    }
}
