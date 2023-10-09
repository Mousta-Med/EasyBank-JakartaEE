<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employe</title>
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
            <li><a href="<%= request.getContextPath() %>/ClientServlet?action=list">Client</a></li>
            <li><a class="current" href="<%= request.getContextPath() %>/EmployeServlet?action=list">Employe</a></li>
        </ul>
    </div>
    <div class="menu">
        <img src="Resources/images/icon-hamburger.svg" alt="ham" class="burger">
    </div>
</div>
<div class="body_div">
    <div style="width: 70%">
            <form action="EmployeServlet?action=update&matricule=<c:out value="${employe.matricule}"/>" method="post">
                <h1 style="margin-bottom: 15px; text-align: center;">Update Employe</h1>
                <label for="matricule">Matricule:</label>
                <input type="text" id="matricule" name="matricule" value="${employe.matricule}" readonly><br><br>

                <label for="firstName">First Name:</label>
                <input type="text" id="firstName" name="firstName" value="${employe.firstName}" required><br><br>

                <label for="lastName">Last Name:</label>
                <input type="text" id="lastName" name="lastName" value="${employe.lastName}" required><br><br>

                <label for="birthDate">Birth Date:</label>
                <input type="date" id="birthDate" name="birthDate" value="${employe.birthDate}" required><br><br>

                <label for="mobile">Mobile:</label>
                <input type="tel" id="mobile" name="mobile" value="${employe.mobile}" required><br><br>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${employe.email}" required><br><br>

                <label for="recruitmentDate">Recruitment Date:</label>
                <input type="date" id="recruitmentDate" name="recruitmentDate" value="${employe.recruitmentDate}" required><br><br>

                <input type="submit" value="Update Employee">
                <a href="<%= request.getContextPath() %>/EmployeServlet?action=list"><button class="back" type="button">Back</button></a>
            </form>
    </div>
</div>
<script src="<%= request.getContextPath() %>/Resources/js/script.js"></script>
</body>
</html>
