<%--
  Created by IntelliJ IDEA.
  User: Dell
  Date: 6/2/2023
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>${action}</h1>
<a href="/product?action=create">Create Product</a>
<c:if test="${requestScope['products'].size() != 0}">
    <table border="1">
        <tr>
            <td>Id</td>
            <td>Name</td>
            <td>price</td>
            <td>quantity</td>
            <td>category</td>
        </tr>
        <c:forEach items="${products}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.category.name}</td>
                <%-- Khi nhân nút edit, sẽ gưi lên đường dẫn /product và chạy vào doGet
                     Với action là edit và id là idProduct --%>
                <td><a href="/product?action=edit&id=${product.id}">Edit</a> </td>
                <td><a href="/product?action=delete&id=${product.id}" onclick="return confirm('Do you want to remove ${product.name}?')">Delete</a> </td>
            </tr>
        </c:forEach>
        <tr>

        </tr>
    </table>
</c:if>

</body>
</html>