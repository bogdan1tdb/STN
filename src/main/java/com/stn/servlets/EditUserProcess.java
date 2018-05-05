package com.stn.servlets;

import com.stn.helpers.UserHelper;
import com.stn.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/EditUserProcess")
public class EditUserProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("edit_profile.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        String error = "";
        response.setContentType("text/html");

        UserHelper userHelper = new UserHelper();
        int id = (int) session.getAttribute("userId");

        String username = request.getParameter("username");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        int gender = Integer.parseInt(request.getParameter("gender"));
        String email = request.getParameter("email");
        String avatar = request.getParameter("avatar");
        String url = "/edit_profile.jsp";

        if (Validator.isEmpty(username, email, firstName, lastName)) {
            error = "You must fill all the requiered fields!";
        } else if (!Validator.between(firstName, 2, 30)) {
            error = "Firstname must be between 2 and 30 characters!";
        } else if (!Validator.isName(firstName)) {
            error = "Invalid Firstname!";
        } else if (!Validator.between(lastName, 2, 30)) {
            error = "Lastname must be between 2 and 30 characters!";
        } else if (!Validator.isName(lastName)) {
            error = "Invalid Lastname!";
        } else if (!Validator.between(email, 6, 30)) {
            error = "Email must be between 6 and 30 characters!";
        } else if (!Validator.isEmail(email)) {
            error = "Invalid email addres!";
        } else if (!avatar.equals("img/profile.png") && !Validator.isURL(avatar)) {
            error = "Invalid avatar!";
        } else {
            try {
                userHelper.updateUser(id, gender, username, firstName, lastName, email, avatar);
                error = "Profile updated!";
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            }
        }

        if(error.equals("Profile updated!")) {
            session.setAttribute("error","<br/><b style='color: green; display: inline;'>"+error+"</b>");
        }
        else {
            session.setAttribute("error","<br/><b style='color: red; display: inline;'>"+error+"</b>");
        }

        response.sendRedirect(url);
    }

}
