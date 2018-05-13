<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="structure/meta.jsp" %>
<title>Orar grupa</title>
<%@ include file="structure/header.jsp" %>
</head>

<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<jsp:useBean id="userdetails" class="com.stn.pojo.User"/>
<jsp:useBean id="orar2" class="com.stn.helpers.OrarHelper"/>

<c:set var="userdetails" value='${user.getUserInfo(sessionScope.userId)}'/>

<c:if test="${userdetails.getId() > 0}">
    <table class="black" style="margin-top: 8pt; width: 570pt">
        <th style="background: none">Grupa ${e:forHtml(userdetails.getGrupa())}</th>
        <tbody>
        <tr>
            <td><br/>

                <fieldset
                        style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: center; border: solid 1px #62635f'>
                    <legend><b>Orar ${userdetails.getGrupa()}</b></legend>
                    <br/>

                    <table style="width: 95%; margin-right: auto ; margin-left: auto ;">
                        <tbody>
                        <c:if test="${userdetails.getUserClass() == 2||userdetails.getUserClass()==6||userdetails.getUserClass()==7}">

                            <tr id="luni">
                                <td>Luni</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='1.Luni' }">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui"> ${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>

                                </c:forEach>
                            </tr>

                            <tr id="marti">
                                <td>Marti</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='2.Marti'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}
"
                                               class="ui">${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="miercuri">
                                <td>Miercuri</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='3.Miercuri'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                                class="ui">${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="joi">
                                <td>Joi</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='4.Joi'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui">${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="vineri">
                                <td>Vineri</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='5.Vineri'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui">${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="sambata">
                                <td>Sambata</td>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='6.Sambata'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui">${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="duminica">
                                <td>Duminica</td>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='7.Duminica'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}" class="ui"
                                               class="ui">${ore.getName()}</a><a href="javascript:void(0);" onclick="return deleteOra(${ore.getId()})" class="buton">Delete!</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>

                        </c:if>
                        <c:if test="${userdetails.getUserClass()!=2 && userdetails.getUserClass()!=6 && userdetails.getUserClass()!=7}">
                            <tr id="luni">
                                <td>Luni</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='1.Luni' }">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui"> ${ore.getName()}</a></td>

                                    </c:if>

                                </c:forEach>
                            </tr>

                            <tr id="marti">
                                <td>Marti</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='2.Marti'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}
"
                                               class="ui">${ore.getName()}</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="miercuri">
                                <td>Miercuri</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='3.Miercuri'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}">
                                                class="ui">${ore.getName()}</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="joi">
                                <td>Joi</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='4.Joi'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui">${ore.getName()}</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="vineri">
                                <td>Vineri</td>
                                <c:set var="orar" value='${orar2.getOrar(userdetails.getIdGrupa())}'/>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='5.Vineri'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui">${ore.getName()}</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="sambata">
                                <td>Sambata</td>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='6.Sambata'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}"
                                               class="ui">${ore.getName()}</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                            <tr id="duminica">
                                <td>Duminica</td>
                                <c:forEach items="${orar}" var="ore" varStatus="contor">

                                    <c:if test="${ore.getZi()=='7.Duminica'}">
                                        <td class="${ore.getZi()}"
                                            title="${ore.getDurata()},${ore.getSala()},${ore.getSgr()},${ore.getSapt()}">
                                            <a href="ora1.jsp?id=${ore.getId()}" class="ui"
                                               class="ui">${ore.getName()}</a></td>

                                    </c:if>
                                </c:forEach>
                            </tr>
                        </c:if>
                        </tbody>
                        <c:if test="${userdetails.getUserClass()==2 || userdetails.getUserClass() ==6 || userdetails.getUserClass() ==7}">

                            <a href="ora.jsp" class="buton">Add</a>
                            <div class="ui">${sessionScope.error}</div>

                        </c:if>
                        <c:if test="${userdetails.getUserClass()!=2 && userdetails.getUserClass()!=6 && userdetails.getUserClass()!=7}">

                            <div class="ui">${sessionScope.error}</div>

                        </c:if>
                    </table>
                </fieldset>
            </td>
        </tr>
        </tbody>
    </table>

</c:if>
<c:remove var="error" scope="session"/>
<%@ include file="structure/footer.jsp" %>
</body>
</html>
