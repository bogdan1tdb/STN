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
        <td class="center"><h1 style="color: ${user.classColor(userdetails.getUserClass())} ">${e:forHtml(userdetails.getUserName())}

        <c:if test="${userdetails.getUserClass() == 0}">
            <img src="img/disabled.png" alt="Disabled" style="display: inline-block; vertical-align: bottom; padding-left: 3pt" title="Disabled">
        </c:if>
        </h1>
        </td>
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
                    <td class='row' style="text-align: left;"><img src="${e:forHtmlAttribute(userdetails.getAvatar())}" class="profile"></td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">User Class</td>
                    <td class='row' style="text-align: left; color: ${user.classColor(userdetails.getUserClass())}">${user.className(userdetails.getUserClass())}</td>
                </tr>
                <tr>
                    <td class='row2' style="text-align: left">Gender</td>
                    <td class='row' style="text-align: left;">
                         <p style="display: inline; vertical-align: middle">${user.genderName(userdetails.getGender())}</p>
                            ${user.genderImage(userdetails.getGender())}
                    </td>
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

    <c:if test="${userInfo.getUserClass() > 4}">
        <jsp:useBean id="facultate" class="com.stn.helpers.FacultateHelper"/>
        <c:set var="facultati" value='${facultate.getFacultati()}'/>
        <c:set var="serii" value='${facultate.getSerii()}'/>
        <c:set var="grupe" value='${facultate.getGrupe()}'/>

        <script>
            setidAplicatie(${param.id});
        </script>

        <c:forEach items="${facultati}" var="facultate">
            <script>
                addFacultate(${facultate.getIdFacultate()},'${facultate.getNume()}');
            </script>
        </c:forEach>

        <c:forEach items="${serii}" var="serie">
            <script>
                addSerie(${serie.getIdSerie()},'${serie.getNume()}',${serie.getIdFacultate()});
            </script>
        </c:forEach>

        <c:forEach items="${grupe}" var="grupa">
            <script>
                addGrupa(${grupa.getIdGrupa()},'${grupa.getNume()}',${grupa.getIdSerie()});
            </script>
        </c:forEach>


    <tr>
        <td>
            <table class="black" border="1" style="margin-top: 15pt; border-collapse: collapse;">
                <tr>
                    <td class='row2' style="text-align: center;">Setari Facultate</td>
                    <td class="" id="temp2">
                        <script>
                            addTempFacInfo('../UpdateFacProcess');
                        </script>
                    </td>
                </tr>
                <form name="search" action="ProfileProcess" method="post">
                <tr>
                    <td class='row2' style="text-align: center;">Change Userclass</td>
                    <td class='row' style="text-align: center">
                        <select name="userclass">
                            <option disabled selected value="-1">--</option>
                            <option value="0">Disabled</option>
                            <option value="1">Student</option>
                            <option value="2">Sef de Grupa</option>
                            <option value="3">VIP</option>
                            <option value="4">Guest of Honour</option>
                       <c:if test="${userInfo.getUserClass() > 5}">
                            <option value="5">Moderator</option>
                       </c:if>
                       <c:if test="${userInfo.getUserClass() > 6}">
                            <option value="6">Administrator</option>
                            <option value="7">Owner</option>
                       </c:if>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="row" style="text-align: center" colspan="2">
                        <input type="hidden" name="userid" value="${param.id}">
                        <br/>
                        <input name="save_changes" type="submit" value="Save Changes">
                        <br/><br/>${sessionScope.error2}
                    </td>
                </tr>
                </form>

            </table>
        </td>
    </tr>

        <c:remove var="error2" scope="session" />
    </c:if>

    <tr>
        <td colspan="2" style="height: 30pt"></td>
    </tr>
</table>
    </c:if>

</c:if>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
