package com.stn.servlets;

import com.stn.helpers.OrarHelper;
import com.stn.helpers.UserHelper;
import com.stn.pojo.Notes;
import com.stn.pojo.Orar;
import com.stn.pojo.User;
import com.stn.utils.Tools;

import javax.mail.MessagingException;
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

@WebServlet("/DeleteOraProcess")
public class DeleteOraProcess extends HttpServlet {
    private void setTempFields(HttpServletRequest request, Integer id_ora, Integer id_student) {
        HttpSession session = request.getSession();
        session.setAttribute("id_ora", id_ora);
        session.setAttribute("id_student", id_student);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String error = "";
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Integer id_ora = Integer.valueOf(request.getParameter("id_ora"));
        String url = "orar.jsp";

        OrarHelper deleteOra = new OrarHelper();
        ArrayList<Notes> notification = new ArrayList<>();
        Tools sendSchool = new Tools();
        UserHelper user = new UserHelper();
        User userInfo;

        try {
            notification = deleteOra.getSchools(id_ora);
            for (Notes n : notification
                    ) {
                Integer id_stud = n.getId_stud();
                userInfo = user.getUserInfo(id_stud);
                Orar ora = deleteOra.getOra(id_ora, deleteOra.getOrar(userInfo.getIdGrupa()));
                try {
                    if(user.checkEmail(userInfo.getEmail())) {
                        String body = "Ora " + ora.getName()+ " cu tipul "+ora.getTip_act()+ " din ziua de "+ ora.getZi()+" din saptamana "+ora.getSapt();
                        if(ora.getSgr()!=0)
                            body=body+" al semigrupei "+ora.getSgr();
                        body=body+" cu durata " + ora.getDurata() + " al grupei tale a fost stearsa de seful grupei, aici sunt informatiile salvate pentru acea materie<br/>"+"Teme<br/>"+n.getTema()+"<br/>"+"Examen<br/>"+n.getExamen()+"<br/>"+"Note:"+n.getNota()+"<br/><br/>Best of luck,<br/> SchoolingTheInternet Team";
                        String subject = "Stergerea orei " + ora.getName();
                        sendSchool.sendEmail(userInfo.getEmail(),subject,body);
                    }
                } catch (ClassNotFoundException | SQLException | MessagingException  e) {
                    out.println(e);

                }
            }
            deleteOra.deleteOra(id_ora);
        } catch (SQLException | ClassNotFoundException e) {
            out.println(e);
            return;
        }
        error="<b style='color=aquamarine'>Mailuri trimise si ora stearsa.</b>";
        session.setAttribute("error", error);
        response.sendRedirect(url);
    }
}
