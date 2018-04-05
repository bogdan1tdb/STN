<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%@ include file="structure/meta.jsp" %>
    <title>Home</title>
    <%@ include file="structure/header.jsp" %>
</head>
<body>

<%@ include file="structure/statusbar.jsp" %>

${user.LogOut(pageContext.request,pageContext.response)}

<%@ include file="structure/footer.jsp" %>
</body>
</html>
