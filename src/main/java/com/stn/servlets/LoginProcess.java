package com.stn.servlets;

import com.stn.utils.DBConnection;
import com.stn.utils.IPHelper;
import com.stn.utils.LoginHelper;
import com.stn.utils.PasswordHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {

    private void updateAttempts(String ip) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBConnection db = new DBConnection();
        String query  = "UPDATE failed_logins SET Attempts = Attempts + 1 WHERE Ip=?;";
        Integer affected;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ip);
            affected = preparedStatement.executeUpdate();
            if(affected == 0)
            {
                long day = 24 * 60 * 60 * 1000;
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime() + day);
                query  = "INSERT INTO failed_logins (Ip,Attempts,ExpireDate) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ip);
                preparedStatement.setInt(2, 1);
                preparedStatement.setTimestamp(3, date);
                preparedStatement.executeUpdate();
            }
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error ="";
        String url = "index.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        String dbPassword = "";
        String encryptedPassword = "";
        byte[] salt = null;

        if(user.isEmpty() || password.isEmpty()) {
            error = "Fill all the requiered spaces!";
            url = "login.jsp";
        } else if(LoginHelper.getAttempts(request) > 9) {
            error = "You are out of login attempts!";
            url = "login.jsp";
        } else {
            PreparedStatement preparedStatement = null;
            Connection connection = null;
            DBConnection db = new DBConnection();
            String query = "SELECT Password, Salt FROM users WHERE Username = ?";
            PasswordHelper passwordHelper = new PasswordHelper();
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,user);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    dbPassword = rs.getString(1);
                    salt = rs.getBytes(2);
                    passwordHelper.setSalt(salt);
                    encryptedPassword = passwordHelper.getPassword(password);
                    if(encryptedPassword.equals(dbPassword)) {
                        session.invalidate();
                        session=request.getSession(true);
                        session.setAttribute("user", user);
                    } else {
                        error = "Invalid username or password!";
                        url = "login.jsp";
                        this.updateAttempts(IPHelper.getClientIpAddress(request));
                    }
                } else {
                    error = "Invalid username or password!";
                    url = "login.jsp";
                    this.updateAttempts(IPHelper.getClientIpAddress(request));
                }

            } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
                out.println(e);
                return;
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                    if (connection != null)
                        connection.close();
                    if (rs != null)
                        rs.close();
                } catch (SQLException e) {
                    out.println(e);
                }
            }
        }

       request.setAttribute("error", error);
       RequestDispatcher dispatcher = request.getRequestDispatcher(url);
       dispatcher.forward(request, response);
    }
}
