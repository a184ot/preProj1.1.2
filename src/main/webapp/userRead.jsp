<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Read</title>
</head>
<body>
<div align="center">
    <table width="80%">
        <caption><h2>My data list</h2></caption>
        <tr>
            <th align="left">ID</th>
            <th align="left">Name</th>
            <th align="left">Password</th>
            <th align="left">Age</th>
            <th align="left">Email</th>
            <th align="left">Role</th>
<%--            <th align="left">Actions</th>--%>
        </tr>

        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.role}"/></td>
            </tr>
        </c:forEach>
    </table>
</div>
<hr>
<div style="text-align: center;">

    <h2>
        <button onclick="location.href='logout'" ;>Logout User</button>
        &nbsp;&nbsp;&nbsp;
        <c:if test = "${user.role == admin}">

        <button onclick="location.href='create'" ;>Add New User</button>
        &nbsp;&nbsp;&nbsp;
        <button onclick="location.href='admin'" ;>List All Users</button>
        </c:if>
    </h2>

</div>

</body>
</html>
