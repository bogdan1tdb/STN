package com.stn.helpers;

import com.stn.utils.DBConnection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserHelper extends DBConnection{

    public UserHelper() {
        super();
    }

    public void addUser(String username, String password, byte[] salt, String email, String firstName, String lastName) throws ClassNotFoundException, SQLException {
        query = "INSERT INTO users(Username, Password, Salt, Email, FirstName, LastName) VALUES (?,?,?,?,?,?)";

        try {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setBytes(3, salt);
        preparedStatement.setString(4, email);
        preparedStatement.setString(5, firstName);
        preparedStatement.setString(6, lastName);
        preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

    }

    public boolean authenticateUser(String username, String password) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        SecurityHelper securityHelper = new SecurityHelper();
        String dbPassword = ""; // parola din baza de date
        byte[] dbSalt; //salt pentru parola
        String hashedPassword = ""; // parola pe care o introducem si pe care o vom cripta
        Boolean result = false ;
        query = "SELECT Password, Salt FROM users WHERE Username = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //daca am gasit user-ul in baza de date verificam si parola acum
                dbPassword = resultSet.getString(1); // parola (criptata) din baza de date
                dbSalt = resultSet.getBytes(2); // salt-ul din baza de date
                securityHelper.setSalt(dbSalt);
                hashedPassword = securityHelper.getHash(password); // criptam parola pe care am introdus-o
                if (hashedPassword.equals(dbPassword)) { // verificam daca cele 2 hash-uri sunt egale
                    result = true;
                }
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }

        return result;
    }

    public boolean checkAvailability(String username, String email) throws ClassNotFoundException, SQLException {
        Boolean available = true;
        query = "SELECT 1 FROM users WHERE Username = ? OR Email = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                available = false;
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }
        return available;
    }

    public boolean checkEmail(String email) throws ClassNotFoundException, SQLException {
        Boolean found = false;
        query = "SELECT 1 FROM users WHERE Email = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                found = true;
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }
        return found;
    }

    public static void LogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String url = "index.jsp";
        session.invalidate();

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request,response);
    }

    public void updateLastSeen(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");

        java.sql.Timestamp date = new java.sql.Timestamp( (new java.util.Date().getTime()) + 3*3600*1000 );
        query = "UPDATE users SET LastSeen = ? WHERE Username = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, date);
            preparedStatement.setString(2, username);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updatePassword(String email, String password, byte[] salt) throws ClassNotFoundException, SQLException {
        query = "UPDATE users SET Password = ? , Salt = ? WHERE Email = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, password);
            preparedStatement.setBytes(2, salt);
            preparedStatement.setString(3, email);
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

    public void verifyAuthentication(HttpServletRequest request,HttpServletResponse response) {
        if(request.getSession().getAttribute("user") == null) {
            try {
                response.sendRedirect("login.jsp");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
