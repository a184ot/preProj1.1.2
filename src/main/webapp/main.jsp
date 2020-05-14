<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login User</title>
</head>
<body>
<div align="center">
    <form action="admin" method="post">
        <table>
            <caption>
                <h2>
                    User login form
                </h2>
            </caption>
            <tr>
                <th>Login:</th>
                <td>
                    <input type="text" name="login" size="45"
<%--                           value="<c:out value='${user.name}' />"--%>
                    />
                </td>
            </tr>
            <tr>
                <th> Password:</th>
                <td>
                    <input type="text" name="password" size="45"
<%--                           value="<c:out value='${user.email}' />"--%>
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <button onclick="location.href='login'" ;>Login</button>
                </td>
            </tr>
        </table>
    </form>
</div>
<hr>
<div style="text-align: center;">
    <h2>
    </h2>
</div>
<%--<div align="center" class="form">

    <h1>Login</h1><br>
    <form method="post" action="">

        Login<input type="text" required placeholder="login" name="login"><br>
        Password<input type="password" required placeholder="password" name="password"><br><br>
        <input class="button" type="submit" value="Enter">

    </form>
</div>--%>
</body>
</html>
