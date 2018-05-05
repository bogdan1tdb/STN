package com.stn.servlets;

import com.stn.helpers.OrarHelper;
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

@WebServlet("/AddOra")
public class AddOra extends HttpServlet {

    private void setTempFields(HttpServletRequest request,Integer id_user, String nume_m, String durata, String tip, Integer semig, String sala, String nume_p,String zi,Integer grupa,String sapt) {
        HttpSession session = request.getSession();
        session.setAttribute("id_user",id_user);
        session.setAttribute("nume_m", nume_m);
        session.setAttribute("durata", durata);
        session.setAttribute("tip", tip);
        if(semig==0)
            session.setAttribute("semig",null);
        else
        session.setAttribute("semig", semig);
        session.setAttribute("sala", sala);
        session.setAttribute("nume_p", nume_p);
        session.setAttribute("zi",zi);
        session.setAttribute("id_gr",grupa);
        session.setAttribute("sapt",sapt);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("orar.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String error = "";
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        //Setam toti parametrii pe care ii primim din form
        Integer id_grupa;
        Integer id_user= Integer.valueOf(request.getParameter("id_user"));
        String url = "orar.jsp";
        String nume_m = request.getParameter("nume_m");
        String durata = request.getParameter("durata");
        String tip = request.getParameter("tip");
        Integer semig = Integer.valueOf(request.getParameter("semig"));
        String sala = request.getParameter("sala");
        String nume_p = request.getParameter("nume_p");
        String zi=request.getParameter("zi");
        Integer grupa= Integer.valueOf(request.getParameter("id_gr"));
        String sapt=request.getParameter("sapt");
        if (Validator.isEmpty(nume_m, durata, tip, sala, nume_p)) {
            error = "You must fill all the requiered fields!";
            url = "ora.jsp?id="+id_user;
            this.setTempFields(request, id_user, nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
        } else if (!Validator.between(nume_m, 5, 50)) {
            error = "Numele materiei este prea lung/scurt!";
            url = "ora.jsp?id="+id_user;
            this.setTempFields(request, id_user, nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
        } else if (!Validator.between(durata, 2, 15)) {
            error = "Durata trebuie setata ca in model!";
            url = "ora.jsp?id="+id_user;
            this.setTempFields(request, id_user, nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
        } else if (!Validator.between(sala, 1, 10)) {
            error = "Sala trebuie sa fie intre 1 si 10 caractere";
            url = "ora.jsp?id="+id_user;
            this.setTempFields(request, id_user, nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
        } else if (!Validator.between(nume_p, 5, 50)) {
            error = "Numele profesorului prea scurt/lung!";
            url = "ora.jsp?id="+id_user;
            this.setTempFields(request, id_user, nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
        } else {
            OrarHelper addOraHelper=new OrarHelper();
            OrarHelper addNotes=new OrarHelper();
            try {
                id_grupa=addOraHelper.getGrupaOra(id_user);
                addOraHelper.addOra(id_grupa,nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
                addNotes.addNotes(id_grupa,id_user,nume_m, durata, tip, semig, sala, nume_p,zi,grupa,sapt);
            } catch (SQLException | ClassNotFoundException e) {
                out.println(e);
                return;
            }
            error="Ora adaugata!";
        }
        if(error.equals("Ora adaugata!"))
        {
            error = "<b style='color: green; display: inline'>" + error + "</b>";
        } else {
            error = "<b style='color: red; display: inline'>" + error + "</b>";
        }

        //Redirectionare catre o anumita pagina (este data de string-ul url)
        session.setAttribute("error", error);
        response.sendRedirect(url);
    }
}
