<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Meal</title>
</head>
<body>

<h2><a href="meals?action=showMeals">Back</a></h2>

<form action="meals" method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <p>Date/Time</p>
    <input title="Date/Time" type="datetime-local" name="datetime"
           value="<c:out value="${meal.dateTime}" />"/>
    <p>Description</p>
    <input title="Description" type="text" name="description" value="<c:out value="${meal.description}" />"/>
    <p>Calories</p>
    <input title="Calories" type="text" name="calories" value="<c:out value="${meal.calories}" />"/>
    <input type="submit" value="OK">
</form>
</body>
</html>