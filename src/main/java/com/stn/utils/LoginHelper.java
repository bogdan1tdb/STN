package com.stn.utils;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;

public class LoginHelper {

    public static int getAttempts(HttpServletRequest request) {
        PreparedStatement preparedStatement = null;
        Connection connection = null;
        DBConnection db = new DBConnection();
        String query = "SELECT Attempts,ExpireDate FROM failed_logins WHERE Ip = ?";

        ResultSet rs = null;
        String ip = IPHelper.getClientIpAddress(request);
        int attempts;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,ip);
            rs = preparedStatement.executeQuery();
            if(!rs.next()) {
                return 0;
            } else {
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                Timestamp expireDate = rs.getTimestamp("ExpireDate");
                if(expireDate.before(date)) {
                    query = "DELETE FROM failed_logins WHERE Ip = ? ";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,ip);
                    preparedStatement.executeUpdate();
                    return 0;
                } else {
                    attempts = rs.getInt("Attempts");
                    return attempts;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
                if (rs != null)
                    rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
