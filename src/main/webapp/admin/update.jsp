<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <form action="update" method="post">
            <table>
                <caption>
                    <h2>
                            Edit User
                    </h2>
                </caption>
                    <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                <tr>
                    <th>User Name:</th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${user.name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>User Password:</th>
                    <td>
                        <input type="text" name="password" size="45"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th> Age:</th>
                    <td>
                        <input type="text" name="age" size="45"
                               value="<c:out value='${user.age}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td>
                        <input type="text" name="email" size="45"
                               value="<c:out value='${user.email}' />"
                        />
                    </td>
                </tr>

                <tr>
                    <th>Role:</th>
                    <td>
                        <select name="role">
                            <c:if test="${user.role == 'user'}">
                                <option selected value="user">user</option>
                                <option value="admin">admin</option>
                            </c:if>
                            <c:if test="${user.role == 'admin'}">
                                <option value="user">user</option>
                                <option selected value="admin">admin</option>
                            </c:if>
                        </select>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center">
                        <button onclick="location.href='update'" ;>Save User</button>
                    </td>
                </tr>
            </table>
        </form>
</div>
<hr>
<div style="text-align: center;">
    <h2>
        <button onclick="location.href='create'" ;>Add New User</button>
        &nbsp;&nbsp;&nbsp;
        <button onclick="location.href='admin'" ;>List All Users</button>
    </h2>
</div>
</body>
</html>