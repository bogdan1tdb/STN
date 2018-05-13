<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Wiki</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>


<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Reguli generale</b></legend><br/>
                <ul>
                    <li>Respecta toti studentii si userii acestei comunitati.</li>
                    <li>Site-ul este destintat studentilor,va rugam sa nu va faceti cont daca momentan nu sunteti in postura de student.</li>
                    <li>Falsificarea carnetelor/actelor doveditoare care atesta ca sunteti student este interzisa.</li>
                    <li>Intrarea in posesie a mai multor conturi pe acest site este interzisa.</li>
                    <li>Utilizarea comunitatii in alt scop poate duce la banarea tuturor utilizatorilor din acea grupa/serie/etc.</li>
                    <li>Insistam ca datele personale sa fie cele reale (acestea vor putea fi vazute decat de administratori).</li>
                </ul>
            </fieldset><br/><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">Informatii utilizator</b></legend><br/>
                <b>Cum pot sa schimb fontul,sa adaug emotes etc.?</b><br/><br/>
                Accesati <a class="ui" href="/bbcode_legend.jsp" style="color: #99ccff">acest</a> link pentru a vedea lista intreaga de optiuni.<br/><br/><br/>

                <b>Care sunt clasele de utilizatori?</b><br/><br/>
                <table style="border-spacing: 2pt">
                    <tr>
                        <td style="background-color: #353535"><b>Student</b></td>
                        <td>Clasa pe care o primeste un student in momentul in care se inregistreaza.</td>
                    </tr>
                    <tr>
                        <td style="background-color: #353535"><b style="color: #b52db5">Sef de Grupa</b></td>
                        <td>Clasa rezervata sefului de grupa.Maxim 2/grupa.</td>
                    </tr>
                    <tr>
                        <td style="background-color: #353535"><b style="color: #089f00">VIP</b></td>
                        <td>Studenti care au obtinuit rezultate de exceptie la olimpiade/concursuri/etc.</td>
                    </tr>
                    <tr>
                        <td style="background-color: #353535"><b style="color: #ffa00b">Guest of Honour</b></td>
                        <td>Persoane care au contribuit la dezvoltarea site-ului.</td>
                    </tr>
                    <tr>
                        <td style="background-color: #353535"><b style="color: #5d56ef">Moderator</b></td>
                        <td>Persoane care se ocupa intretinerea comunitatii.</td>
                    </tr>
                    <tr>
                        <td style="background-color: #353535"><b style="color: #ff0026">Administrator</b></td>
                        <td>Persoane care se ocupa cu administrarea site-ului.</td>
                    </tr>
                    <tr>
                        <td style="background-color: #353535"><b style="color: #A83838">Owner</b></td>
                        <td>Fondatorii comunitatii.</td>
                    </tr>
                </table>
            </fieldset><br/><br/>

            <fieldset style='background-color: #2c2c2c; margin:auto; width: 93%; text-align: left; border: solid 1px #62635f'>
                <legend><b style="color: white">STN Features</b></legend>
                    <br/><br/>
                <table class="black" border="1" align='center' style="border-collapse: collapse;">
                    <thead>
                    <tr>
                        <td class="row2"><b>Feature</b></td>
                        <td class="row"><b>Status</b></td>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="row2">Login</td>
                        <td class="row" style="color: #089f00">Enabled</td>
                    </tr>
                    <tr>
                        <td class="row2">Register</td>
                        <td class="row" style="color: #089f00">Enabled</td>
                    </tr>
                    <tr>
                        <td class="row2">Recover Password</td>
                        <td class="row" style="color: #089f00">Enabled</td>
                    </tr>
                    <tr>
                        <td class="row2">Invite System</td>
                        <td class="row" style="color: #089f00">Enabled</td>
                    </tr>
                    <tr>
                        <td class="row2">School Module</td>
                        <td class="row" style="color: #54bbbb">Beta</td>
                    </tr>
                    <tr>
                        <td class="row2">Forum</td>
                        <td class="row" style="color: #ff0026">TBD</td>
                    </tr>
                    <tr>
                        <td class="row2">Profile Settings</td>
                        <td class="row" style="color: #089f00">Enabled</td>
                    </tr>
                    <tr>
                        <td class="row2">News</td>
                        <td class="row" style="color: #54bbbb">Beta</td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td class="row" colspan="2">Website Stabilty : <p style="display: inline; color: #089f00">Good</p></td>
                    </tr>
                    </tfoot>
                </table>
                <br/>
                <p>Site version : 0.2.0</p>
                <br/>
            </fieldset><br/>

        </td>
    </tr>

</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
