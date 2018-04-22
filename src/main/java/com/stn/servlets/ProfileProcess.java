package com.stn.servlets;

import com.stn.helpers.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/ProfileProcess")
public class ProfileProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String error ="";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int userId = Integer.parseInt(request.getParameter("userid"));
        int userClass;
        if(request.getParameter("userclass") != null) {
            userClass = Integer.parseInt(request.getParameter("userclass"));
        } else {
            userClass = -1;
        }

        UserHelper userHelper = new UserHelper();

        if((int) userClass < 0) {
            error = "<b style='color: red; display: inline'>No userclass selected!</b>";
        } else {

            try {
                userHelper.updateProfile(userId,userClass);
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            }

            error = "<b style='color: green; display: inline'>Profile updated!</b>";

        }


        String url = "userdetails.jsp?id=" + userId;
        session.setAttribute("error2",error);
        response.sendRedirect(url);
    }
}
