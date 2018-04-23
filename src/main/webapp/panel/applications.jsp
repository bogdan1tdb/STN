<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/structure/meta.jsp" %>
    <title>Aplicatii</title>
    <%@ include file="/structure/header.jsp" %>
</head>
<body>
<%@ include file="/structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="/structure/statusbar.jsp" %>

<jsp:useBean id="aplicatie" class="com.stn.helpers.ApplicationHelper"/>
<c:set var="aplicatii" value='${aplicatie.getAplicatii()}'/>

<jsp:useBean id="facultate" class="com.stn.helpers.FacultateHelper"/>
<c:set var="facultati" value='${facultate.getFacultati()}'/>
<c:set var="serii" value='${facultate.getSerii()}'/>
<c:set var="grupe" value='${facultate.getGrupe()}'/>

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td class="center"><h1>Aplicatii in curs de asteptare</h1></td>
    </tr>

    <tr>
        <td>

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

        </td>
    </tr>

    <tr>
        <td class="center">
            <table class="black" border="1" style="margin-top: 5pt; width: 90%; border-collapse: collapse;" id="apps">

                <tr>
                    <th style="text-align: left; padding: 3pt; width: 5%">Id</th>
                    <th style="text-align: left; padding: 3pt; width: 60%">Nume si Prenume</th>
                    <th style="text-align: left; padding: 3pt">Extra Info</th>
                    <th style="text-align: left; padding: 3pt">Data</th>
                    <th style="text-align: left; padding: 3pt">Action</th>
                </tr>

                <c:forEach items="${aplicatii}" var="app">
                    <c:if test="${app.getType() == 1}">
                    <tr id="app${app.getAppId()}">
                        <td class='row' style="text-align: left">${app.getAppId()}</td>
                        <td class='row' style="text-align: left">${app.getNume()}&nbsp${app.getPrenume()}</td>
                        <td class='row' style="text-align: center"><a class="ui" href="#" onclick="return addTempAppInfo(${app.getAppId()},'${app.getFacultate()}','${app.getSerie()}','${app.getGrupa()}','${app.getEmail()}','${app.getDocument()}');">[+]</a></td>
                        <td class='row' style="text-align: left">${tool.formatDate(app.getDate(),3)}</td>
                        <td class='row' style="text-align: center">
                            <a class="decline" href="../AdminAppProcess?id=${app.getAppId()}" title="Decline"><i class="material-icons" style="font-size: 12pt; vertical-align: bottom;">clear</i></a>
                            <a class="accept" href="#" onclick="return addTempApp(${app.getAppId()},'${app.getEmail()}',${app.getAppId()},'../AdminAppProcess');" title="Accept"><i class="material-icons" style="font-size: 12pt; vertical-align: bottom;">done</i></a>
                        </td>
                    </tr>
                    </c:if>
                </c:forEach>

            </table>
        </td>
    </tr>

    <tr>
        <td class="center"><h1>Aplicatii acceptate</h1></td>
    </tr>

    <tr>
        <td class="center">
            <table class="black" border="1" style="margin-top: 5pt; width: 90%; border-collapse: collapse;" id="apps">

                <tr>
                    <th style="text-align: left; padding: 3pt; width: 5%">Id</th>
                    <th style="text-align: left; padding: 3pt; width: 60%">Nume si Prenume</th>
                    <th style="text-align: left; padding: 3pt">Extra Info</th>
                    <th style="text-align: left; padding: 3pt">Data</th>
                </tr>

                <c:forEach items="${aplicatii}" var="app">
                    <c:if test="${app.getType() == 3}">
                        <tr id="app${app.getAppId()}">
                            <td class='row' style="text-align: left">${app.getAppId()}</td>
                            <td class='row' style="text-align: left">${app.getNume()}&nbsp${app.getPrenume()}</td>
                            <td class='row' style="text-align: center"><a class="ui" href="#" onclick="return addTempAppInfo(${app.getAppId()},'${app.getFacultate()}','${app.getSerie()}','${app.getGrupa()}','${app.getEmail()}','${app.getDocument()}');">[+]</a></td>
                            <td class='row' style="text-align: left">${tool.formatDate(app.getDate(),3)}</td>
                        </tr>
                    </c:if>
                </c:forEach>

            </table>
        </td>
    </tr>

    <tr>
        <td class="center"><h1>Aplicatii respinse</h1></td>
    </tr>

    <tr>
        <td class="center">
            <table class="black" border="1" style="margin-top: 5pt; width: 90%; border-collapse: collapse;" id="apps">

                <tr>
                    <th style="text-align: left; padding: 3pt; width: 5%">Id</th>
                    <th style="text-align: left; padding: 3pt; width: 60%">Nume si Prenume</th>
                    <th style="text-align: left; padding: 3pt">Extra Info</th>
                    <th style="text-align: left; padding: 3pt">Data</th>
                </tr>

                <c:forEach items="${aplicatii}" var="app">
                    <c:if test="${app.getType() == 2}">
                        <tr id="app${app.getAppId()}">
                            <td class='row' style="text-align: left">${app.getAppId()}</td>
                            <td class='row' style="text-align: left">${app.getNume()}&nbsp${app.getPrenume()}</td>
                            <td class='row' style="text-align: center"><a class="ui" href="#" onclick="return addTempAppInfo(${app.getAppId()},'${app.getFacultate()}','${app.getSerie()}','${app.getGrupa()}','${app.getEmail()}','${app.getDocument()}');">[+]</a></td>
                            <td class='row' style="text-align: left">${tool.formatDate(app.getDate(),3)}</td>
                        </tr>
                    </c:if>
                </c:forEach>

            </table>
        </td>
    </tr>

    <tr>
        <td colspan="2" style="height: 30pt"></td>
    </tr>

</table>

<%@ include file="/structure/footer.jsp" %>
</body>
</html>
