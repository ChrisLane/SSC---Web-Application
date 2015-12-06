<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Email Client</title>
</head>
<body>
<% if (session.getAttribute("username") == null | !request.isRequestedSessionIdValid()) {
    // This is a new visitor! Send them to log in
    response.sendRedirect("login.html");
} else {
    // The user is already signed in, forward to email form
    response.sendRedirect("EmailForm.html");
}%>
</body>
</html>
