package com.stn.servlets;

import com.stn.helpers.SecurityHelper;
import com.stn.helpers.UserHelper;

import javax.servlet.RequestDispatcher;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error ="";
        String url = "index.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("user");
        String password = request.getParameter("password");

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
                if(userHelper.authenticateUser(username, password)) {
                    session.invalidate(); // stergem sesiunea curenta
                    session=request.getSession(true);
                    session.setAttribute("user", username); // setam sesiune pe user-ul curent
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
       request.setAttribute("error", error);
       RequestDispatcher dispatcher = request.getRequestDispatcher(url);
       dispatcher.forward(request, response);
    }
}
