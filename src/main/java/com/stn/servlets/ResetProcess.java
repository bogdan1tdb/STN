package com.stn.servlets;

import com.stn.helpers.RecoverHelper;
import com.stn.helpers.SecurityHelper;
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
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet("/ResetProcess")
public class ResetProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("reset.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String error ="";
        String url = "index.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("token");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String email ;
        String hashedpassword;
        byte[] salt;

        if(Validator.isEmpty(password1, password2)) {
            error = "You must fill the fields!";
            url = "reset.jsp?token="+token;
        }
        else if(!password1.equals(password2)) {
            error = "The passwords are not matching!";
            url = "reset.jsp?token="+token;
        } else {
            RecoverHelper recoverHelper = new RecoverHelper();
            SecurityHelper securityHelper = new SecurityHelper();
            UserHelper userHelper = new UserHelper();

            try {
                if(( email = recoverHelper.checkToken(token) ) != null) {

                    securityHelper.generateSalt();
                    hashedpassword = securityHelper.getHash(password1);
                    salt = securityHelper.getSalt();

                    userHelper.updatePassword(email,hashedpassword,salt);
                    recoverHelper.deleteToken(token);
                } else {
                    url = "reset.jsp";
                }
            } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
                out.println(e);
                return ;
            }

        }

        session.setAttribute("error", error);
        response.sendRedirect(url);

    }
}
