package com.stn.servlets;

import com.stn.helpers.UserHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/LogoutProcess")
public class LogoutProcess extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        UserHelper userHelper = new UserHelper();

        String url = "index.jsp";

        HttpSession session = request.getSession();
        if(session.getAttribute("userId") != null)
        {
            for (Cookie cookie : request.getCookies()) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }

            userHelper.deleteToken((int)session.getAttribute("userId"));
            session.invalidate();
        }

        response.sendRedirect(url);

    }
}
