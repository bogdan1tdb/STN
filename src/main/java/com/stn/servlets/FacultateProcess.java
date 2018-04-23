package com.stn.servlets;

import com.stn.helpers.FacultateHelper;
import com.stn.pojo.Facultate;
import com.stn.utils.Validator;
import org.owasp.encoder.Encode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FacultateProcess")
public class FacultateProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/panel/editfacultati.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String result = "";
        String url = "/panel/editfacultati.jsp";

        String type = request.getParameter("type");
        String facultate = request.getParameter("facultate");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        FacultateHelper facultateHelper = new FacultateHelper();
        List<Facultate> facultati = new ArrayList<Facultate>();

        if(type.equals("add")) {

            if(Validator.isEmpty(facultate)) {
                result = "Nu ai selectat o facultate!";
            } else {

                try {
                    facultateHelper.addFacultate(facultate);
                    result = "Facultatea a fost adaugata!";
                } catch (ClassNotFoundException | SQLException e) {
                    out.println(e);
                    return;
                }

            }

            if(result.equals("Nu ai selectat o facultate!")) {
                session.setAttribute("result","<b style='color: red; display: inline'>"+result+"</b>");
            }
            else {
                session.setAttribute("result","<b style='color: green; display: inline'>"+result+"</b>");
            }

        } else {

            try {
                facultati = facultateHelper.findFacultate(facultate);

                result = "<table class='black' align='center' style='border-collapse: collapse; width : 60%; padding-top : 10pt'>";
                result = result + "<tr><td class='row' style='padding: 2pt; width : 8%; text-align: center'><b>Id</b></td><td class='row' style='padding: 2pt; text-align: center'><b>Nume</b></td></tr>";
                if(facultati.size() > 0) {
                    for (Facultate fac : facultati) {
                        result = result + "<tr><td class='row' style='padding: 2pt'>"+fac.getIdFacultate()+"</td>";
                        result = result + "<td class='row' style='padding: 2pt'>"+ Encode.forHtml(fac.getNume())+"</td></tr>";
                    }
                } else {
                    result = result + "<tr><td class='row' colspan='2' style='padding: 2pt; text-align: center'>Nu au fost gasite facultati!</td></tr>";
                }

                result = result + "</table>";

            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            }

            if(result.equals("Nu ai selectat o facultate!")) {
                session.setAttribute("result","<b style='color: red; display: inline'>"+result+"</b>");
            }
            else {
                session.setAttribute("result",result);
            }

        }


        response.sendRedirect(url);

    }
}
