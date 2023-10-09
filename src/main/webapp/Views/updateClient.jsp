<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Client</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/Resources/css/style.css">
</head>
<body>
<div class="header">
    <div>
        <li>
            <a class="logo" href="<%= request.getContextPath() %>/index.jsp">EasyBank</a>
        </li>
    </div>
    <div class="nav__links">
        <ul>
            <li><a class="current" href="<%= request.getContextPath() %>/ClientServlet?action=list">Client</a></li>
            <li><a href="<%= request.getContextPath() %>/EmployeServlet?action=list">Employe</a></li>
        </ul>
    </div>
    <div class="menu">
        <img src="Resources/images/icon-hamburger.svg" alt="ham" class="burger">
    </div>
</div>
<div class="body_div">
    <c:if test="${empty errorMessage}">
        <div style="width: 70%">
            <form action="ClientServlet?action=update&code=<c:out value="${client.code}"/>" method="post">
                <h1 style="margin-bottom: 15px; text-align: center;">Update Client</h1>
                <label for="code">CODE:</label>
                <input type="text" id="code" name="code" value="${client.code}" readonly><br><br>

                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value="${client.firstName}" required><br><br>

                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value="${client.lastName}" required><br><br>

                <label for="birthDate">Birth Date:</label>
                <input type="date" id="birthDate" name="birthDate" value="${client.birthDate}" required><br><br>

                <label for="mobile">Mobile:</label>
                <input type="tel" id="mobile" name="mobile" value="${client.mobile}" required><br><br>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" value="${client.address}" required><br><br>

                <input type="submit" value="Update Client">
                <a href="<%= request.getContextPath() %>/ClientServlet?action=list">
                    <button class="back" type="button">Back</button>
                </a>
            </form>
        </div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>
</div>
<script src="<%= request.getContextPath() %>/Resources/js/script.js"></script>
</body>
</html>
