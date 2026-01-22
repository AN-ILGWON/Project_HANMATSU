<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Check</title>
</head>
<body>
    <h2>Session Status</h2>
    <p>User ID: ${sessionScope.userid}</p>
    <p>Role: ${sessionScope.role}</p>
    <p>Nickname: ${sessionScope.nickname}</p>
    <hr>
    <h3>All Attributes</h3>
    <ul>
    <%
        java.util.Enumeration<String> names = session.getAttributeNames();
        while(names.hasMoreElements()) {
            String name = names.nextElement();
            out.println("<li>" + name + ": " + session.getAttribute(name) + "</li>");
        }
    %>
    </ul>
</body>
</html>
