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

                                <tr id="news_${nw.getIdNews()}">
                                    <td width=100% colspan="2" style="background-color: #2c2c2c; text-align: left" id="title_${nw.getIdNews()}">
                                        <b style="color : #99ccff">${tool.formatDate(nw.getDate(),4)} - </b> <b style="color : #99ccff" id="title_name_${nw.getIdNews()}">${e:forHtml(nw.getTitle())}</b>
                                        <c:if test="${userInfo.getUserClass() == 2 || userInfo.getUserClass() > 4}">
                                            <a class="ui" href="javascript:void(0);" style="color: white" onclick="return editNews(1,${nw.getIdNews()});" id="news_edit_${nw.getIdNews()}">[Edit]</a>
                                            <a class="ui" href="javascript:void(0);" onclick="return deleteNews(${nw.getIdNews()});" style="color: white" >[Delete]</a>
                                        </c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td width=100% style="text-align: left" id="body_${nw.getIdNews()}">
                                            ${tool.formatText(e:forHtml(nw.getBody()))}
                                        <input type="hidden" value="${nw.getBody()}" id="news_body_${nw.getIdNews()}">
                                        <div style="text-align: right; margin-top: 3pt;">
                                            <i>Added by <a href='/userdetails.jsp?id=${nw.getIdUser()}' style="text-decoration: none; color: ${user.classColor(nw.getUserClass())}">${e:forHtml(nw.getUsername())}</a>
                                                on ${tool.formatDate(nw.getDate(),3)}</i>
                                            <c:if test="${nw.getLastEdit() != null}">
                                                <br/>
                                                <i>Last edited on : ${tool.formatDate(nw.getLastEdit(),3)}</i>&nbsp;
                                            </c:if>
                                        </div>
                                        <c:if test="${userInfo.getUserClass() == 2 || userInfo.getUserClass() > 4}">
                                            <div style="text-align: center; margin-top: 3pt"><a class="ui" href="javascript:void(0);" style="color: #99ccff;" onclick=" return editNews(2,${nw.getIdNews()}); " id="news_edit_body_${nw.getIdNews()}">[Edit]</a></div>
                                        </c:if>
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