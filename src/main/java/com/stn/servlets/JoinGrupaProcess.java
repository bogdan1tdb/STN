package com.stn.servlets;

import com.stn.helpers.FacultateHelper;
import com.stn.helpers.UserHelper;
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

@WebServlet("/JoinGrupaProcess")
public class JoinGrupaProcess extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String result = "";
        String url = "/group.jsp";

        String token = request.getParameter("gtoken");
        int idUser = (int) session.getAttribute("userId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        UserHelper userHelper = new UserHelper();
        FacultateHelper facultateHelper = new FacultateHelper();

        if(Validator.isEmpty(token)) {
            result = "Introdu un cod al unei grupe!";
        } else {

            try {

                int idGrupa = -1;
                idGrupa = facultateHelper.checkToken(token);
                if(idGrupa > 0) {
                   userHelper.updateFacultate(idUser,idGrupa);
                } else {
                    result = "Cod invalid!";
                }

            } catch (ClassNotFoundException | SQLException e) {
                out.println(e);
                return;
            }

        }

        session.setAttribute("result","<b style='color: red; display: inline'>"+result+"</b>");

        response.sendRedirect(url);

    }

}
