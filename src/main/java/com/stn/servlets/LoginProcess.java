package com.stn.servlets;

import com.stn.utils.DBConnection;
import com.stn.utils.IPHelper;
import com.stn.utils.PasswordHelper;

import javax.print.DocFlavor;
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

    private static final String[] IP_HEADER_CANDIDATES = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_X_FORWARDED_FOR",
            "HTTP_X_FORWARDED",
            "HTTP_X_CLUSTER_CLIENT_IP",
            "HTTP_CLIENT_IP",
            "HTTP_FORWARDED_FOR",
            "HTTP_FORWARDED",
            "HTTP_VIA",
            "REMOTE_ADDR" };

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    private void updateAttempts(String ip) {

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBConnection db = new DBConnection();
        String query  = "IF EXISTS (SELECT * FROM failed_logins WHERE Ip = ?) " +
                        "  THEN UPDATE failed_logins SET Attempts = Attempts + 1 WHERE Ip = ?" +
                        "ELSE " +
                        "  INSERT INTO failed_logins (Ip,Attempts,Expire_date) VALUES(?,?,?)";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ip);
            preparedStatement.setString(2, ip);
            preparedStatement.setInt(3, 1);
            preparedStatement.setInt(4, 0);
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
                        this.updateAttempts(this.getClientIpAddress(request));
                    }
                } else {
                    error = "Invalid username or password!";
                    url = "login.jsp";
                    this.updateAttempts(this.getClientIpAddress(request));
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
