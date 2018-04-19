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

<form name="register" action="ApplicationProcess" method="post">
    <table class="black" style="margin-top: 30pt;">
        <tr>
            <td colspan="2" class="center" style="padding-top: 12pt">All fields are required!</td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Nume:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="lastname" value="${sessionScope.lastname}" size="50"></td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Prenume:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="firstname" value="${sessionScope.firstname}" size="50"></td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Facultate:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="facultate" value="${sessionScope.faculty}" size="50"></td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Serie:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="serie" value="${sessionScope.serie}" size="50"></td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Grupa:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="grupa" value="${sessionScope.grupa}" size="50"></td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Act/Legitimatie:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="document" value="${sessionScope.document}" size="50"></td>
        </tr>
        <tr>
            <td class="right" style="padding-left: 30pt; padding-top: 8pt">Email:</td>
            <td style="padding-right: 30pt; padding-top: 8pt"><input type="text" name="email" value="${sessionScope.email}" size="50"></td>
        </tr>
        <tr>
            <td class="center" colspan="2">${sessionScope.error}</td>
        </tr>
        <tr>
            <td class="left" colspan="2" style="padding-top: 5pt;">
                <ul>
                    <li>In cazul actului/legitimatie trebuie sa adaugati un link catre o poza a documentului.</li>
                    <li>Exista o limita de 2 sefi pe grupa.</li>
                    <li>Daca aplicatia va fi acceptata veti primi un email cu un link pentru inregistrare.</li>
                </ul>
            </td>
        </tr>
        <tr>
            <td colspan="2" class="center" style="padding-top: 20pt"><input name="apply" type="submit" value="Send application">
        </tr>
        <tr>
            <td colspan="2" height="24"></td>
        </tr>
    </table>
</form>

<c:remove var="error" scope="session" />
<c:remove var="firstname" scope="session" />
<c:remove var="lastname" scope="session" />
<c:remove var="email" scope="session" />
<c:remove var="faculty" scope="session" />
<c:remove var="serie" scope="session" />
<c:remove var="grupa" scope="session" />
<c:remove var="document" scope="session" />

<%@ include file="structure/footer.jsp" %>

</body>
</html>
