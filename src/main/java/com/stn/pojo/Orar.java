package com.stn.pojo;

public class Orar {
    private int id = -1;
    private int id_grupa = -1;
    private String name = "";
    private String durata = "";
    private String tip_act = "";
    private int sgr = -1;
    private String sala = "";
    private String nume_prof = "";
    private String zi = "";
    private String sapt="";
    private int grupa=-1;

    public String getSapt() {
        return sapt;
    }

    public void setSapt(String sapt) {
        this.sapt = sapt;
    }

    public int getSgr() {
        return sgr;
    }

    public void setSgr(int sgr) {
        this.sgr = sgr;
    }

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_grupa() {
        return id_grupa;
    }

    public void setId_grupa(int id_grupa) {
        this.id_grupa = id_grupa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getTip_act() {
        return tip_act;
    }

    public void setTip_act(String tip_act) {
        this.tip_act = tip_act;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getNume_prof() {
        return nume_prof;
    }

    public void setNume_prof(String nume_prof) {
        this.nume_prof = nume_prof;
    }

    public String getZi() {
        return zi;
    }

    public void setZi(String zi) {
        this.zi = zi;
    }
}


