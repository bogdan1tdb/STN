<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
    Date date = new Date();
%>

<jsp:useBean id="t" class="com.stn.helpers.UserHelper"/>

${t.updateLastSeen(pageContext.request)}

<table class="black" style="margin-top: 15pt; width: 38%;">
    <tr>
        <td class="left">Welcome, <%=session.getAttribute("user") %>!</td>
        <td class="right">Userclass: WIP
            &nbsp&nbsp&nbsp <p style="color: #387FA8; display : inline">Invites:</p>
            <a class="ui" href="index.jsp">0</a>
        </td>
    </tr>
    <tr>
        <td class="left"></td>
        <td class="right"><i class="material-icons" style="font-size: 10px">schedule&nbsp</i><%=dateFormat.format(date)  %>&nbsp&nbsp
            <a class="ui" href="index.jsp">[Settings]</a>&nbsp&nbsp
            <a class="ui" href="logout.jsp">[Logout]</a></td>
    </tr>
</table>

<ul class="menu">
    <li>
        <a class="ui3" href="../index.php">Home</a>
    </li>
    <li>
        <a class="ui3" href="../browse.php">Files</a>
    </li>
    <li>
        <a class="ui3" href="../add.php">Add</a>
    </li>
    <li>
        <a class="ui3" href="../requests.php">Requests</a>
    </li>
    <li>
        <a class="ui3" href="../forums.php">Forums</a>
    </li>
    <li>
        <a class="ui3" href="../chat.php">IRC</a>
    </li>
    <li>
        <a class="ui3" href="../top10.php">Top 10</a>
    </li>
    <li>
        <a class="ui3" href="../wiki.php">Wiki</a>
    </li>
</ul>