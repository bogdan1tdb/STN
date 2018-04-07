<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="com.stn.helpers.UserHelper"/>
<jsp:useBean id="tool" class="com.stn.utils.Tools"/>

${user.verifyAuthentication(pageContext.request,pageContext.response)}
${user.updateLastSeen(pageContext.request)}

<table class="black" style="margin-top: 5pt; width: 550pt">
    <tr>
        <td class="left">Welcome, <b>${sessionScope.user}</b>!</td>
        <td class="right">
            <p style="color: #387FA8; display : inline">Invites:</p> <a class="ui" href="index.jsp">0</a>
        </td>
    </tr>
    <tr>
        <td class="left"></td>
        <td class="right"><i class="material-icons" style="font-size: 10px">schedule&nbsp</i> ${tool.getDate()} &nbsp&nbsp
            <a class="ui" href="#">[Settings]</a>&nbsp&nbsp
            <a class="ui" href="logout.jsp">[Logout]</a></td>
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