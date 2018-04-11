<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Reset Password</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>
<jsp:useBean id="user" class="com.stn.helpers.UserHelper"/>
<jsp:useBean id="recover" class="com.stn.helpers.RecoverHelper"/>

${user.verifyAcces(pageContext.request,pageContext.response)}


<c:set var="test" value="${recover.checkToken(param.token)}" />

<c:if test="${test == null}">
    <table class="black" style="margin-top: 30pt;">
        <tr>
            <td class="center" style="padding: 20pt">
                The token was not found. <br/>
                Please check your email and try again!
            </td>
        </tr>
    </table>
</c:if>

<c:if test="${test != null}">
    <form name="recover" action="ResetProcess" method="post">
        <table class="black" style="margin-top: 30pt;">
            <tr>
                <td colspan="2" class="center" style="padding-top: 12pt">
                    Va rugam sa introduceti noua parola.<br/>
                    Veti fi redirectionat catre pagina de login in cazul unei resetari cu succes.
                </td>
            </tr>
            <tr>
                <td class="right" style="padding-left: 30pt; padding-top: 8pt">Password:</td>
                <td style="padding-right: 30pt; padding-top: 8pt"><input type="password" name="password1" value="" size="35"></td>
            </tr>
            <tr>
                <td class="right" style="padding-left: 30pt; padding-top: 8pt">Confirm Password:</td>
                <td style="padding-right: 30pt; padding-top: 8pt"><input type="password" name="password2" value="" size="35"></td>
            </tr>
            <tr>
                <td class="center" colspan="2"><b style="color: red; display: inline">${sessionScope.error}</b></td>
            </tr>
            <tr>
                <td colspan="2" class="center" style="padding-top: 20pt"><input name="ResetPassword" type="submit" value="Reset Password">
            </tr>
            <tr>
                <td colspan="2" height="24">
                    <input type="hidden" name="token" value="${param.token}">
                </td>
            </tr>
        </table>
    </form>
</c:if>

<c:remove var="error" scope="session" />

<%@ include file="structure/footer.jsp" %>

</body>
</html>
