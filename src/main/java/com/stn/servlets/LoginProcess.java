package com.stn.servlets;

import com.stn.helpers.SecurityHelper;
import com.stn.helpers.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
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
                    session.setAttribute("userId", userId); // setam sesiune pe user-ul curent
                    userHelper.updateIp(userId,ip);
                    if(rememberMe != null) {
                        String token = securityHelper.generateRandomString(28);
                        Cookie cookie = new Cookie("token", token);
                        cookie.setMaxAge(60 * 60 * 24 * 365 );
                        userHelper.updateLoginToken(userId,token,ip);
                        response.addCookie(cookie);
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
