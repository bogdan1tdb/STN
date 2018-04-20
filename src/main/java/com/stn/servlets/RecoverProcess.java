package com.stn.servlets;

import com.stn.helpers.RecoverHelper;
import com.stn.helpers.SecurityHelper;
import com.stn.helpers.UserHelper;
import com.stn.utils.Tools;
import com.stn.utils.Validator;

import javax.mail.MessagingException;
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

@WebServlet("/RecoverProcess")
public class RecoverProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("recover.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String error ="";
        String url = "recover.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String token;
        String hashedToken;
        String body;
        String ip;
        String resetUrl;

        if(Validator.isEmpty(email) ) {
            error = "Please insert an email address!";
        } else if(!Validator.isEmail(email)){
            error = "Invalid email addres!";
        } else {
            UserHelper userHelper = new UserHelper();
            Tools tools = new Tools();
            SecurityHelper securityHelper = new SecurityHelper();
            RecoverHelper recoverHelper = new RecoverHelper();

            try {
                if(userHelper.checkEmail(email)) {
                    token = securityHelper.generateRandomString(7);
                    ip = securityHelper.getClientIpAddress(request);
                    securityHelper.generateSalt();
                    hashedToken = securityHelper.getHash(token);
                    resetUrl = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/reset.jsp?token=" + hashedToken;
                    body = "Your reset link : <br/> " + "<a href='" + resetUrl + "'>" + resetUrl + "</a>" + " <br/>" +
                    "The request was made from following IP : " + ip + "<br/>" +
                    "If this wasn't you,report this message to the website administrator!";
                    recoverHelper.insertToken(email,hashedToken);
                    tools.sendEmail(email,"Password reset",body);
                }
                error = "A validation email has been sent to your email address!";
            } catch (ClassNotFoundException | SQLException | MessagingException | NoSuchAlgorithmException e) {
                out.println(e);
                return;
            }
        }

        if(error.equals("A validation email has been sent to your email address!")) {
            session.setAttribute("error","<b style='color: green; display: inline'>"+error+"</b>");
        }
        else {
            session.setAttribute("error","<b style='color: red; display: inline'>"+error+"</b>");
        }

        response.sendRedirect(url);
    }
}
