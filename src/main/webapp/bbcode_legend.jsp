<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>BBCode Legend</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<%@ include file="structure/beans.jsp" %>
${user.verifyAcces(pageContext.request,pageContext.response)}
<%@ include file="structure/statusbar.jsp" %>

<table class="black" style="margin-top: 8pt; width: 570pt">

    <tr>
        <td class="center"><h1 style="color: ${user.classColor(userdetails.getUserClass())} ">BBCode Legend</h1>
        </td>
    </tr>

<tr>
    <td class="center">

        <table class="black" border="1" style="margin-top: 5pt; border-collapse: collapse;">

            <tr>
                <td class='row2' style="text-align: center;">[b]Random word.[/b]</td>
                <td class='row' style="text-align: center">${tool.formatText('[b]Random word.[/b]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[i]Random word.[/i]</td>
                <td class='row' style="text-align: center">${tool.formatText('[i]Random word.[/i]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[u]Random word.[/u]</td>
                <td class='row' style="text-align: center">${tool.formatText('[u]Random word.[/u]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[center]Random word.[/center]</td>
                <td class='row' style="text-align: center">${tool.formatText('[center]Random word.[/center]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[color=#54bbbb]Random word.[/color]</td>
                <td class='row' style="text-align: center">${tool.formatText('[color=#54bbbb]Random word.[/color]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[size=4]Random word.[/size]</td>
                <td class='row' style="text-align: center">${tool.formatText('[size=4]Random word.[/size]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:)</td>
                <td class='row' style="text-align: center">${tool.formatText(':)')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:|</td>
                <td class='row' style="text-align: center">${tool.formatText(':|')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:|</td>
                <td class='row' style="text-align: center">${tool.formatText(':(')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:O</td>
                <td class='row' style="text-align: center">${tool.formatText(':O')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:D</td>
                <td class='row' style="text-align: center">${tool.formatText(':D')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:o)</td>
                <td class='row' style="text-align: center">${tool.formatText(':o)')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:beer:</td>
                <td class='row' style="text-align: center">${tool.formatText(':beer:')}</td>
            </tr>
        </table>

    <br/><br/>
    </td>

</tr>

</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
