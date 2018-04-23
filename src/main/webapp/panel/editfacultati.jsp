<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="/structure/meta.jsp" %>
    <title>Edit Facultati</title>
    <%@ include file="/structure/header.jsp" %>
</head>
<body>
<%@ include file="/structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="/structure/statusbar.jsp" %>

<jsp:useBean id="facultate" class="com.stn.helpers.FacultateHelper"/>
<c:set var="serii" value='${facultate.getSerii()}'/>
<c:set var="grupe" value='${facultate.getGrupe()}'/>

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Facultate</b></legend>
                <br/>

                <table style="width: 95%; margin-right: auto ; margin-left: auto ;">
                    <tr>
                        <form name="search" action="../FacultateProcess" method="post">
                            <td class="right" style="width: 67%"> Cauta o facultate : <input type="text" name="facultate" value="" size="35"> </td>
                            <td class="left">
                                <input name="search" type="submit" value="Search">
                                <input type="hidden" name="type" value="search">
                            </td>
                        </form>
                    </tr>

                    <tr>
                        <form name="add" action="../FacultateProcess" method="post">
                            <td class="right" style="padding-top: 5pt"> Adauga o facultate : <input type="text" name="facultate" value="" size="35"> </td>
                            <td class="left">
                                <input name="add" type="submit" value="Add">
                                <input type="hidden" name="type" value="add">
                            </td>
                        </form>
                    </tr>

                    <tr>
                        <td class="center" colspan="2">${sessionScope.result}</td>
                    </tr>

                </table><br/>

            </fieldset><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Serie</b></legend>
                <br/>

                <table style="width: 95%; margin-right: auto ; margin-left: auto ;">
                    <tr>
                            <td class="right" style="width: 35%">Alege facultatea : </td>
                            <td class="left"> ${facultate.generateOptions(facultate.getFacultati())}  </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                              <c:forEach items="${serii}" var="serie">
                                  <script>
                                      addSerie(${serie.getIdSerie()},'${serie.getNume()}',${serie.getIdFacultate()});
                                  </script>
                              </c:forEach>
                        </td>

                    </tr>

                    <tr>
                        <td class="center" colspan="2" id="serii">

                        </td>
                    </tr>

                    <tr>
                        <td class="center" colspan="2">${sessionScope.result2}</td>
                    </tr>

                </table><br/>

            </fieldset><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Grupa</b></legend>
                <br/>

                <table style="width: 95%; margin-right: auto ; margin-left: auto ;">
                    <tr>
                        <td class="right" style="width: 35%">Alege facultatea : </td>
                        <td class="left"> ${facultate.generateOptions2(facultate.getFacultati())}  </td>
                    </tr>

                    <tr>
                        <td class="right" style="width: 35%" id="alegefac"></td>
                        <td class="left" id="selectfac"></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <c:forEach items="${grupe}" var="grupa">
                                <script>
                                    addGrupa(${grupa.getIdGrupa()},'${grupa.getNume()}',${grupa.getIdSerie()});
                                </script>
                            </c:forEach>
                        </td>

                    </tr>

                    <tr>
                        <td class="center" colspan="2" id="tdgrupe"></td>
                    </tr>

                    <tr>
                        <td class="center" colspan="2">${sessionScope.result3}</td>
                    </tr>

                </table><br/>

            </fieldset><br/>

        </td>
    </tr>

</table>


<c:remove var="result" scope="session" />
<c:remove var="result2" scope="session" />
<c:remove var="result3" scope="session" />
<%@ include file="/structure/footer.jsp" %>
</body>
</html>
