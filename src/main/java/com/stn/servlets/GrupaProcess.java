package com.stn.servlets;

import com.stn.helpers.FacultateHelper;
import com.stn.utils.Validator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/GrupaProcess")
public class GrupaProcess extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/panel/editfacultati.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String result = "";
        String url = "/panel/editfacultati.jsp";

        String type = request.getParameter("type");
        String name = request.getParameter("grupadd");
        int idSerie = Integer.parseInt(request.getParameter("idserie"));
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        FacultateHelper facultateHelper = new FacultateHelper();

        if(Validator.isEmpty(name)) {
            result = "Nu ai dat nume grupei!";
        } else {

            try {
                facultateHelper.addGrupa(name,idSerie);
                result = "Grupa a fost adaugata!";
            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            }

        }

        if(result.equals("Nu ai dat nume grupei!")) {
            session.setAttribute("result3","<b style='color: red; display: inline'>"+result+"</b>");
        }
        else {
            session.setAttribute("result3","<b style='color: green; display: inline'>"+result+"</b>");
        }

        response.sendRedirect(url);

    }
}
