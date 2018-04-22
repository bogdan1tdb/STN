package com.stn.servlets;

import com.stn.helpers.ApplicationHelper;
import com.stn.helpers.SecurityHelper;
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
import java.sql.SQLException;

@WebServlet("/AdminAppProcess")
public class AdminAppProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/panel/applications.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ApplicationHelper applicationHelper = new ApplicationHelper();

        if(!Validator.isEmpty() || request.getParameter("id") != null) {
            int id = Integer.parseInt(request.getParameter("id"));

            try {
                applicationHelper.uptateApplicationStatus(id,2);
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return ;
            }

        }

        response.sendRedirect(url);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "panel/applications.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        int idAplicatie = -1;
        if ((int) session.getAttribute("userclass") > 5) {
            idAplicatie = Integer.parseInt(request.getParameter("idaplicatie"));
        }
        int idGrupa = Integer.parseInt(request.getParameter("idgrupa"));
        int idSerie = Integer.parseInt(request.getParameter("idserie"));
        int idFacultate = Integer.parseInt(request.getParameter("idfacultate"));

        ApplicationHelper applicationHelper = new ApplicationHelper();
        SecurityHelper securityHelper = new SecurityHelper();
        Tools tools = new Tools();

        if((int) session.getAttribute("userclass") == 2 && !Validator.isEmail(email)) {
            String result = "<b style='color: red; display: inline'>Invalid email addres!</b>";
            url = "invite.jsp";
            session.setAttribute("result",result);
            response.sendRedirect(url);
        }

        String token = securityHelper.generateRandomString(38);

        try {
            if ((int) session.getAttribute("userclass") == 2) {
                applicationHelper.createInvite(token,idGrupa, idSerie, idFacultate, email, 1);
            }

            if ((int) session.getAttribute("userclass") > 5) {
                applicationHelper.createInvite(token,idGrupa, idSerie, idFacultate, email, 2);
            }

        } catch (SQLException | ClassNotFoundException e) {
            out.println(e);
            return;
        }
        if ((int) session.getAttribute("userclass") > 5) {
            try {
                applicationHelper.uptateApplicationStatus(idAplicatie, 3);
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            }
        }

        String link  = request.getScheme() + "://" + request.getServerName() + request.getContextPath() + "/register.jsp?invite=" + token;
        String body = "Your invitation link : <br/> " + "<a href='" + link + "'>" + link + "</a>" + " <br/>" +
                "Enjoy! :)";

        try {
            tools.sendEmail(email,"SchoolingTheInternet Invitation",body);
        } catch (MessagingException e) {
            out.println(e);
            return;
        }

        if ((int) session.getAttribute("userclass") > 5) {
            response.sendRedirect(url);
        } else if((int) session.getAttribute("userclass") == 2) {
            String result = "<b style='color: green; display: inline'>Invitatia a fost trimisa!</b>";
            url = "invite.jsp";
            session.setAttribute("result",result);
            response.sendRedirect(url);
        }

    }

}
