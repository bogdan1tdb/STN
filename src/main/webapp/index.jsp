<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Home</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<jsp:useBean id="facultate" class="com.stn.helpers.FacultateHelper"/>
<jsp:useBean id="stats" class="com.stn.pojo.Stats"/>
<jsp:useBean id="newsb" class="com.stn.helpers.NewsHelper"/>
<c:set var="useri" value='${user.getUsers()}'/>
<c:set var="facultati" value='${facultate.getFacultati()}'/>
<c:set var="serii" value='${facultate.getSerii()}'/>
<c:set var="grupe" value='${facultate.getGrupe()}'/>
<c:set var="newsinfo" value="${newsb.getNews(userInfo.getIdSerie(),3)}"/>

${stats.setStats(facultati,serii,grupe,useri)}

<table class="black" style="margin-top: 8pt; width: 570pt">

    <tr>
        <td style="padding-top: 10pt; padding-bottom: 15pt">
            <h2 style="padding-left: 20pt">Recent News</h2>
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
            <a href="/news_archive.jsp" style="color: #99ccff; text-decoration: none;">-- News Archive --</a>&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${(userInfo.getUserClass() == 2 || userInfo.getUserClass() > 4 ) && userInfo.getIdGrupa() != 0 }">
            <a href="javascript:void(0);" style="color: #99ccff; text-decoration: none;" onclick="return addNewsForm(${userInfo.getIdSerie()});" id="newsform">-- Add announcement --</a>
            </c:if>
            ${sessionScope.error}
            <!-- <a href="#" style="color: #66ba5b; text-decoration: none">-- News Archive --</a> -->
        </td>
    </tr>
            </table>
        </td>

    </tr>

    <tr>

        <td style="padding-bottom: 15pt">
            <h2 style="padding-left: 20pt">Online Users (last 5 minutes)</h2>
            <table class="black" border="0" style="width: 94%; background-color: #353535">
                <tr>
                    <td class="left" style="padding: 10pt">
                        <b>
                            <c:set var="cnt" value='${0}'/>
                            <c:forEach items="${useri}" var="u">
                                <c:if test="${tool.userIsOnline(u.getLastSeen()) == true}">
                                    <c:if test="${cnt != 0}">
                                        ,&nbsp;<a href="/userdetails.jsp?id=${u.getId()}" style="color: ${user.classColor(u.getUserClass())};text-decoration: none">${u.getUserName()}</a>
                                        <c:if test="${u.getDonor() == 1}">
                                            <img src="img/star.gif" alt="Donor" style="display: inline; vertical-align: bottom" title="Donor">
                                        </c:if>
                                    </c:if>
                                    <c:if test="${cnt == 0}">
                                        <a href="/userdetails.jsp?id=${u.getId()}" style="color: ${user.classColor(u.getUserClass())};text-decoration: none">${u.getUserName()}</a>
                                        <c:if test="${u.getDonor() == 1}">
                                            <img src="img/star.gif" alt="Donor" style="display: inline; vertical-align: bottom" title="Donor">
                                        </c:if>
                                    </c:if>
                                    <c:set var="cnt" value='${1}'/>
                                </c:if>

                            </c:forEach>
                        </b>
                    </td>
                </tr>
            </table>
        </td>
    </tr>

    <tr>

        <td style="padding-bottom: 15pt">
            <h2 style="padding-left: 20pt">Stats</h2>
            <table class="black" style="width: 94%; background-color: #353535">
                <tr>
                    <td class="center" style="padding-top: 10pt;padding-bottom: 10pt">
                        <table class="black" border="1" align='center' style="border-collapse: collapse;">
                            <tr>
                                <td class="row2" style="text-align: left">Online since</td>
                                <td class="row" style="text-align: left">19-04-2018</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left">Users</td>
                                <td class="row" style="text-align: left">${stats.getUserCount()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left">Newest member</td>
                                <td class="row" style="text-align: left">
                                    <a href='/userdetails.jsp?id=${stats.getLatestUserId()}' style='text-decoration: none; color: ${user.classColor(stats.getLatestUserUserClass())}'> ${e:forHtml(stats.getLatestUserUsername())}</a>
                                    <c:if test="${stats.getLatestUserUserClass() == 0}">
                                        <img src="img/disabled_small.png" alt="Disabled" style="display: inline; vertical-align: bottom; margin-left: 1pt" title="Disabled">
                                    </c:if>
                                    <c:if test="${stats.getLatestUserDonor() == 1}">
                                        <img src="img/star.gif" alt="Donor" style="display: inline; vertical-align: bottom" title="Donor">
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left">Facultati</td>
                                <td class="row" style="text-align: left">${stats.getFacultatiCount()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left">Serii</td>
                                <td class="row" style="text-align: left">${stats.getSeriiCount()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left">Grupe</td>
                                <td class="row" style="text-align: left">${stats.getGrupeCount()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left">Resurse</td>
                                <td class="row" style="text-align: left">0</td>
                            </tr>
                        </table>
                    </td>

                    <td class="center" style="padding-top: 10pt;padding-bottom: 10pt">
                        <table class="black" border="1" align='center' style="border-collapse: collapse;">
                            <tr>
                                <td class="row2" style="text-align: left"><b>Student</b></td>
                                <td class="row" style="text-align: left; width: 25pt">${stats.getStudents()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left"><b style="color: #b52db5">Sef de Grupa</b></td>
                                <td class="row" style="text-align: left">${stats.getSefDeGrupa()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left"><b style="color: #089f00">VIP</b></td>
                                <td class="row" style="text-align: left">${stats.getVips()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left"><b style="color: #ffa00b">Guest of Honour</b></td>
                                <td class="row" style="text-align: left">${stats.getGuestOfHonour()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left"><b style="color: #5d56ef">Moderator</b></td>
                                <td class="row" style="text-align: left">${stats.getModerators()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left"><b style="color: #ff0026">Administrator</b></td>
                                <td class="row" style="text-align: left">${stats.getAdministrators()}</td>
                            </tr>
                            <tr>
                                <td class="row2" style="text-align: left"><b style="color: #A83838">Owner</b></td>
                                <td class="row" style="text-align: left">${stats.getOwners()}</td>
                            </tr>

                        </table>
                    </td>

                </tr>
            </table>
        </td>
    </tr>

</table>

<c:remove var="error" scope="session" />

<%@ include file="structure/footer.jsp" %>
</body>
</html>