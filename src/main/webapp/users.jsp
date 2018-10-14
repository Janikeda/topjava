<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Users</h2>
<table border="1" cellpadding="8" cellspacing="0">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Registration date</th>
        <th></th>
        <th></th>
    </tr>
    </thead>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.registered}</td>
            <td><a href="meals?action=update&id=${user.id}">Update</a></td>
            <td><a href="meals?action=delete&id=${user.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>