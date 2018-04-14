<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="black" style="margin-top: 5pt; width: 570pt">
    <tr>
        <td class="left">
            Welcome, <a href='userdetails.jsp?id=${sessionScope.userId}' style="text-decoration: none"><b style="color: ${user.classColor(userInfo.getUserClass())}">${userInfo.getUserName()}</b></a>
        </td>
        <td class="right">
            <p style="color: #387FA8; display : inline">Invites:</p> <a class="ui" href="index.jsp">0</a>
        </td>
    </tr>
    <tr>
        <td class="left"></td>
        <td class="right"><i class="material-icons" style="font-size: 10px">schedule</i> ${tool.formatDate(tool.getDate(),1)} &nbsp&nbsp
            <a class="ui" href="#">[Settings]</a>&nbsp&nbsp
            <a class="ui" href="LogoutProcess">[Logout]</a></td>
    </tr>
</table>


<table class="menu">
    <tr>
     <td class="center"><a class="ui2" href="index.jsp">Home</a>
        <a class="ui3" href="#">Files</a>
        <a class="ui3" href="#">Add</a>
        <a class="ui3" href="#">Requests</a>
        <a class="ui3" href="#">Forums</a>
        <a class="ui3" href="#">IRC</a>
        <a class="ui3" href="#">Top 10</a>
        <a class="ui2" href="wiki.jsp">Wiki</a></td>
    </tr>
</table>