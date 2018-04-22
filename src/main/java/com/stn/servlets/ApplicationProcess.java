package com.stn.servlets;

import com.stn.helpers.ApplicationHelper;
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

@WebServlet("/ApplicationProcess")
public class ApplicationProcess extends HttpServlet {

    private void setTempFields(HttpServletRequest request, String firstName, String lastName, String email, String facultate, String serie, String grupa, String document) {
        HttpSession session= request.getSession();
        session.setAttribute("firstname", firstName);
        session.setAttribute("lastname", lastName);
        session.setAttribute("email", email);
        session.setAttribute("faculty",facultate);
        session.setAttribute("serie",serie);
        session.setAttribute("grupa",grupa);
        session.setAttribute("document",document);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String error ="";
        String url = "apply.jsp";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        //Setam toti parametrii pe care ii primim din form
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String facultate = request.getParameter("facultate");
        String serie = request.getParameter("serie");
        String grupa = request.getParameter("grupa");
        String document = request.getParameter("document");

        if(Validator.isEmpty(email, firstName, lastName, facultate, serie, grupa, document)) {
            error = "You must fill all the requiered fields!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(firstName,2,30)) {
            error = "Firstname be between 2 and 30 characters!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.isName(firstName)) {
            error = "Invalid Firstname!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(lastName,2,30)) {
            error = "Lastname be between 2 and 30 characters!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.isName(lastName)) {
            error = "Invalid Lastname!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(facultate, 3, 120)) {
            error = "Facultatea trebuie sa contina intre 3 si 120 de caractere!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(serie, 1, 20)) {
            error = "Seria trebuie sa contina intre 1 si 20 de caractere!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(grupa, 1, 20)) {
            error = "Grupa trebuie sa contina intre 1 si 20 de caractere!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(document,6,120)) {
            error = "Documentul trebuie sa contina intre 6 si 120 de caractere!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.isURL(document)) {
            error = "Invalid document url!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.between(email,6,30)) {
            error = "Email be between 6 and 30 characters!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else if(!Validator.isEmail(email)) { //verificare email daca este valid
            error = "Invalid email addres!";
            this.setTempFields(request,firstName,lastName,email,facultate,serie,grupa,document);
        }
        else {
            ApplicationHelper applicationHelper = new ApplicationHelper();

            try {
                applicationHelper.addApplication(email,firstName,lastName,facultate,serie,grupa,document);
            } catch (SQLException | ClassNotFoundException e) {
                out.println(e);
                return;
            }

            error = "Application sent!";

        }


        if(error.equals("Application sent!"))
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
