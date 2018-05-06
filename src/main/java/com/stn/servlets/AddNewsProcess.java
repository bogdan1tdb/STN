package com.stn.servlets;

import com.stn.helpers.NewsHelper;
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

@WebServlet("/AddNewsProcess")
public class AddNewsProcess extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        String error = "";
        String url = "/index.jsp";
        response.setContentType("text/html");

        UserHelper userHelper = new UserHelper();
        int idUser = (int) session.getAttribute("userId");

        String title = request.getParameter("news_title");
        String body = request.getParameter("news_body");
        int idSerie = Integer.parseInt(request.getParameter("news_serie"));
        NewsHelper newsHelper = new NewsHelper();


        if (Validator.isEmpty(title,body)) {
            error = "<br/><b style='color: #db5555; padding-top: 5pt'>You must fill all the requiered fields!</b>";
            url = "/index.jsp#newsform";
        } else {
            try {
                newsHelper.addNews(title,body,idUser,idSerie);
            } catch (SQLException | ClassNotFoundException e) {
                out.println(e);
            }
        }

        session.setAttribute("error",error);
        response.sendRedirect(url);

    }
}
