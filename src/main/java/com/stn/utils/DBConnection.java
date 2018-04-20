package com.stn.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConnection {
    private String dbHost;
    private String dbName;
    private String dbUser;
    private String dbPassword;
    private String dbPort;

    protected String query;

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    //Se utilizeaza pentru construirea unui query
    public DBConnection() {
        this.dbHost = "165.227.172.85";
        this.dbName = "stn_db";
        this.dbUser = "MaD";
        this.dbPassword = "super123#";
        this.dbPort = "3306";
        this.preparedStatement = null;
        this.connection = null;
        this.resultSet = null;
        this.query = "";
    }

    public String getHost() {
        return "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName+"?useSSL=false";
    }

    public String getUser() {
        return dbUser;
    }

    public String getPassword() {
        return dbPassword;
    }
}
