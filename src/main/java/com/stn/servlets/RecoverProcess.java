package com.stn.servlets;

import com.stn.utils.DBConnection;
import com.stn.utils.PasswordHelper;
import com.stn.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/RecoverProcess")
public class RecoverProcess extends HttpServlet {

    // WIP! Nu trimite nimic,doar verifica daca avem un email valid
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error ="";
        String url = "recover.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");

        if(email.isEmpty() ) {
            error = "Please insert an email address!";
        } else if(!Validator.isEmail(email)){
            error = "Invalid email addres!";
        } else {
            PreparedStatement preparedStatement = null;
            Connection connection = null;
            DBConnection db = new DBConnection();
            String query = "SELECT 1 FROM users WHERE Email = ?";
            ResultSet rs = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1,email);
                rs = preparedStatement.executeQuery();
                if (rs.next()) {
                }
                error = "A validation email has been sent to your email address!";
            } catch (ClassNotFoundException | SQLException e) {
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

        if(error.equals("A validation email has been sent to your email address!")) {
            request.setAttribute("error","<b style='color: green; display: inline'>"+error+"</b>");
        }
        else {
            request.setAttribute("error","<b style='color: red; display: inline'>"+error+"</b>");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}
