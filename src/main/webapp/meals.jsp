<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04.10.2018
  Time: 23:16
  To change this template use File | Settings | File Templates.
--%>
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
<c:forEach var="meal" items="${mealList}">
    <table>
        <tr class="${meal.exceed == true ? 'red' : 'green'}">
            <td>${meal}</td>
        </tr>
    </table>
</c:forEach>
</body>
</html>