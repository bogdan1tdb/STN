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
                <td class='row2' style="text-align: center;">[align=left]Random word.[/align]</td>
                <td class='row' style="text-align: center">${tool.formatText('[align=left]Random word.[/align]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[color=#54bbbb]Random word.[/color]</td>
                <td class='row' style="text-align: center">${tool.formatText('[color=#54bbbb]Random word.[/color]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[size=13]Random word.[/size]</td>
                <td class='row' style="text-align: center">${tool.formatText('[size=13]Random word.[/size]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[url]https://www.google.ro[/url]</td>
                <td class='row' style="text-align: center">${tool.formatText('[url]https://www.google.ro[/url]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[url=https://www.google.ro]Link Oficial Google[/url]</td>
                <td class='row' style="text-align: center">${tool.formatText('[url=https://www.google.ro]Link Oficial Google[/url]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[email]schoolingtheinternet@gmail.com[/email]</td>
                <td class='row' style="text-align: center">${tool.formatText('[email]schoolingtheinternet@gmail.com[/email]')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">[img]http://schoolingtheinter.net/img/profile.png[/img]</td>
                <td class='row' style="text-align: center">${tool.formatText('[img]http://schoolingtheinter.net/img/profile.png[/img]')}</td>
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
                <td class='row2' style="text-align: center;">:(</td>
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
                <td class='row2' style="text-align: center;">:-/</td>
                <td class='row' style="text-align: center">${tool.formatText(':-/')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">;)</td>
                <td class='row' style="text-align: center">${tool.formatText(';)')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:p</td>
                <td class='row' style="text-align: center">${tool.formatText(':p')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">'(</td>
                <td class='row' style="text-align: center"><img src='/img/smilies/cry.gif' class='smile'></td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:-(</td>
                <td class='row' style="text-align: center">${tool.formatText(':-(')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">8)</td>
                <td class='row' style="text-align: center">${tool.formatText('8)')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:king:</td>
                <td class='row' style="text-align: center">${tool.formatText(':king:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:hbd:</td>
                <td class='row' style="text-align: center">${tool.formatText(':hbd:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:book:</td>
                <td class='row' style="text-align: center">${tool.formatText(':book:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:chef:</td>
                <td class='row' style="text-align: center">${tool.formatText(':chef:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:hi:</td>
                <td class='row' style="text-align: center">${tool.formatText(':hi:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:horse:</td>
                <td class='row' style="text-align: center">${tool.formatText(':horse:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:beer:</td>
                <td class='row' style="text-align: center">${tool.formatText(':beer:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:happy2:</td>
                <td class='row' style="text-align: center">${tool.formatText(':happy2:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:gathering:</td>
                <td class='row' style="text-align: center">${tool.formatText(':gathering:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:flowers:</td>
                <td class='row' style="text-align: center">${tool.formatText(':flowers:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:console:</td>
                <td class='row' style="text-align: center">${tool.formatText(':console:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:cake:</td>
                <td class='row' style="text-align: center">${tool.formatText(':cake:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:beer2:</td>
                <td class='row' style="text-align: center">${tool.formatText(':beer2:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:cap:</td>
                <td class='row' style="text-align: center">${tool.formatText(':cap:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:chair:</td>
                <td class='row' style="text-align: center">${tool.formatText(':chair:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:clap:</td>
                <td class='row' style="text-align: center">${tool.formatText(':clap:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:clap2:</td>
                <td class='row' style="text-align: center">${tool.formatText(':clap2:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:drunk:</td>
                <td class='row' style="text-align: center">${tool.formatText(':drunk:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:fishing:</td>
                <td class='row' style="text-align: center">${tool.formatText(':fishing:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:hang:</td>
                <td class='row' style="text-align: center">${tool.formatText(':hang:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:mml:</td>
                <td class='row' style="text-align: center">${tool.formatText(':mml:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:pepsi:</td>
                <td class='row' style="text-align: center">${tool.formatText(':pepsi:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:rant:</td>
                <td class='row' style="text-align: center">${tool.formatText(':rant:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:rb:</td>
                <td class='row' style="text-align: center">${tool.formatText(':rb:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:rip:</td>
                <td class='row' style="text-align: center">${tool.formatText(':rip:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:rofl:</td>
                <td class='row' style="text-align: center">${tool.formatText(':rofl:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:shoot2:</td>
                <td class='row' style="text-align: center">${tool.formatText(':shoot2:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:kissing:</td>
                <td class='row' style="text-align: center">${tool.formatText(':kissing:')}</td>
            </tr>
            <tr>
                <td class='row2' style="text-align: center;">:pope:</td>
                <td class='row' style="text-align: center">${tool.formatText(':pope:')}</td>
            </tr>

        </table>

    <br/><br/>
    </td>

</tr>

</table>

<%@ include file="structure/footer.jsp" %>
</body>
</html>
