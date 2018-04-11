<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Apply</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<jsp:useBean id="user" class="com.stn.helpers.UserHelper"/>
${user.verifyAcces(pageContext.request,pageContext.response)}

<table class="black" style="margin-top: 30pt;">
    <tr>
        <td class="center" style="padding: 30pt">
            Registrations are currently open!<br/>
            Click <a class="ui" href="register.jsp">here</a> to go to the registraion page.
        </td>
    </tr>
</table>
<%@ include file="structure/footer.jsp" %>

</body>
</html>
