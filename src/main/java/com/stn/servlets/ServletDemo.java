package com.stn.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.stn.db.*;

@WebServlet("/ServletDemo")
public class ServletDemo extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        DB test = new DB();
        test.query("SELECT * FROM users");
        while(test.next())
        {
            String username = test.getString(2);
            String password = test.getString(3);
            Integer join_date = test.getInt(4);
            out.println(username+" | "+password+" "+" | "+join_date+"<br/>");
        }
        out.println("</body></html>");
        test.close();
    }
}
