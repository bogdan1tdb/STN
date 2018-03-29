package com.stn.servlets;

import com.stn.utils.DBConnection;
import com.stn.utils.Validator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/RegisterProcess")
public class RegisterProcess extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error ="";
        String url = "index.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String email = request.getParameter("email");

        if(Validator.isEmpty(user, password1, password2, email)) {
            error = "You must fill all the requiered fields!";
            url = "register.jsp";
        }
        else if(!Validator.isEmail(email)) {
            error = "Invalid email addres!";
            url = "register.jsp";
        }
        else if(!password1.equals(password2)) {
            error = "The passwords are not matching!";
            url = "register.jsp";
        } else {
            PreparedStatement preparedStatement = null;
            Connection connection = null;
            DBConnection db = new DBConnection();
            String query = "INSERT INTO users(Username, Password, Email) VALUES (?,?,?)";

            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(db.getHost(), db.getUser(), db.getPassword());
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, user);
                preparedStatement.setString(2, password1);
                preparedStatement.setString(3, email);
                preparedStatement.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            } finally {
                try {
                    if (preparedStatement != null)
                        preparedStatement.close();
                    if (connection != null)
                        connection.close();
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
