<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Recover Password</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<form action="RecoverProcess" method="post">
    <table class="black" style="margin-top: 2%">
        <tr>
            <td><br/></td>
        </tr>
        <tr>
            <td class="center">
                Va rugam sa introduceti adresa de email care corespunde contului dumneavoastra.<br/>
                Veti primi pe email-ul solicitat un link cu un cod unic.<br/>
                Completati formularul primit pentru a va reseta parola!<br/>
            </td>
        </tr>
        <tr>
            <td class="center" style="padding-top: 10pt"><input type="text" name="email" value="" size="35"></td>
        </tr>
        <tr>
            <td class="center">${sessionScope.error}</td>
        </tr>
        <tr>
            <td class="center" style="padding-top: 8pt"><input name="recover" type="submit" id="Recover" value="Recover">
        </tr>
        <tr>
            <td><br/></td>
        </tr>
    </table>
</form>

<c:remove var="error" scope="session" />

<%@ include file="structure/footer.jsp" %>
</body>
</html>
