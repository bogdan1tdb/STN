<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Userdetails</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<c:if test="${param.id != null}">

<jsp:useBean id="userdetails" class="com.stn.pojo.User"/>
<c:set var="userdetails" value='${user.getUserInfo(param.id)}'/>

    <c:if test="${userdetails.getId() > 0}">

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td class="center"><h1 style="color: ${user.classColor(userdetails.getUserClass())} ">${e:forHtml(userdetails.getUserName())}</h1></td>
    </tr>
    <tr>
        <td class="center">
            <table class="black" border="1" style="margin-top: 5pt; width: 90%; border-collapse: collapse;">
                <tr>
                    <th colspan='2' style="text-align: left; padding: 3pt">User Information</th>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left; width: 80pt">Join Date</td>
                    <td class='row' style="text-align: left">${tool.formatDate(userdetails.getJoinDate(),2)}</td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">Last Seen</td>
                    <td class='row' style="text-align: left">${tool.formatDate(userdetails.getLastSeen(),2)}</td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">Avatar</td>
                    <td class='row' style="text-align: left"><img src="${e:forHtmlAttribute(userdetails.getAvatar())}" class="profile"></td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">User Class</td>
                    <td class='row' style="text-align: left; color: ${user.classColor(userdetails.getUserClass())}">${user.className(userdetails.getUserClass())}</td>
                </tr>
                <c:if test="${userInfo.getUserClass() > 4}">
                    <tr>
                        <td class='row2' style="text-align: left">Ip</td>
                        <td class='row' style="text-align: left;">${userdetails.getIp()}</td>
                    </tr>
                </c:if>
                <tr>
                    <td class='row2' style="text-align: left">Facultate</td>
                    <td class='row' style="text-align: left">${e:forHtml(userdetails.getFacultate())}</td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">Serie</td>
                    <td class='row' style="text-align: left">${e:forHtml(userdetails.getSerie())}</td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">Grupa</td>
                    <td class='row' style="text-align: left">${e:forHtml(userdetails.getGrupa())}</td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">Forums Posts</td>
                    <td class='row' style="text-align: left">0</td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td colspan="2" style="height: 30pt"></td>
    </tr>
</table>
    </c:if>

</c:if>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
