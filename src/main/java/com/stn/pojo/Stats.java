package com.stn.pojo;

import java.util.List;

public class Stats {

    private int userCount;
    private int facultatiCount;
    private int seriiCount;
    private int grupeCount;

    private int latestUserId;
    private String latestUserUsername;
    private int latestUserUserClass;

    private int students = 0;
    private int sefDeGrupa = 0;
    private int vips = 0;
    private int guestOfHonour = 0;
    private int moderators = 0;
    private int administrators = 0;
    private int owners = 0;

    public void setStats(List<Facultate> facultate, List<Serie> serie, List<Grupa> grupa, List<User> user) {
        userCount = user.size();
        facultatiCount = facultate.size();
        seriiCount = serie.size();
        grupeCount = grupa.size();

        for(User u : user) {
            switch (u.getUserClass()) {
                case 1: students++;
                    break;
                case 2: sefDeGrupa++;
                    break;
                case 3: vips++;
                    break;
                case 4: guestOfHonour++;
                    break;
                case 5: moderators++;
                    break;
                case 6: administrators++;
                    break;
                case 7: owners++;
                    break;
            }

            latestUserId = u.getId();
            latestUserUsername = u.getUserName();
            latestUserUserClass = u.getUserClass();

        }
    }


    public int getUserCount() {
        return userCount;
    }

    public int getFacultatiCount() {
        return facultatiCount;
    }

    public int getSeriiCount() {
        return seriiCount;
    }

    public int getGrupeCount() {
        return grupeCount;
    }

    public int getLatestUserId() {
        return latestUserId;
    }

    public String getLatestUserUsername() {
        return latestUserUsername;
    }

    public int getLatestUserUserClass() {
        return latestUserUserClass;
    }

    public int getStudents() {
        return students;
    }

    public int getSefDeGrupa() {
        return sefDeGrupa;
    }

    public int getVips() {
        return vips;
    }

    public int getGuestOfHonour() {
        return guestOfHonour;
    }

    public int getModerators() {
        return moderators;
    }

    public int getAdministrators() {
        return administrators;
    }

    public int getOwners() {
        return owners;
    }
}
