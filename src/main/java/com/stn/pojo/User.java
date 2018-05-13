package com.stn.pojo;

import java.sql.Timestamp;

public class User {

    private int id = -1;
    private int userClass = 0;
    private String userName = "";
    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private Timestamp joinDate = null;
    private Timestamp lastSeen = null;
    private String avatar = "";
    private String ip = "";
    private String grupa="";
    private String serie="";
    private String facultate="";
    private int idGrupa = 0;
    private int idSerie = 0;
    private int idFacultate = 0;
    private int gender = 0;
    private byte[] salt;
    private String password;
    private int countryId;
    private String countryName;
    private String countryImage;
    private int anonymity;
    private int donor;

    public void setId(int id) {
        this.id = id;
    }

    public void setUserClass(int userClass) {
        this.userClass = userClass;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setJoinDate(Timestamp joinDate) {
        this.joinDate = joinDate;
    }

    public void setLastSeen(Timestamp lastSeen) {
        this.lastSeen = lastSeen;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
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

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public void setCountryImage(String countryImage) {
        this.countryImage = countryImage;
    }

    public void setAnonymity(int anonymity) {
        this.anonymity = anonymity;
    }

    public void setDonor(int donor) {
        this.donor = donor;
    }

    public int getId() {
        return id;
    }

    public int getUserClass() {
        return userClass;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Timestamp getJoinDate() {
        return joinDate;
    }

    public Timestamp getLastSeen() {
        return lastSeen;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getIp() {
        return ip;
    }

    public String getGrupa() {
        return grupa;
    }

    public String getSerie() {
        return serie;
    }

    public String getFacultate() {
        return facultate;
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

    public int getGender() {
        return gender;
    }

    public int getCountryId() {
        return countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryImage() {
        return countryImage;
    }

    public int getAnonymity() {
        return anonymity;
    }

    public int getDonor() {
        return donor;
    }

    public byte[] getSalt() {
        return salt;
    }

    public String getPassword() {
        return password;
    }
}
