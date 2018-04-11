package com.stn.pojo;

import java.sql.Timestamp;

public class User {

    private int userClass = 0;
    private String userName = "";
    private String email = "";
    private String firstName = "";
    private String lastName = "";
    private Timestamp joinDate= null;
    private Timestamp lastSeen= null;

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
}
