package com.stn.helpers;

import com.stn.pojo.Aplicatie;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
            "userdetails.jsp",
            "group.jsp",
            "invite.jsp",
            "edit_profile.jsp",
            "news_archive.jsp",
            "bbcode_legend.jsp",
            ""};

    private static final String[] modAcces = {
            "cpanel.jsp"};

    private static final String[] adminAcces = {
            "applications.jsp",
            "editfacultati.jsp"};

    public UserHelper() {
        super();
    }

    public void addUser(String username, String password, byte[] salt, String email, String firstName, String lastName, int idGrupa, int idSerie, int idFacultate, int userClass) throws ClassNotFoundException, SQLException {
        if(idGrupa != 0) {
            query = "INSERT INTO users(Username, Password, Salt, Email, FirstName, LastName, IdGrupa, IdSerie, IdFacultate, Class) VALUES (?,?,?,?,?,?,?,?,?,?)";
        } else {
            query = "INSERT INTO users(Username, Password, Salt, Email, FirstName, LastName) VALUES (?,?,?,?,?,?)";
        }

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
        if(idGrupa != 0) {
            preparedStatement.setInt(7,idGrupa);
            preparedStatement.setInt(8,idSerie);
            preparedStatement.setInt(9,idFacultate);
            preparedStatement.setInt(10,userClass);
        }
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

        query = "SELECT Id, Password, Salt, Class FROM users WHERE Username = ?";

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
                if(resultSet.getInt(4) == 0 ){
                    id = 0;
                } else if (hashedPassword.equals(dbPassword)) { // verificam daca cele 2 hash-uri sunt egale
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
            case 0: color = "white";
                break;
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

    public static String className(int userClass) {
        String name = "";
        switch (userClass) {
            case 0: name = "Disabled";
                break;
            case 1: name = "Student";
                break;
            case 2: name = "Sef de Grupa";
                break;
            case 3: name = "VIP";
                break;
            case 4: name = "Guest of Honour";
                break;
            case 5: name = "Moderator";
                break;
            case 6: name = "Administrator";
                break;
            case 7: name = "Owner";
                break;
        }
        return name;
    }

    public static String genderName(int gender) {
        String name = "";
        switch (gender) {
            case 0: name = "Unknown";
                break;
            case 1: name = "Male";
                break;
            case 2: name = "Female";
                break;
        }
        return name;
    }

    public static String genderImage(int gender) {
        String url = "";
        switch (gender) {
            case 0: url = "";
                break;
            case 1: url = "<img src='/img/male.png' title='Male' style='vertical-align: middle'>";
                break;
            case 2: url = "<img src='/img/female.png' title='Female' style='vertical-align: middle'>";
                break;
        }
        return url;
    }

    public void deleteToken(int id) {
        query = "UPDATE users SET LoginToken = '' WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
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

    public void deleteGrupa(int id) throws ClassNotFoundException, SQLException {
        query = "UPDATE users SET IdGrupa = ? , IdSerie = ? , IdFacultate = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setNull(1,java.sql.Types.INTEGER);
            preparedStatement.setNull(2,java.sql.Types.INTEGER);
            preparedStatement.setNull(3,java.sql.Types.INTEGER);
            preparedStatement.setInt(4,id);
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }
    }

    public User getUserInfo(int id) {
        User user = new User();
        String avatarURL;
        String grupa;
        String serie;
        String facultate;

        query = "SELECT Username, Email, FirstName, LastName, JoinDate, LastSeen, Class, Avatar, Ip , g.Nume, s.Nume, f.Nume, u.IdGrupa, u.IdSerie, u.IdFacultate, Gender " +
                "FROM users u LEFT JOIN grupe g ON u.IdGrupa = g.IdGrupa LEFT JOIN serii s ON u.IdSerie = s.IdSerie " +
                "LEFT JOIN facultati f ON u.IdFacultate = f.IdFacultate " +
                "WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user.setId(id);
                user.setUserName(resultSet.getString(1));
                user.setEmail(resultSet.getString(2));
                user.setFirstName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setJoinDate(resultSet.getTimestamp(5));
                user.setLastSeen(resultSet.getTimestamp(6));
                user.setUserClass(resultSet.getInt(7));
                avatarURL = resultSet.getString(8);
                if(avatarURL.equals("")) {
                    user.setAvatar("img/profile.png");
                } else {
                    user.setAvatar(avatarURL);
                }
                user.setIp(resultSet.getString(9));
                grupa = resultSet.getString(10);
                serie = resultSet.getString(11);
                facultate = resultSet.getString(12);
                if(grupa != null && serie != null && facultate != null) {
                    user.setGrupa(grupa);
                    user.setSerie(serie);
                    user.setFacultate(facultate);
                    user.setIdGrupa(resultSet.getInt(13));
                    user.setIdSerie(resultSet.getInt(14));
                    user.setIdFacultate(resultSet.getInt(15));
                } else {
                    user.setGrupa("Not set");
                    user.setSerie("Not set");
                    user.setFacultate("Not set");
                }
                user.setGender(resultSet.getInt(16));
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

    public User getPassword(int id) throws SQLException, ClassNotFoundException {
        User u = new User();
        query = "SELECT Password,Salt FROM users WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                u.setPassword(resultSet.getString(1));
                u.setSalt(resultSet.getBytes(2));
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return u;
    }

    public List<User> getUsers() throws ClassNotFoundException, SQLException {
        List<User> user = new ArrayList<User>();

        query = "SELECT * FROM users";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                User usert = new User();
                usert.setId(resultSet.getInt(1));
                usert.setUserName(resultSet.getString(2));
                usert.setLastSeen(resultSet.getTimestamp(9));
                usert.setUserClass(resultSet.getInt(10));
                usert.setIdGrupa(resultSet.getInt(14));
                usert.setIdSerie(resultSet.getInt(15));
                usert.setIdFacultate(resultSet.getInt(16));
                user.add(usert);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
        }

        return user;
    }

    public void updateIp(int id, String ip) throws ClassNotFoundException, SQLException {
        query = "UPDATE users SET Ip = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, ip);
            preparedStatement.setInt(2, id);
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

    public void updateLastSeen(int id) {
        java.sql.Timestamp date = new java.sql.Timestamp( (new java.util.Date().getTime()) );

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

    public void updateProfile(int id, int userClass) throws ClassNotFoundException, SQLException {
        query = "UPDATE users SET Class = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, userClass);
            preparedStatement.setInt(2, id);
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

    public void updateUser(int id, int gender, String username, String firstName, String lastName, String email, String avatar) throws SQLException, ClassNotFoundException{
        query = "UPDATE users SET FirstName = ?, LastName = ?, Email = ?, Avatar = ?, Gender = ?, Username = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, avatar);
            preparedStatement.setInt(5, gender);
            preparedStatement.setString(6, username);
            preparedStatement.setInt(7, id);
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

    public void updateFacultate(int id, int idGrupa) throws SQLException, ClassNotFoundException {
        query = "SELECT s.IdSerie,s.IdFacultate FROM grupe g JOIN serii s ON g.IdSerie = s.IdSerie WHERE IdGrupa = ?";

        int idSerie = -1;
        int idFacultate = -1;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGrupa);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                idSerie = resultSet.getInt(1);
                idFacultate = resultSet.getInt(2);
            }
        } finally {
            if (preparedStatement != null)
                preparedStatement.close();
            if (connection != null)
                connection.close();
            if (resultSet != null)
                resultSet.close();
        }

        query = "UPDATE users SET IdGrupa = ? , IdSerie = ?, IdFacultate = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGrupa);
            preparedStatement.setInt(2, idSerie);
            preparedStatement.setInt(3, idFacultate);
            preparedStatement.setInt(4, id);
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

    public void updateFacultateAdmin(int id, int idGrupa, int idSerie, int idFacultate) throws SQLException, ClassNotFoundException {

        query = "UPDATE users SET IdGrupa = ? , IdSerie = ?, IdFacultate = ? WHERE Id = ?";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(this.getHost(), this.getUser(), this.getPassword());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, idGrupa);
            preparedStatement.setInt(2, idSerie);
            preparedStatement.setInt(3, idFacultate);
            preparedStatement.setInt(4, id);
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

            for (String page : modAcces) {
                if(page.equals(pageName) && (int) session.getAttribute("userclass") < 5) {
                    response.sendRedirect("/index.jsp");
                }
            }

            for (String page : adminAcces) {
                if(page.equals(pageName) && (int) session.getAttribute("userclass") < 6) {
                    response.sendRedirect("/index.jsp");
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
        String token = null;

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }

        if( session.getAttribute("userId") == null || (int)session.getAttribute("userId") < 0)
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
