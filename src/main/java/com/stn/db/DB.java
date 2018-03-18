package com.stn.db;

import java.sql.*;

public class DB {

    ResultSet records = null;
    Integer rows = 0;
    Integer queryID = 0;

    String dbHost;
    String dbName;
    String dbUser;
    String dbPassword;
    String dbPort;

    Connection connection = null;
    PreparedStatement prepQuery = null;

    public DB() {
        this.dbHost = "sql11.freesqldatabase.com";
        this.dbName = "sql11227166";
        this.dbUser = "sql11227166";
        this.dbPassword = "74LzvYYNkH";
        this.dbPort = "3306";
    }

    public DB(String host, String name, String user, String password, String port) {
        this.dbHost = host;
        this.dbName = name;
        this.dbUser = user;
        this.dbPassword = password;
        this.dbPort = port;
    }

    public Connection getConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName,dbUser,dbPassword);
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return this.connection;
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getQueryID() {
        return queryID;
    }

    public void reloadRecords() {
        try {
            records.beforeFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getString(Integer i) {
        try {
            return records.getString(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Integer getInt(Integer i) {
        try {
            return records.getInt(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean next()  {
        try {
            if(records.next())
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public PreparedStatement prepare(Connection connection, String query, Object ... args) {
        Integer i = 1;
        PreparedStatement q = null;
        try {
            q = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (Object arg : args) {
                q.setString(i,"" +arg);
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return q;
    }

    public void query(String query, Object ... args) {
        this.connection = getConn();
        try {
            this.prepQuery = prepare(connection, query, args);
            this.records = prepQuery.executeQuery();
            if (records.last()) {
                rows = records.getRow();
                records.beforeFirst();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            this.prepQuery = prepare(connection, query, args);
            this.rows = prepQuery.executeUpdate();
            ResultSet keys = prepQuery.getGeneratedKeys();
            keys.next();
            this.queryID = keys.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if(prepQuery != null)
                prepQuery.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if(connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}