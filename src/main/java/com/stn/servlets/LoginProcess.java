package com.stn.servlets;

import com.stn.helpers.SecurityHelper;
import com.stn.helpers.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

@WebServlet("/LoginProcess")
public class LoginProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error ="";
        String url = "index.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("user");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember_me");
        int userId ;

        HttpSession session = request.getSession();

        SecurityHelper securityHelper = new SecurityHelper();
        String ip = securityHelper.getClientIpAddress(request);

        if(username.isEmpty() || password.isEmpty()) {
            error = "Fill all the requiered spaces!";
            url = "login.jsp";
        } else if(securityHelper.getAttempts(request) > 9) { // se verifica cate incercari pentru login mai avem
            error = "You are out of login attempts!";
            url = "login.jsp";
        } else {
            UserHelper userHelper = new UserHelper();
            try {
                if( ( userId = userHelper.authenticateUser(username, password) ) > 0 ) {
                    session.invalidate(); // stergem sesiunea curenta
                    session=request.getSession(true);
                    session.setAttribute("userId", userId); // setam sesiune pe user-ul curent
                    if(rememberMe != null) {
                        session.setMaxInactiveInterval(-1);
                    }
                } else {
                    error = "Invalid username or password!";
                    url = "login.jsp";
                    securityHelper.updateAttempts(ip);
                }
            } catch (ClassNotFoundException | SQLException | NoSuchAlgorithmException e) {
                out.println(e);
                return;
            }
        }

        //Redirectionare catre o anumita pagina (este data de string-ul url)
        session.setAttribute("error", error);
        response.sendRedirect(url);
    }
}
