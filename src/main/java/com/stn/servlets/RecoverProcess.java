package com.stn.servlets;

import com.stn.helpers.RecoverHelper;
import com.stn.helpers.SecurityHelper;
import com.stn.helpers.UserHelper;
import com.stn.utils.Tools;
import com.stn.utils.Validator;

import javax.mail.MessagingException;
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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String error ="";
        String url = "recover.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String token;
        String hashedToken;
        String body;
        String ip;

        if(email.isEmpty() ) {
            error = "Please insert an email address!";
        } else if(!Validator.isEmail(email)){
            error = "Invalid email addres!";
        } else {
            UserHelper userHelper = new UserHelper();
            Tools tools = new Tools();
            SecurityHelper securityHelper = new SecurityHelper();
            RecoverHelper recoverHelper = new RecoverHelper();

            //WIP
            try {
                if(userHelper.checkEmail(email)) {
                    token = securityHelper.generateRandomString(7);
                    ip = securityHelper.getClientIpAddress(request);
                    securityHelper.generateSalt();
                    hashedToken = securityHelper.getHash(token);
                    body = "Your reset link : <br/> " + request.getServerName() + "/reset.jsp?token=" + hashedToken + " <br/>" +
                    "The request was made from this ip : " + ip;
                    tools.sendEmail(email,"Password reset",body);
                    recoverHelper.insertToken(email,hashedToken);
                }
                error = "A validation email has been sent to your email address!";
            } catch (ClassNotFoundException | SQLException | MessagingException | NoSuchAlgorithmException e) {
                out.println(e);
                return;
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
