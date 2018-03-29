<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
%>

<table class="black" width="760">
    <tr>
        <td class="left">Welcome, <%=session.getAttribute("user") %>!</td>
        <td class="right">Userclass: WIP
            &nbsp&nbsp&nbsp <p style="color: #387FA8; display : inline">Invites:</p>
            <a class="ui" href="../index.jsp">0</a>
        </td>
    </tr>
    <tr>
        <td class="left"></td>
        <td class="right"><i class="material-icons" style="font-size: 10px">schedule&nbsp</i><%=dateFormat.format(date)  %>&nbsp&nbsp
            <a class="ui" href="../index.jsp">[Settings]</a>&nbsp&nbsp
            <a class="ui" href="../logout.jsp">[Logout]</a></td>
    </tr>
</table>
