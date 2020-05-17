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
<div align="center">
    <table>
        <caption><h2>Actions</h2></caption>
        <tr>
            <c:if test="${role == 'admin'}">
            <th align="left">
                <form action="admin" method="post">
                    <button name="" value="">List All Users</button>
                </form>
            </th>
            <th align="left">
                <form action="create" method="post">
                    <button name="" value="">Add New User</button>
                </form>
            </th>
            </c:if>
            <th align="left">
                <form action="logout" method="post">
                    <button name="" value="">Logout</button>
                </form>
            </th>
        </tr>
    </table>
</div>
</body>
</html>
