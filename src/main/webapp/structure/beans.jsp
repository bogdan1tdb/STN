<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="user" class="com.stn.helpers.UserHelper"/>
<jsp:useBean id="tool" class="com.stn.utils.Tools"/>
<jsp:useBean id="userInfo" class="com.stn.pojo.User"/>

${user.verifyAuthentication(pageContext.request,pageContext.response)}
${user.updateLastSeen(sessionScope.userId)}
<c:set var="userInfo" value="${user.getUserInfo(sessionScope.userId)}"/>
<c:set var="userclass" value="${userInfo.getUserClass()}" scope="session"  />
