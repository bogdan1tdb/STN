<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Adaugare Ora</title>
    <%@ include file="structure/header.jsp" %>
    <script>
        window.onload = function () {
            var agr = document.getElementById("selector");
            agr.onchange = function () {
                var elem = document.getElementById("g");
                var elem2=elem.getElementsByTagName("select")
                if (agr.value != "Curs") {
                    elem.style.display = "block";
                    elem2[0].selectedIndex = "1";
                }
                else {
                    elem.style.display = "none";
                    elem2[0].selectedIndex = "0";
                }
            }
        }
    </script>
    <style>
        body {
            text-align: center;
        }

        form {
            display: flex;
            flex-flow: row;
        }

        input {
            height: 10pt;
        }

    </style>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
<h1>Adaugare ora</h1>
${user.verifyAcces(pageContext.request,pageContext.response)}

    <c:set var="grupa" value='${user.getUserInfo(sessionScope.userId)}'/>
    <c:if test="${grupa.getId() > 0}">
        <form action="AddOra" method="post">

            <label>Nume materie</label><input type="text" placeholder="Numele materiei" name="nume_m"
                                              value="${sessionScope.nume_m}">
            <label>Durata</label><input type="text" placeholder="HH:MM-HH:MM" name="durata"
                                        value="${sessionScope.durata}">
            <label>Tip activitate</label><select id="selector" name="tip" value="${sessionScope.tip}">
            <option selected>Curs</option>
            <option>Seminar</option>
            <option>Laborator</option>
        </select>
            <div id="g" style="display: none"><label>Semigrupa</label><select name="semig" value="${sessionScope.semig}">
                <option selected value="0">toti</option>
                <option>1</option>
                <option>2</option>
            </select></div>
            <label>Sala</label><input type="text" placeholder="Sala" name="sala" value="${sessionScope.sala}">
            <label>Nume Profesor</label><input type="text" placeholder="Nume profesor" name="nume_p"
                                               value="${sessionScope.nume_p}">
            <label>Zi</label><select name="zi" value="${sessionScope.zi}">
            <option selected>1.Luni</option>
            <option>2.Marti</option>
            <option>3.Miercuri</option>
            <option>4.Joi</option>
            <option>5.Vineri</option>
            <option>6.Sambata</option>
            <option>7.Duminica</option>
        </select>
            <label>Saptamana in care se tine ora</label>
            <select name="sapt" value="${sessionScope.sapt}">
                <option selected>Toate</option>
                <option>Para</option>
                <option>Impara</option>
            </select>
            <label>Grupa ${grupa.getGrupa()}</label><input type="hidden" name="id_gr" value="${grupa.getGrupa()}"/>
            <input type="hidden" name="id_user" value="${grupa.getId()}" />
            <input type="submit"  value="Adauga">
        </form>
        ${sessionScope.error}

    </c:if>
<c:remove var="error" scope="session" />
<c:remove var="id_gr" scope="session" />
<c:remove var="id_user" scope="session"/>
<%@ include file="structure/footer.jsp" %>
</body>
</html>
