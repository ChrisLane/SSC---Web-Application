<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Client</title>
</head>
<body>
<% if (session.getAttribute("username") == null) {
    response.sendRedirect("login.html");
} else {
    response.sendRedirect("EmailForm.html");
}%>
</body>
</html>
