package com.stn.helpers;

import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RecoverHelper extends DBConnection {

    public void insertToken(String email, String token) throws ClassNotFoundException, SQLException {
        long day = 24 * 60 * 60 * 1000;
        java.sql.Timestamp expDate = new java.sql.Timestamp(new java.util.Date().getTime() + ( 3 * day) );

        query = "INSERT INTO password_reset(Token, Email, ExpireDate) VALUES (?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, email);
            preparedStatement.setTimestamp(3, expDate);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public String checkToken(String token) throws ClassNotFoundException, SQLException {
        String email = null;

        query = "SELECT Email FROM password_reset WHERE Token = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,token);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString("Email");
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }

        return email;
    }

    public void deleteToken(String token) throws ClassNotFoundException, SQLException {
        query = "DELETE FROM password_reset WHERE Token = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }
    }

}
