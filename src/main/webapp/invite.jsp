<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Invite</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<c:if test="${userInfo.getUserClass() == 2}">

    <table class="black" style="margin-top: 8pt; width: 570pt">
        <tr>
            <td class="center">

                <table class="black" style="margin-top: 8pt; width: 50%">
                    <tr>
                        <td class="center" style="padding: 5pt">
                            In cazul in care studentul nu si-a facut cont,il poti invita din acest meniu.<br/>Introdu adresa de email pe care doresti sa ii trimiti invitatia!<br/><br/>
                            <form action="AdminAppProcess"  method="post">
                                <input type="hidden" name="idgrupa" value="${userInfo.getIdGrupa()}">
                                <input type="hidden" name="idserie" value="${userInfo.getIdSerie()}">
                                <input type="hidden" name="idfacultate" value="${userInfo.getIdFacultate()}">
                                <input type="text" name="email" value="" size="40"><br/>
                                    ${sessionScope.result}<br/><br/>
                                <input type="submit" value="Send Invite">
                            </form>
                        </td>
                    </tr>
                </table>
                <br/><br/>
            </td>
        </tr>
    </table>

</c:if>

<c:if test="${userInfo.getUserClass() != 2}">
    <table class="black" style="margin-top: 8pt; width: 570pt">
        <tr>
            <td class="center">

                <table class="black" style="margin-top: 8pt; width: 50%">
                    <tr>
                        <td class="center" style="padding: 5pt">
                            Numai sefii de grupa au abilitatea de a trimite invitatii!
                        </td>
                    </tr>
                </table>
                <br/><br/>
            </td>
        </tr>
    </table>
</c:if>

<c:remove var="result" scope="session" />
<%@ include file="structure/footer.jsp" %>
</body>
</html>