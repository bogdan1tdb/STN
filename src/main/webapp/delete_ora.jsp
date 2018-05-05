<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <%@ include file="structure/header.jsp" %>
    <%@ include file="structure/beans.jsp" %>
    <jsp:useBean id="notes" class="com.stn.helpers.OrarHelper"/>
    <c:set var="ora" value="${notes.getOra(param.id,notes.getOrar(userInfo.getIdGrupa()))}"/>
    <title>Delete ${ora.getName()}</title>
</head>
<body>
${user.verifyAcces(pageContext.request,pageContext.response)}
<form action="DeleteOraProcess" method="post">
    <table class="black">
        <input type="hidden" name="id_ora" value="${sessionScope.id_ora}${param.id}">
        <th>Sunteti pe cale sa stergeti ora ${ora.getName()}, cu tipul ${ora.getTip_act()}, <c:if test="${ora.getSgr()!=0}">al semigrupei ${ora.getSgr()}</c:if> cu durata ${ora.getDurata()} din ziua de ${ora.getZi()} al grupei ${ora.getGrupa()}</th>
        <tbody>
        <tr><td><b>Sunteti sigur ca vreti sa faceti asta?</b></td></tr>
        <tr><td>Odata cu stergerea acestei ore totul despre ea va disparea si colegii dumneavoastra vor primii un email cu informatiile pierdute!</td></tr>
        <td><input type="submit" value="Da!"></td>
        <td><a href="orar.jsp" class="buton">Mai bine nu.</a></td>
        </tbody>
    </table>
</form>
</body>
</html>
