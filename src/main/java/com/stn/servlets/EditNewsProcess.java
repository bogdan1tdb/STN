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

@WebServlet("/EditNewsProcess")
public class EditNewsProcess extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String error = "";
        String url = "/index.jsp";
        response.setContentType("text/html");
        NewsHelper newsHelper = new NewsHelper();

        int idNews = Integer.parseInt(request.getParameter("id"));

        try {
            newsHelper.deleteNews(idNews);
        } catch (SQLException | ClassNotFoundException e) {
            out.println(e);
        }

        response.sendRedirect(url);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();

        String error = "";
        String url = "/index.jsp";
        response.setContentType("text/html");

        UserHelper userHelper = new UserHelper();

        String title = "";
        String body = "";
        int type = Integer.parseInt(request.getParameter("news_type"));
        if(type == 1) {
            title = request.getParameter("news_title_temp");
        } else {
            body = request.getParameter("news_body_temp");
        }

        int idNews = Integer.parseInt(request.getParameter("news_id_temp"));
        NewsHelper newsHelper = new NewsHelper();

        if(type == 1) {
            try {
                newsHelper.updateTitle(idNews,title);
            } catch (SQLException | ClassNotFoundException e) {
                out.println(e);
            }
        } else if(type == 2){
            try {
                newsHelper.updateBody(idNews,body);
            } catch (SQLException | ClassNotFoundException e) {
                out.println(e);
            }
        }

        response.sendRedirect(url);

    }
}
