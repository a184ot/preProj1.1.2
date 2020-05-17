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
            <th align="left">Actions 2</th>
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
                        <%--                    <button onclick="location.href='update?id=<c:out value='${user.id}'/>'" ;>Edit</button>--%>
                        <%--                    &nbsp;&nbsp;&nbsp;--%>
                        <%--                    <button formmethod="post" onclick="location.href='delete?id=<c:out value='${user.id}'/>'">Delete</button>--%>
                    <form action="update" method="post">
                        <button name="id" value="<c:out value='${user.id}'/>">Edit</button>
                    </form>
                </td>
                <td>
                    <form action="delete" method="post">
                        <button name="id" value="<c:out value='${user.id}'/>">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
<hr>
<%--<div style="text-align: center;">
    <h2>
        <tr>
            <td>
                <button formmethod="post" onclick="location.href='create'" ;>Add New User</button>
                <button formmethod="post" onclick="location.href='admin'" ;>List All Users</button>
                &lt;%&ndash;                <button onclick="location.href='logout'" ;>Logout</button>&ndash;%&gt;
                <form action="user" method="post">
                    <button name="foo" value="upvote">User page2</button>
                </form>
                &lt;%&ndash;        <button formmethod="post" onclick="location.href='user'" ;>User page</button>&ndash;%&gt;
                &lt;%&ndash;        &nbsp;&nbsp;&nbsp;&ndash;%&gt;
                <p>
                <form action="user" method="post">
                    <button name="foo" value="upvote">User page2</button>
                    <button name="foo" value="upvote">User page2</button>
                </form>
                <form action="user" method="post">
                    <button name="foo" value="upvote">User page4</button>
                    <button name="foo" value="upvote">User page4</button>
                </form>
                </p>
            </td>
        </tr>
    </h2>
</div>--%>
<div align="center">
    <table>
        <caption><h2>Admin Actions</h2></caption>
        <tr>
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
            <th align="left">
                <form action="user" method="post">
                    <button name="" value="">User Page</button>
                </form>
            </th>
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
