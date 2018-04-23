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

@WebServlet("/LeaveGrupaProcess")
public class LeaveGrupaProcess extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/group.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String result = "";
        String url = "/group.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int idUser = (int) session.getAttribute("userId");
        UserHelper userHelper = new UserHelper();

        try {
            userHelper.deleteGrupa(idUser);
        } catch (ClassNotFoundException | SQLException e) {
            out.println(e);
            return;
        }

        response.sendRedirect(url);

    }
}
