<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>News Archive</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<jsp:useBean id="newsb" class="com.stn.helpers.NewsHelper"/>
<c:set var="newsinfo" value="${newsb.getNews(userInfo.getIdSerie(),-1)}"/>

<table class="black" style="margin-top: 8pt; width: 570pt">

    <tr>
        <td style="padding-top: 10pt; padding-bottom: 15pt">
            <table class="black" cellspacing="0" style="width: 94%" style="background-color: #2c2c2c;">
                <tr id="news">
                    <td style="background-color: #2c2c2c; padding: 8pt; text-align: center">
                        <table width="100%" border="0" cellpadding="5" cellspacing="7" style="background-color: #353535;">

                            <c:forEach items="${newsinfo}" var="nw">

                                <tr>
                                    <td width=100% colspan="2" style="background-color: #2c2c2c; text-align: left"><b style="color : #99ccff">${tool.formatDate(nw.getDate(),4)} - ${e:forHtml(nw.getTitle())}</b></td>
                                </tr>
                                <tr>
                                    <td width=100% style="text-align: left">
                                            ${tool.formatText(e:forHtml(nw.getBody()))}
                                    </td>
                                </tr>

                            </c:forEach>

                        </table>

                        <hr>
                    </td>
                </tr>
            </table>
        </td>

    </tr>
</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>