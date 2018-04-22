package com.stn.helpers;

import com.stn.pojo.Facultate;
import com.stn.pojo.Grupa;
import com.stn.pojo.Serie;
import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacultateHelper extends DBConnection {

    public void addFacultate(String name) throws ClassNotFoundException, SQLException {
        query = "INSERT INTO facultati (Nume) VALUES (?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void addSerie(String name,int idFacultate) throws ClassNotFoundException, SQLException {
        query = "INSERT INTO serii (Nume,IdFacultate) VALUES (?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idFacultate);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void addGrupa(String name,int idSerie) throws ClassNotFoundException, SQLException {
        query = "INSERT INTO grupe (Nume,IdSerie,Token) VALUES (?,?,?)";

        SecurityHelper securityHelper = new SecurityHelper();
        String token = securityHelper.generateRandomString(32);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, idSerie);
            preparedStatement.setString(3, token);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<Facultate> findFacultate(String name) throws ClassNotFoundException, SQLException {
        List<Facultate> facultate = new ArrayList<Facultate>();
        name = "%"+name+"%";

        query = "SELECT * FROM facultati WHERE LOWER(Nume) Like LOWER(?) LIMIT 15";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                facultate.add(new Facultate(resultSet.getInt(1),resultSet.getString(2)));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return facultate;
    }

    public List<Facultate> getFacultati() throws ClassNotFoundException, SQLException {
        List<Facultate> facultate = new ArrayList<Facultate>();

        query = "SELECT * FROM facultati";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                facultate.add(new Facultate(resultSet.getInt(1),resultSet.getString(2)));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return facultate;
    }

    public List<Serie> getSerii() throws ClassNotFoundException, SQLException {
        List<Serie> serie = new ArrayList<Serie>();

        query = "SELECT * FROM serii";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                serie.add(new Serie(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return serie;
    }

    public List<Grupa> getGrupe() throws ClassNotFoundException, SQLException {
        List<Grupa> grupa = new ArrayList<Grupa>();

        query = "SELECT * FROM grupe";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                grupa.add(new Grupa(resultSet.getInt(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return grupa;
    }

    public static String generateOptions(List<Facultate> facultate) {

        String result = "<select name='facultate' onchange='addTabelSerie(this)'>";
        result = result + "<option disabled selected value>--</option>";

        if(facultate.size() > 0) {
            for (Facultate fac : facultate) {
                result = result + "<option value='" + fac.getIdFacultate() + "'>" + fac.getNume() + "</option>";
            }
        }

        result = result + "</select>";
        return  result;
    }

    public static String generateOptions2(List<Facultate> facultate) {

        String result = "<select name='facultate' onchange='addSelectSerie(this)'>";
        result = result + "<option disabled selected value>--</option>";

        if(facultate.size() > 0) {
            for (Facultate fac : facultate) {
                result = result + "<option value='" + fac.getIdFacultate() + "'>" + fac.getNume() + "</option>";
            }
        }

        result = result + "</select>";
        return  result;
    }

    public String getTokenGrupa(int id) throws SQLException, ClassNotFoundException {
        String token = "";

        query = "SELECT token FROM grupe WHERE IdGrupa = ?";


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                token = resultSet.getString(1);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return token;
    }

    public int checkToken(String token) throws SQLException, ClassNotFoundException {
        int id = -1;

        query = "SELECT IdGrupa FROM grupe WHERE token = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,token);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return id;
    }

}
