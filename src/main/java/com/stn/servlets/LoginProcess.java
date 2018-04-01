package com.stn.servlets;

import com.stn.utils.DBConnection;
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
import java.util.Arrays;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {

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
                    }
                } else {
                    error = "Invalid username or password!";
                    url = "login.jsp";
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
