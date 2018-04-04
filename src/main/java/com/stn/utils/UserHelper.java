package com.stn.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserHelper {
    public static void updateLastSeen(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("user");

        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBConnection db = new DBConnection();

        java.sql.Timestamp date = new java.sql.Timestamp( (new java.util.Date().getTime()) + 3*3600*1000 );
        String query = "UPDATE users SET LastSeen = ? WHERE Username = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
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
}
