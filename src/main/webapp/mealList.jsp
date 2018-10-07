<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <style>
        .green {
            color: green;
        }

        .red {
            color: red;
        }
    </style>
</head>
<body>
<h2>Meals</h2>

<h3><a href="index.html">Home</a></h3>

<table border="1" cellpadding="3">
    <thead>
    <tr>
        <th>Date/Time</th>
        <th>Description</th>
        <th>Calories</th>
        <th colspan=2>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="meal" items="${mealList}">
        <tr class="${not meal.exceed ? 'green' : 'red'}">
            <td>${meal.dateTime}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="?action=edit&userId=<c:out value="${meal.id}"/>">Edit</a></td>
            <td><a href="?action=delete&userId=<c:out value="${meal.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<p><a href="?action=insert">Add</a></p>

</body>
</html>