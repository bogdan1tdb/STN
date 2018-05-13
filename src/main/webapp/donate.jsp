<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Donate</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<table class="black" style="margin-top: 8pt; width: 570pt">

    <tr>
        <td>
        <br/><br/>
            <table align="center" width="95%" border="0" style="background-color: #353535;">

                <tr>
                    <td class="center">
                        <img src="img/charity.png" style="width: 60%; height: auto">
                        <p style="font-size: 15pt; color: #99ccff">Doneaza si sustine educatia!</p>
                        <p>Iti multim pentru interesul pe care il acorzi site-ului,insa scopul nostru este acela de a oferi o platforma gratuita pentru a ajuta studentii sa isi desfasoare activitaiile scolare
                        intr-un mod cat mai intuitiv.Din pacate exista copii care nu au acces la aceste resurse,viitorul acestora depinzand de spatiile de lucru/materialele/etc. puse la dispozitie.</p>
                        <p>Daca sunteti interesati de acest subiect si doriti sa ajutati,puteti sa accesati link-urile de mai jos pentru a face o donatie.Consideram ca donatia ar trebui facuta fara a primi
                        nimic inapoi,insa in mod simbolic veti primi pe site o steluta (<img src="img/star.gif" alt="Donor" style="display: inline; vertical-align: bottom" title="Donor">) care va fi afista langa numele dvs.</p>
                        <p>Daca ati facut o donatie,puteti sa ne trimiteti mail pe adresa <a class='ui' href='mailto:schoolingtheinternet@gmail.com' style='color: #66ba5b'>schoolingtheinternet@gmail.com</a>
                        cu dovada donatiei si contul dvs va fi modificat in cel mai scurt timp.</p>
                        <p>Save the Children <a class='ui' href='https://www.savethechildren.org/us/what-we-do/global-programs/education' style='color: #99ccff'>https://www.savethechildren.org/us/what-we-do/global-programs/education</a></p>
                        <p>Young Initiative <a class='ui' href='https://www.younginitiative.org/doneaza' style='color: #99ccff'>https://www.younginitiative.org/doneaza</a></p>
                        <p style="font-size: 15pt; color: #99ccff">Va multumim!</p>
                    </td>
                </tr>

            </table>
            <br/><br/>
        </td>
    </tr>


</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>