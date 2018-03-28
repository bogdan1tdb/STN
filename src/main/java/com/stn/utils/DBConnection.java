package com.stn.utils;

public class DBConnection {
    private String dbHost;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String dbPort;

    public DBConnection() {
        this.dbHost = "johnny.heliohost.org";
        this.dbName = "mad_stn";
        this.dbUser = "mad_stnuser";
        this.dbPassword = "super767&&";
        this.dbPort = "3306";
    }

    public String getHost() {
        return "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
    }

    public String getUser() {
        return dbUser;
    }

    public String getPassword() {
        return dbPassword;
    }
}
