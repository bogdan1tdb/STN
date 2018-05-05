package com.stn.servlets;

import com.stn.helpers.SecurityHelper;
import com.stn.helpers.UserHelper;
import com.stn.pojo.User;
import com.stn.utils.Validator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/ChangePasswordProcess")
public class ChangePasswordProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("edit_profile.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String error = "";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int id = (int) session.getAttribute("userId");

        String password0 = request.getParameter("password0");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String hashedpassword;
        byte[] salt;
        String url = "/edit_profile.jsp";

        if (Validator.isEmpty(password0,password1, password2)) {
            error = "You must fill the fields!";
        } else if (!password1.equals(password2)) {
            error = "The passwords are not matching!";
        } else {
            SecurityHelper securityHelper = new SecurityHelper();
            UserHelper userHelper = new UserHelper();

            try {
                User u = userHelper.getPassword(id);
                securityHelper.setSalt(u.getSalt());
                String hashedpwcurrent = securityHelper.getHash(password0);

                if(hashedpwcurrent.equals(u.getPassword())) {
                    securityHelper.generateSalt();
                    hashedpassword = securityHelper.getHash(password1);
                    salt = securityHelper.getSalt();
                    String email = userHelper.getUserInfo(id).getEmail();
                    userHelper.updatePassword(email, hashedpassword, salt);
                    error = "Your password has been updated!";
                } else {
                    error = "Invalid current password!";
                }
            }
            catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
                out.println(e);
                return;
            }

        }

        if(error.equals("Your password has been updated!")) {
            session.setAttribute("error2","<br/><b style='color: green; display: inline;'>"+error+"</b>");
        }
        else {
            session.setAttribute("error2","<br/><b style='color: red; display: inline;'>"+error+"</b>");
        }

        response.sendRedirect(url);

    }

}
