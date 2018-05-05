package com.stn.servlets;

import com.stn.helpers.OrarHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/AddNotes")
public class AddNotes extends HttpServlet {
    private void setTempFields(HttpServletRequest request, Integer id_ora,Integer id_stud, String teme, String examen, Integer nota) {
        HttpSession session = request.getSession();
        session.setAttribute("id_stud",id_stud);
        session.setAttribute("id_ora", id_ora);
        session.setAttribute("teme", teme);
        session.setAttribute("examen", examen);
        session.setAttribute("nota", nota);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        response.sendRedirect("orar.jsp");
    }
    protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        HttpSession session=request.getSession();

        String error="";
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        String name=request.getParameter("name");
        Integer id_ora=Integer.valueOf(request.getParameter("id_ora"));
        Integer id_student=Integer.valueOf(request.getParameter("id_user"));
        String url="ora1.jsp?id="+id_ora;
        String teme=request.getParameter("teme");
        String examen=request.getParameter("examen");
        Integer nota= Integer.valueOf(request.getParameter("nota"));

        OrarHelper addNotes=new OrarHelper();
        try{
            addNotes.updateNotes(id_ora,id_student,teme,examen,nota);
        } catch (SQLException | ClassNotFoundException e) {
            out.println(e);
            return;
        }
        error="Schimbat";
        session.setAttribute("error",error);
        response.sendRedirect(url);
    }
}
