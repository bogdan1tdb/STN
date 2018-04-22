package com.stn.helpers;

import com.stn.pojo.Aplicatie;
import com.stn.pojo.Invitatie;
import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public List<Aplicatie> getAplicatii() throws ClassNotFoundException, SQLException {
        List<Aplicatie> aplicatie = new ArrayList<Aplicatie>();

        query = "SELECT * FROM applications";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                int appId = resultSet.getInt(1);
                String nume = resultSet.getString(2);
                String prenume = resultSet.getString(3);
                String facultate = resultSet.getString(4);
                String serie = resultSet.getString(5);
                String grupa = resultSet.getString(6);
                String email = resultSet.getString(7);
                String document = resultSet.getString(8);
                Timestamp date = resultSet.getTimestamp(9);
                int type = resultSet.getInt(10);
                aplicatie.add(new Aplicatie(appId,nume,prenume,facultate,serie,grupa,email,document,date,type));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return aplicatie;
    }

    public void uptateApplicationStatus(int id, int type) throws ClassNotFoundException, SQLException {
        query = "UPDATE applications SET Type = ? WHERE AppId = ? ";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,type);
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

    }

    public void createInvite(String token, int idGrupa, int idSerie, int idFacultate, String email, int userClass) throws SQLException, ClassNotFoundException {
        long day = 24 * 60 * 60 * 1000;
        java.sql.Timestamp expDate = new java.sql.Timestamp(new java.util.Date().getTime() + ( 7 * day) );

        query = "INSERT INTO invites(Token,Email,idGrupa,idSerie,idFacultate,Class,ExpDate) VALUES (?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, email);
            preparedStatement.setInt(3, idGrupa);
            preparedStatement.setInt(4, idSerie);
            preparedStatement.setInt(5, idFacultate);
            preparedStatement.setInt(6, userClass);
            preparedStatement.setTimestamp(7, expDate);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public Invitatie getInvitatie(String token) throws ClassNotFoundException, SQLException {
        Invitatie invitatie = null;

        query = "SELECT * FROM invites WHERE token = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idInvitatie = resultSet.getInt(1);
                token = resultSet.getString(2);
                String email = resultSet.getString(3);
                int idGrupa = resultSet.getInt(4);
                int idSerie = resultSet.getInt(5);
                int idFacultate = resultSet.getInt(6);
                int userClass = resultSet.getInt(7);
                Timestamp expDate = resultSet.getTimestamp(8);
                invitatie = new Invitatie(idInvitatie, token, email, idGrupa, idSerie, idFacultate, userClass, expDate);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return invitatie;
    }

    public void deleteInvitatie(int id) throws SQLException, ClassNotFoundException {
        query = "DELETE FROM invites WHERE IdInvitatie = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

    }

}
