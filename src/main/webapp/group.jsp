<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Grupa</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<jsp:useBean id="facultate" class="com.stn.helpers.FacultateHelper"/>
<c:set var="useri" value='${user.getUsers()}'/>

<c:if test="${userInfo.getIdGrupa() == 0}">

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td class="center">

            <table class="black" style="margin-top: 8pt; width: 50%">
            <tr>
                <td class="center" style="padding: 5pt">
                    Momentan nu faci parte din nici o grupa!<br/>Daca ai un cod primit de la seful de grupa il poti introduce mai jos!<br/><br/>
                    <form action="JoinGrupaProcess"  method="post">
                        <input type="text" name="gtoken" value="" size="40"><br/>
                            ${sessionScope.result}<br/><br/>
                        <input type="submit" value="Join Group">
                    </form>
                </td>
            </tr>
            </table>
            <br/><br/>
        </td>
    </tr>
</table>

</c:if>

<c:if test="${userInfo.getIdGrupa() != 0}">

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td class="center"><h1>Grupa ${userInfo.getGrupa()}</h1></td>
    </tr>
    <tr>
        <td class="center">
            <table class="black" border="0" style="background-color: #2c2c2c; width: 94%">
                <tr>
                    <td colspan="3"><br/></td>
                </tr>
                <tr>
                    <td class="left" colspan="3"><b style="color: #b52db5">Sef de grupa</b></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <hr color="#131313" size="1">
                    </td>
                </tr>
                <tr>
                    <c:set var="i" value='0'/>
                    <c:forEach items="${useri}" var="sef">
                        <c:if test="${sef.getUserClass() == 2 && sef.getIdGrupa() == userInfo.getIdGrupa()}">
                        <td class="left" style="width: 33%; padding-left: 15pt">
                            <a href="userdetails.jsp?id=${sef.getId()}" style="text-decoration: none"><b style="color: ${user.classColor(sef.getUserClass())}">${sef.getUserName()}</b></a>
                            <a class="ui" href="#" style="color: #b3daff; padding-left: 20pt">[PM]</a>
                        </td>
                            <c:set var="i" value='${i+1}'/>
                        </c:if>
                    </c:forEach>

                </tr>

                <tr>
                    <td colspan="3"><br/><br/></td>
                </tr>
                <tr>
                    <td class="left" colspan="3"><b>Students</b></td>
                </tr>
                <tr>
                    <td colspan="3">
                        <hr color="#131313" size="1">
                    </td>
                </tr>

                <c:set var="i" value='0'/>
                <c:forEach items="${useri}" var="sef">
                    <c:if test="${ (sef.getUserClass() > 2 || sef.getUserClass() < 2 ) && sef.getIdGrupa() == userInfo.getIdGrupa()}">
                        <c:if test="${i == 0}">
                            <tr>
                        </c:if>
                        <td class="left" style="width: 33%; padding-left: 15pt">
                            <a href="userdetails.jsp?id=${sef.getId()}" style="text-decoration: none"><b style="color: ${user.classColor(sef.getUserClass())}">${sef.getUserName()}</b></a>
                            <c:if test="${sef.getUserClass() == 0}">
                                <img src="img/disabled_small.png" alt="Disabled" style="display: inline-block; vertical-align: bottom; padding-left: 1pt" title="Disabled">
                            </c:if>
                            <a class="ui" href="#" style="color: #b3daff; margin-left: 20pt">[PM]</a>
                        </td>
                        <c:set var="i" value='${i+1}'/>
                        <c:if test="${i == 3}">
                            </tr>
                        </c:if>
                    </c:if>
                </c:forEach>

                <tr>
                    <td colspan="3"><br/><br/><br/><br/><br/></td>
                </tr>
                <tr>
                    <td class="center" colspan="3">
                        Codul Grupei:<br/> <input type="text" name="group_code" value="${facultate.getTokenGrupa(userInfo.getIdGrupa())}" size="35">
                    </td>
                </tr>

                <tr>
                    <td class="center" colspan="3">
                        <br/>
                        <form action="LeaveGrupaProcess" method="post">
                            <input type="submit" value="Pareseste grupa">
                        </form>
                        <br/>
                    </td>
                </tr>

                <tr>
                    <td colspan="3"><br/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td><br/><br/></td>
    </tr>
</table>

</c:if>

<c:remove var="result" scope="session" />
<%@ include file="structure/footer.jsp" %>
</body>
</html>