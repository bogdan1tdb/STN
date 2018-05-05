package com.stn.pojo;

public class Notes {
    int id=-1;
    int id_ora=-1;
    int id_stud=-1;
    String tema="";
    String examen="";
    int nota=-1;

    public int getId_stud() {
        return id_stud;
    }

    public void setId_stud(int id_stud) {
        this.id_stud = id_stud;
    }

    public int getId_ora() {
        return id_ora;
    }

    public void setId_ora(int id_ora) {
        this.id_ora = id_ora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getExamen() {
        return examen;
    }

    public void setExamen(String examen) {
        this.examen = examen;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }
}
