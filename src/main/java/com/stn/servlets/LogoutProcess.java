package com.stn.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutProcess")
public class LogoutProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "index.jsp";
        HttpSession session = request.getSession();
        Cookie cookie = new Cookie("token", "");

        cookie.setMaxAge(0);
        session.invalidate();

        response.sendRedirect(url);

    }
}
