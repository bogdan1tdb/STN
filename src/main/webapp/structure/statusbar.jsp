<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table class="black" style="margin-top: 5pt; width: 570pt">
    <tr>
        <td class="left">
            Welcome, <a href='/userdetails.jsp?id=${sessionScope.userId}' style="text-decoration: none"><b style="color: ${user.classColor(userInfo.getUserClass())}">${e:forHtml(userInfo.getUserName())}</b></a>
        </td>
        <td class="right">
            <a class="ui" href="/invite.jsp" style="color: #387FA8">Invite a friend</a>
        </td>
    </tr>
    <tr>
        <td class="left">
            <i class="material-icons" style="font-size: 10pt; vertical-align: bottom; color: #cc33ff">layers</i>&nbsp<a class="ui" href="/group.jsp">Grupa mea</a>&nbsp&nbsp
            <i class="material-icons" style="font-size: 10pt; vertical-align: bottom; color: #2db300">date_range</i>&nbsp<a class="ui" href="/orar.jsp">Orar</a>
        </td>
        <td class="right">
            <i class="material-icons" style="font-size: 9pt; vertical-align: bottom; color: #ffa00b">mail</i>&nbsp<a class="ui" href="javascript:void(0);">0</a> &nbsp&nbsp
            <i class="material-icons" style="font-size: 9pt; vertical-align: bottom; color: #b3daff">schedule</i> <div id="time" style="display: inline"></div> &nbsp&nbsp
            <a class="ui" href="/edit_profile.jsp">[Settings]</a>&nbsp&nbsp
            <a class="ui" href="/LogoutProcess">[Logout]</a></td>
    </tr>
</table>

<table class="menu">
    <tr>
     <td class="menubar"><a class="ui2" href="/index.jsp">Home</a></td>
        <td class="menubar"> <a class="ui3" href="javascript:void(0);">Files</a></td>
        <td class="menubar"><a class="ui3" href="javascript:void(0);">Add</a></td>
        <td class="menubar"><a class="ui3" href="javascript:void(0);">Forums</a></td>
        <td class="menubar"><a class="ui3" href="javascript:void(0);">Top 10</a></td>
        <td class="menubar"><a class="ui2" href="/wiki.jsp">Wiki</a></td>
        <c:if test="${userInfo.getUserClass() > 4}">
        <td class="menubar"><a class="ui2" href="/cpanel.jsp">Cpanel</a></td>
        </c:if>
    </tr>
</table>