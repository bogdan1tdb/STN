package com.stn.helpers;

import com.stn.pojo.Notes;
import com.stn.pojo.Orar;
import com.stn.utils.DBConnection;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrarHelper extends DBConnection {

    public Orar getOra(Integer id_ora, ArrayList<Orar> ore){
        Orar ora=new Orar();
        for (int i = 0; i < ore.size() ; i++) {
            if(ore.get(i).getId()==id_ora)
            { ora=ore.get(i);
                break;
            }
        }
        return ora;
    }

    public ArrayList<Orar> getOrar(Integer id_grupa) throws SQLException, ClassNotFoundException {
        ArrayList<Orar> orar = new ArrayList<Orar>();

        query = "Select * from ore where IdGrupa=?  \n" +
                "ORDER BY `ore`.`Zi` ASC, `ore`.`Durata` ASC";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_grupa);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Orar ora = new Orar();
                ora.setId(resultSet.getInt(1));
                ora.setId_grupa(resultSet.getInt(2));
                ora.setName(resultSet.getString(3));
                ora.setDurata(resultSet.getString(4));
                ora.setTip_act(resultSet.getString(5));
                ora.setSgr(resultSet.getInt(6));
                ora.setSala(resultSet.getString(7));
                ora.setNume_prof(resultSet.getString(8));
                ora.setZi(resultSet.getString(9));
                ora.setGrupa(resultSet.getInt(10));
                ora.setSapt(resultSet.getString(11));
                orar.add(ora);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return orar;
    }

    public Integer getGrupaOra(Integer id_user) {
        Integer id_grupa = null;
        query = "SELECT IdGrupa from users where Id=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_user);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id_grupa = resultSet.getInt(1);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return id_grupa;
    }

    public void addOra(Integer id_gr, String nume, String durata, String tip_act, Integer semig, String sala, String nume_prof, String zi, Integer grupa,String sapt) throws SQLException, ClassNotFoundException {
        query = "INSERT INTO ore(IdGrupa,Nume,Durata,TipActivitate,Semigrupa,Sala,NumeProfesor,Zi,Grupa,Saptamana) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_gr);
            preparedStatement.setString(2, nume);
            preparedStatement.setString(3, durata);
            preparedStatement.setString(4, tip_act);
            preparedStatement.setInt(5, semig);
            preparedStatement.setString(6, sala);
            preparedStatement.setString(7, nume_prof);
            preparedStatement.setString(8, zi);
            preparedStatement.setInt(9, grupa);
            preparedStatement.setString(10,sapt);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void deleteOra(Integer id_ora) throws ClassNotFoundException, SQLException {

        query=" DELETE FROM `ore` WHERE `ore`.`Id` = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id_ora);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void addNotes(Integer id_grupa,Integer id_student,String nume,String durata,String tip_act,Integer sem,String sala,String numep,String zi,Integer gr,String sapt) throws SQLException, ClassNotFoundException {

        int id_ora=-1;
        query="select Id from ore where `IdGrupa`=? and `Nume`=? and `Durata`=? and `TipActivitate`=? and `Semigrupa`=? and `Sala`=? and `NumeProfesor`=? and `Zi`=? and `Grupa`=? and Saptamana=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(this.getHost(),this.getUser(),this.getPassword());
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id_grupa);
            preparedStatement.setString(2,nume);
            preparedStatement.setString(3,durata);
            preparedStatement.setString(4,tip_act);
            preparedStatement.setInt(5,sem);
            preparedStatement.setString(6,sala);
            preparedStatement.setString(7,numep);
            preparedStatement.setString(8,zi);
            preparedStatement.setInt(9,gr);
            preparedStatement.setString(10,sapt);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) {
                id_ora=resultSet.getInt(1);
            }
        }finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }

            query="insert into notes(IdOra,IdStudent,Teme,Examen,Nota) values (?,?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection(this.getHost(),this.getUser(),this.getPassword());
            preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,id_ora);
            preparedStatement.setInt(2,id_student);
            preparedStatement.setString(3,"");
            preparedStatement.setString(4,"");
            preparedStatement.setInt(5,0);
            preparedStatement.executeUpdate();
        }finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }
    }

    public void updateNotes(Integer id_ora,Integer id_stud,String teme, String examen, Integer nota) throws SQLException, ClassNotFoundException {
        query = "update notes set Teme=?,Examen=?,Nota=? where IdOra=? and IdStudent=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teme);
            preparedStatement.setString(2, examen);
            preparedStatement.setInt(3, nota);
            preparedStatement.setInt(4, id_ora);
            preparedStatement.setInt(5,id_stud);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();

        }
    }

    public Notes getSchool(int id_ora,int id_student) throws SQLException, ClassNotFoundException {
        Notes notes = new Notes();
        query = "Select * from notes where IdOra=? and IdStudent=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_ora);
            preparedStatement.setInt(2, id_student);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                notes.setId(resultSet.getInt(1));
                notes.setId_ora(resultSet.getInt(2));
                notes.setId_stud(resultSet.getInt(3));
                notes.setTema(resultSet.getString(4));
                notes.setExamen(resultSet.getString(5));
                notes.setNota(resultSet.getInt(6));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return notes;
    }

    public ArrayList<Notes> getSchools(Integer id_ora) throws SQLException, ClassNotFoundException {
        ArrayList<Notes> notes = new ArrayList<>();

        query = "Select * from notes where IdOra=?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id_ora);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Notes note=new Notes();
                note.setId(resultSet.getInt(1));
                note.setId_ora(resultSet.getInt(2));
                note.setId_stud(resultSet.getInt(3));
                note.setTema(resultSet.getString(4));
                note.setExamen(resultSet.getString(5));
                note.setNota(resultSet.getInt(6));
                notes.add(note);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
        return notes;
    }
}
