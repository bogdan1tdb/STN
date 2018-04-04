<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Login</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<jsp:useBean id="t" class="com.stn.utils.LoginHelper"/>
<form action="LoginProcess" method="post">
<table class="black" style="margin-top: 30pt;">
    <tr>
    <td style="padding-top: 30pt; padding-left: 30pt;">User:</td>
    <td class="left" style="padding-top: 30pt; padding-right: 30pt;"><input type="text" name="user" value="" size="35"></td>
    </tr>

    <tr>
        <td style="padding-left: 30pt;">Password:</td>
        <td class="left" style="padding-right: 30pt;"><input type="password" name="password" value="" size="35"></td>
    </tr>

    <tr>
        <td></td>
        <td class="left"><input id="remember_me" type="checkbox">Remember Me</td>
    </tr>

    <tr>
        <td colspan="2" class="center"><input class="login" type="submit" name="Login" value="Login"></td>
    </tr>

    <tr>
        <td colspan="2" class="center"><b style="color: red; display: inline">${error}</b></td>
    </tr>

    <tr>
        <td colspan="2" class="center" style="padding-bottom: 25pt;">
            <br/>You have ${t.getAttempts(pageContext.request)} from 10 attemtps left for the login session.<br/>10 failed logins in a row will result in banning your current IP.
        </td>
    </tr>

    <tr>
        <td class="left"><a class="ui" href="register.jsp">Register( <b style="color: green; display: inline">OPEN</b> )</a></td>
        <td class="right">
            <a class="ui" href="apply.jsp">Apply for membership</a>&nbsp;&nbsp;&nbsp;<a class="ui" href="recover.jsp">Recover password</a>
        </td>
    </tr>
</table>
</form>
<%@ include file="structure/footer.jsp" %>
</body>
</html>
