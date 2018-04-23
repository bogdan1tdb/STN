<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Control Panel</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Moderator Panel</b></legend>
                <br/><br/>
                <table class="black" border="1" align='center' style="border-collapse: collapse;">
                    <thead>
                    <tr>
                        <td class="row"><b>Command</b></td>
                        <td class="row" style="text-align: center"><b>Description</b></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="row"><a class="ui" href="#" style="color: #b3daff; text-decoration: line-through;">[User List]</a> </td>
                        <td class="row">Lista de utilizatori.</td>
                    </tr>
                    </tbody>
                </table>
                <br/>
            </fieldset><br/>

            <c:if test="${userInfo.getUserClass() > 5}">

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Administrator Panel</b></legend>
                <br/><br/>
                <table class="black" border="1" align='center' style="border-collapse: collapse;">
                    <thead>
                    <tr>
                        <td class="row"><b>Command</b></td>
                        <td class="row" style="text-align: center"><b>Description</b></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="row"><a class="ui" href="panel/applications.jsp" style="color: #b3daff">[Aplicatii]</a> </td>
                        <td class="row">Aplicatiile trimise de catre utilizatori.</td>
                    </tr>
                    <tr>
                        <td class="row"><a class="ui" href="#" style="color: #b3daff; text-decoration: line-through;">[DB Cleanup]</a> </td>
                        <td class="row">Curata baza de date de campurile vechi de la parole resetate/log-uri etc.</td>
                    </tr>
                    <tr>
                        <td class="row"><a class="ui" href="panel/editfacultati.jsp" style="color: #b3daff">[Edit Facultati]</a> </td>
                        <td class="row">Adauga o grupa,serie sau facultate.</td>
                    </tr>
                    </tbody>
                </table>
                <br/>
            </fieldset><br/>

            </c:if>

        </td>
    </tr>
</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
