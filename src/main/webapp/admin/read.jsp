<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Read User</title>
</head>
<body>
<div align="center">
    <table width="80%">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th align="left">ID</th>
            <th align="left">Name</th>
            <th align="left">Password</th>
            <th align="left">Age</th>
            <th align="left">Email</th>
            <th align="left">Role</th>
            <th align="left">Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.age}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.role}"/></td>
                <td>
                    <button onclick="location.href='update?id=<c:out value='${user.id}'/>'" ;>Edit</button>
                    &nbsp;&nbsp;&nbsp;
                    <button onclick="location.href='delete?id=<c:out value='${user.id}'/>'">Delete</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<hr>
<div style="text-align: center;">
    <h2>
        <button onclick="location.href='create'" ;>Add New User</button>
        &nbsp;&nbsp;&nbsp;
        <button onclick="location.href='admin'" ;>List All Users</button>
        &nbsp;&nbsp;&nbsp;
        <button onclick="location.href='logout'" ;>Logout</button>
        &nbsp;&nbsp;&nbsp;
        <button onclick="location.href='user'" ;>User page</button>
        &nbsp;&nbsp;&nbsp;
<%--        <button onclick="location.href='delete'" ;>Delete All Users</button>--%>

    </h2>
</div>

</body>
</html>
