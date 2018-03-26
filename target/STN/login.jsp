<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Login</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<form action="login.jsp" method="post">
<table class="login">
    <tr>
    <td style="padding-top: 30pt; padding-left: 30pt;">User:</td>
    <td class="left" style="padding-top: 30pt; padding-right: 30pt;"><input type="text" name="user" value="" size="35"></td>
    </tr>

    <tr>
        <td style="padding-left: 30pt;">Password:</td>
        <td class="left" style="padding-right: 30pt;"><input type="password" name="parola" value="" size="35"></td>
    </tr>

    <tr>
        <td></td>
        <td class="left"><input id="remember_me" type="checkbox">Remember Me</td>
    </tr>

    <tr>
        <td colspan="2" class="center" style="padding-bottom: 30pt;"><br/>You have 0 from 10 attemtps left for the login session</td>
    </tr>

    <tr>
        <td colspan="2" class="right"><a class="ui" href="recover.jsp">Recover password</a></td>
    </tr>
</table>
</form>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
