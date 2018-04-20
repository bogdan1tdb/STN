package com.stn.helpers;

import com.stn.utils.DBConnection;

import javax.servlet.http.HttpServletRequest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;

public class SecurityHelper extends DBConnection{

    private String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private SecureRandom rnd = new SecureRandom();
    private byte[] salt;

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

    public String getHash(String passwordToHash) {
        String generatedHash= null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedHash = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedHash;
    }

    public String generateRandomString(int len){
        StringBuilder sb = new StringBuilder(len);
        for(int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    public void generateSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        this.salt = salt;
    }

    public static String getClientIpAddress(HttpServletRequest request) {
        for (String header : IP_HEADER_CANDIDATES) {
            String ip = request.getHeader(header);
            if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
                return ip;
            }
        }
        return request.getRemoteAddr();
    }

    public int getAttempts(HttpServletRequest request) {
        String ip = getClientIpAddress(request);
        int attempts;

        query = "SELECT Attempts,ExpireDate FROM failed_logins WHERE Ip = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,ip);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next()) {
                return 0;
            } else {
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                Timestamp expireDate = resultSet.getTimestamp("ExpireDate");
                if(expireDate.before(date)) {
                    query = "DELETE FROM failed_logins WHERE Ip = ? ";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1,ip);
                    preparedStatement.executeUpdate();
                    return 0;
                } else {
                    attempts = resultSet.getInt("Attempts");
                    return attempts;
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateAttempts(String ip) throws SQLException, ClassNotFoundException {
        int affected;

        query  = "UPDATE failed_logins SET Attempts = Attempts + 1 WHERE Ip=?;";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ip);
            affected = preparedStatement.executeUpdate(); //affected = numarul de randuri care au fost actualizate
            if(affected == 0) //daca nu exista deja ip-ul baza de date,il introducem noi
            {
                long day = 24 * 60 * 60 * 1000;
                java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime() + day); //ziua urmatoare din momentul in care am initializat date
                query  = "INSERT INTO failed_logins (Ip,Attempts,ExpireDate) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, ip);
                preparedStatement.setInt(2, 1);
                preparedStatement.setTimestamp(3, date);
                preparedStatement.executeUpdate();
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getSalt() {
        return salt;
    }

}
