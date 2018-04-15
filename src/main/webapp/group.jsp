<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Grupa</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<table class="black" style="margin-top: 8pt; width: 570pt">
    <tr>
        <td class="center"><h1>Grupa 1337</h1></td>
    </tr>
    <tr>
        <td class="center">
            <table class="black" border="0" style="background-color: #2c2c2c; width: 94%">
                <tr>
                    <td colspan="6"><br/></td>
                </tr>
                <tr>
                    <td class="left" colspan="6"><b style="color: #b52db5">Sef de grupa</b></td>
                </tr>
                <tr>
                    <td colspan="6">
                        <hr color="#131313" size="1">
                    </td>
                </tr>
                <tr>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: #b52db5">Mihai</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="6"><br/><br/></td>
                </tr>
                <tr>
                    <td class="left" colspan="6"><b>Students</b></td>
                </tr>
                <tr>
                    <td colspan="6">
                        <hr color="#131313" size="1">
                    </td>
                </tr>
                <tr>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">tdbarashi</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">MaD</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">John_Mcgibson</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                </tr>
                <tr>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">Catalina</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">George</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">$WAG420_Blaz3</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                </tr>
                <tr>
                    <td class="left" style="padding-left: 20pt"><a href="#" style="text-decoration: none"><b style="color: white">RandomName</b></a></td>
                    <td><a class="ui" href="#">[PM]</a></td>
                    <td class="left" style="padding-left: 20pt"></td>
                    <td></td>
                    <td class="left" style="padding-left: 20pt"></td>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="6"><br/><br/><br/><br/><br/></td>
                </tr>
                <tr>
                    <td class="center" colspan="6">
                        Codul Grupei:<br/> <input type="text" name="group_code" value="9fMBsCxeX05NOkyY4fAqfC2Xtv0x6YuIP7" size="35">
                    </td>
                </tr>
                <tr>
                    <td colspan="6"><br/><br/></td>
                </tr>
            </table>
        </td>
    </tr>
    <tr>
        <td><br/><br/></td>
    </tr>
</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>