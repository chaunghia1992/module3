<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/2/2023
  Time: 4:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:if test="${requestScope['message'] != null}">
    <span>${message}</span>
</c:if>
<a href="/product">Back</a><br>
<form action="/product?action=edit" method="post">
    <input type="hidden" name="id" value="${product.id}">
    <label for="name">Name</label>
    <input type="text" name="name" id="name" value="${product.name}" />
    <label for="price">Price</label>
    <input type="text" name="price" id="price" value="${product.price}" />
    <label for="quantity">Quantity</label>
    <input type="text" name="quantity" id="quantity" value="${product.quantity}" />
    ${product.category.id}
    <select name="category" id="category">
        <option value="">--None--</option>
        <c:forEach items="${categorys}" var="category">
            <c:if test="${product.category.id == category.id}">
                <option selected value="${category.id}">${category.name}</option>
            </c:if>
            <c:if test="${product.category.id != category.id}">
                <option value="${category.id}">${category.name}</option>
            </c:if>
        </c:forEach>
    </select>
    <button type="submit">Edit</button>
</form>
</body>
</html>