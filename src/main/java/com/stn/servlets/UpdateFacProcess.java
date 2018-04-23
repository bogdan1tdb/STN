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

@WebServlet("/UpdateFacProcess")
public class UpdateFacProcess extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int idGrupa = Integer.parseInt(request.getParameter("idgrupa"));
        int idSerie = Integer.parseInt(request.getParameter("idserie"));
        int idFacultate = Integer.parseInt(request.getParameter("idfacultate"));
        int idUser = Integer.parseInt(request.getParameter("idaplicatie"));;
        UserHelper userHelper = new UserHelper();

        String url = "/userdetails.jsp?id="+idUser;

        try {
            userHelper.updateFacultateAdmin(idUser,idGrupa,idSerie,idFacultate);
        } catch (ClassNotFoundException | SQLException e) {
            out.println(e);
            return;
        }

        session.setAttribute("error2","<b style='color: green; display: inline'>Profile updated!</b>");
        response.sendRedirect(url);

    }

}
