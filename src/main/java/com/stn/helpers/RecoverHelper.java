package com.stn.helpers;

import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class RecoverHelper extends DBConnection {

    public void insertToken(String email, String token) throws ClassNotFoundException, SQLException {
        query = "INSERT INTO password_reset(Token, Email, ExpireDate) VALUES (?,?,?)";
        long day = 24 * 60 * 60 * 1000;
        java.sql.Timestamp expDate = new java.sql.Timestamp(new java.util.Date().getTime() + ( 3 * day) );

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

}
