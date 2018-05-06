package com.stn.helpers;

import com.stn.pojo.News;
import com.stn.utils.DBConnection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class NewsHelper extends DBConnection {

    public void addNews(String title, String body, int idUser, int idSerie) throws SQLException, ClassNotFoundException {
        query = "INSERT INTO news_serie(Title,Body,IdUser,IdSerie) VALUES (?,?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, body);
            preparedStatement.setInt(3, idUser);
            preparedStatement.setInt(4, idSerie);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public List<News> getNews(int idSerie, int limit) throws ClassNotFoundException, SQLException {
        List<News> news = new ArrayList<News>();

        if(limit == -1) {
            query = "SELECT IdNews,Title,Body,Date,n.IdUser,n.IdSerie,Username,Class,LastEdit FROM news_serie n " +
                    "JOIN users u ON u.Id = n.IdUser " +
                    "WHERE n.idSerie = ? ORDER BY IdNews DESC";
        } else {
            query = "SELECT IdNews,Title,Body,Date,n.IdUser,n.IdSerie,Username,Class,LastEdit FROM news_serie n " +
                    "JOIN users u ON u.Id = n.IdUser " +
                    "WHERE n.idSerie = ? ORDER BY IdNews DESC LIMIT ?";
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idSerie);
            if(limit != -1) {
                preparedStatement.setInt(2, limit);
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                News newstemp = new News();
                newstemp.setIdNews(resultSet.getInt(1));
                newstemp.setTitle(resultSet.getString(2));
                newstemp.setBody(resultSet.getString(3));
                newstemp.setDate(resultSet.getTimestamp(4));
                newstemp.setIdUser(resultSet.getInt(5));
                newstemp.setIdSerie(resultSet.getInt(6));
                newstemp.setUsername(resultSet.getString(7));
                newstemp.setUserClass(resultSet.getInt(8));
                newstemp.setLastEdit(resultSet.getTimestamp(9));
                news.add(newstemp);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return news;
    }

    public void deleteNews(int idNews) throws SQLException, ClassNotFoundException {
        query = "DELETE FROM news_serie WHERE IdNews = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idNews);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void updateBody(int idNews, String body) throws SQLException, ClassNotFoundException {
        java.sql.Timestamp date = new java.sql.Timestamp( (new java.util.Date().getTime()) );
        query = "UPDATE news_serie SET Body = ? , LastEdit = ? WHERE IdNews = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, body);
            preparedStatement.setTimestamp(2,date);
            preparedStatement.setInt(3, idNews);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void updateTitle(int idNews, String title) throws SQLException, ClassNotFoundException {
        java.sql.Timestamp date = new java.sql.Timestamp( (new java.util.Date().getTime()) );
        query = "UPDATE news_serie SET Title = ? , LastEdit = ? WHERE IdNews = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setTimestamp(2,date);
            preparedStatement.setInt(3, idNews);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

}
