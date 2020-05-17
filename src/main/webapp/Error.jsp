<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Error</title>
</head>
<body>
<div style="text-align: center;">
    <h1>Error</h1>
    <h2>You do not have access rights<br/> </h2>
</div>
<div align="center">
    <table>
        <caption><h2>Actions</h2></caption>
        <tr>
            <c:if test="${role == 'user'}">
                <th align="left">
                    <form action="user" method="post">
                        <button name="" value="">User Page</button>
                    </form>
                </th>
            </c:if>
            <c:if test="${role != 'user'}">
                <th align="left">
                    <form action="" method="post">
                        <button name="" value="">Login page</button>
                    </form>
                </th>
            </c:if>
        </tr>
    </table>
</div>
</body>
</html>