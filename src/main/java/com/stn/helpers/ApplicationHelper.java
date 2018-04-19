package com.stn.helpers;

import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ApplicationHelper extends DBConnection {

    public void addApplication (String email, String firstName, String lastName, String facultate, String serie, String grupa, String document) throws SQLException, ClassNotFoundException {
        query = "INSERT INTO applications(Nume,Prenume,Facultate,Serie,Grupa,Email,Document) VALUES (?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3,facultate);
            preparedStatement.setString(4,serie);
            preparedStatement.setString(5,grupa);
            preparedStatement.setString(6,email);
            preparedStatement.setString(7,document);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

}
