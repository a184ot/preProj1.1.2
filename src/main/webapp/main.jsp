<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login User</title>
</head>
<body>
<div align="center">
    <form action="login" method="post">
        <table>
            <caption>
                <h2>
                    User login form
                </h2>
            </caption>
            <tr>
                <th>Login:</th>
                <td>
                    <input type="text" name="name" size="45"
                    />
                </td>
            </tr>
            <tr>
                <th> Password:</th>
                <td>
                    <input type="text" name="password" size="45"
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
</body>
</html>
