<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <%@ include file="structure/header.jsp" %>
    <%@ include file="structure/beans.jsp" %>
    <jsp:useBean id="notes" class="com.stn.helpers.OrarHelper"/>
    <c:set var="note" value="${notes.getSchool(param.id,sessionScope.userId)}"/>
    <c:set var="ora" value="${notes.getOra(param.id,notes.getOrar(userInfo.getIdGrupa()))}"/>
    <c:if test="${note.getId()>0}">
        <title>Edit ${ora.getName()}</title>
    </c:if>

</head>
<body>

${user.verifyAcces(pageContext.request,pageContext.response)}

<c:if test="${note.getId()>0}">
<form action="/AddNotes" method="post">
    <fieldset>
        <legend><b>Nume materie:${e:forHtml(ora.getName())}</b></legend>
        <div id="info_div"><b>Nume profesor:${ora.getNume_prof()}</b><br>
            <b>Durata:${ora.getDurata()}</b><br>
            <b>Tip activitate:${ora.getTip_act()}</b><br>
            <c:if test="${ora.getSgr()==0}">
                <b>Cu toata grupa</b><br>
            </c:if>
            <c:if test="${ora.getSgr()!=0}">
                <b>Semigrupa:${ora.getSgr()}</b><br>
            </c:if>
            <b>Are loc in sala:${ora.getSala()}</b><br>
            <b>In fiecare ${ora.getZi()}</b><br>
            <b>Cu grupa ${ora.getGrupa()}</b><br>
            <c:if test="${ora.getSapt()!='toate'}">
            <b>Din saptamana ${ora.getSapt()}</b>
            </c:if>
            <c:if test="${ora.getSapt()=='toate'}">
                <b>In ${ora.getSapt()} saptamanile</b>
            </c:if>
        </div>
        <table class="black" id="info">
            <tbody>
            <tr>
                <input type="hidden" name="name" value="${ora.getName()}">
                <input type="hidden" name="id_ora" value="${param.id}">
                <input type="hidden" name="id_user" value="${userInfo.getId()}">
                <td class="left"><b>Tema</b><textarea name="teme" rows="12" cols="60"
                                           style="overflow: auto">${note.getTema()}</textarea></td>
                <td class="left"><b>Examen</b><textarea name="examen" rows="12" cols="60"
                                           style="overflow: auto">${note.getExamen()}</textarea></td>
                <td class="left"><b>Nota</b><select name="nota">
                    <c:forEach var="i" begin="0" end="10" step="1">
                    <c:if test="${note.getNota()==i||i==0}">
                        <option selected>${i}</option>
                    </c:if>
                    <c:if test="${note.getNota()!=i&&i!=0}">

                    <option>${i}</option>
                    </c:if>
                    </c:forEach>
                </select></td>
                <td class="left"><input type="submit" value="Salveaza schimbari"></td>

            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td class="center" colspan="3"><b style="color: #2db300;">${sessionScope.error}</b></td>
            </tr>
            </tfoot>
        </table>
    </fieldset>
</form>
</c:if>
<%@ include file="structure/footer.jsp" %>
</body>

</html>
