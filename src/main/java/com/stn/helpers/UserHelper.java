package com.stn.helpers;

import com.stn.pojo.User;
import com.stn.utils.DBConnection;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UserHelper extends DBConnection{

    private static final String[] guestAcces = {
            "apply.jsp",
            "login.jsp",
            "recover.jsp",
            "register.jsp",
            "reset.jsp",
            "terms.jsp" };

    private static final String[] userAcces = {
            "index.jsp",
            "wiki.jsp",
            ""};

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

    public int authenticateUser(String username, String password) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
        SecurityHelper securityHelper = new SecurityHelper();
        String dbPassword = ""; // parola din baza de date
        byte[] dbSalt; //salt pentru parola
        String hashedPassword = ""; // parola pe care o introducem si pe care o vom cripta
        int id = -1 ;
        query = "SELECT Id, Password, Salt FROM users WHERE Username = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) { //daca am gasit user-ul in baza de date verificam si parola acum
                dbPassword = resultSet.getString(2); // parola (criptata) din baza de date
                dbSalt = resultSet.getBytes(3); // salt-ul din baza de date
                securityHelper.setSalt(dbSalt);
                hashedPassword = securityHelper.getHash(password); // criptam parola pe care am introdus-o
                if (hashedPassword.equals(dbPassword)) { // verificam daca cele 2 hash-uri sunt egale
                    id = resultSet.getInt(1);
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

        return id;
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

    public int checkLoginToken(String token, String ip) throws ClassNotFoundException, SQLException {
        int id = -1 ;
        query = "SELECT Id FROM users WHERE LoginToken = ? AND Ip = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,token);
            preparedStatement.setString(2,ip);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }

        return id;

    }

    public static String classColor(int userClass) {
        String color = "";
        switch (userClass) {
            case 1: color = "white";
                break;
            case 2: color = "#b52db5";
                break;
            case 3: color = "#089f00";
                break;
            case 4: color = "#ffa00b";
                break;
            case 5: color = "#5d56ef";
                break;
            case 6: color = "#ff0026";
                break;
            case 7: color = "#A83838";
                break;
        }
        return color;
    }

    public User getUserInfo(int id) {
        User user = new User();
        query = "SELECT Username, Email, FirstName, LastName, JoinDate, LastSeen, Class FROM users WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setUserName(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setJoinDate(resultSet.getTimestamp(5));
                user.setLastSeen(resultSet.getTimestamp(6));
                user.setUserClass(resultSet.getInt(7));
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
        return user;
    }

    public void updateLastSeen(int id) {

        java.sql.Timestamp date = new java.sql.Timestamp( (new java.util.Date().getTime()) + 3*3600*1000 );
        query = "UPDATE users SET LastSeen = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setTimestamp(1, date);
            preparedStatement.setInt(2,id);
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

    public void updateLoginToken(int id, String token, String ip) throws ClassNotFoundException, SQLException {
        query = "UPDATE users SET LoginToken = ? , Ip = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, token);
            preparedStatement.setString(2, ip);
            preparedStatement.setInt(3, id);
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

    public static void verifyAcces(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if(session.getAttribute("userclass") == null) {
            session.setAttribute("userclass", 0);
        }

        String uri = request.getRequestURI();
        String pageName = uri.substring(uri.lastIndexOf("/")+1);

        try {
            for (String page : guestAcces) {
                if(page.equals(pageName) && (int) session.getAttribute("userclass") > 0) {
                    response.sendRedirect("index.jsp");
                }
            }

            for (String page : userAcces) {
                if(page.equals(pageName) && (int) session.getAttribute("userclass") < 1) {
                    response.sendRedirect("login.jsp");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void verifyAuthentication(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        SecurityHelper securityHelper = new SecurityHelper();
        UserHelper userHelper = new UserHelper();
        String ip = securityHelper.getClientIpAddress(request);
        int id = -1;
        String token = "";

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }

        if( session.getAttribute("userId") == null || (int)session.getAttribute("userId") <= 0)
        {
            if (token != null) {
                try {
                    id = userHelper.checkLoginToken(token,ip);
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
                if(id > 0) {
                    session.setAttribute("userId", id);
                }
            }
        }
    }
}
