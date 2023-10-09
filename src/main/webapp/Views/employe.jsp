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
    <div class="search-form">
        <form action="EmployeServlet?action=get" method="POST">
            <input type="text" class="search-input" name="matricule" placeholder="Enter Employe Matricule">
            <button type="submit" class="search-button">Search</button>
        </form>
    </div>
    <c:if test="${not empty employes or not empty message}">
        <div style="width: 70%">
            <div class="table_title">
                <h1>Employes List</h1>
                <a id="showFormButton" class="add-button">Add Employe</a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Matricule</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Mobile</th>
                    <th>Email</th>
                    <th>Recruitment Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="employe" items="${employes}">
                    <tr>
                        <td>${employe.matricule}</td>
                        <td>${employe.firstName}</td>
                        <td>${employe.lastName}</td>
                        <td>${employe.birthDate}</td>
                        <td>${employe.mobile}</td>
                        <td>${employe.email}</td>
                        <td>${employe.recruitmentDate}</td>
                        <td>
                            <a href="EmployeServlet?action=edit&matricule=<c:out value="${employe.matricule}"/>"
                               class="update-button">Update</a>
                            <a href="EmployeServlet?action=delete&matricule=<c:out value="${employe.matricule}"/>"
                               class="delete-button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <div id="overlay" class="overlay"></div>
                <div id="popupForm" class="popup">
                    <form action="EmployeServlet?action=insert" method="post">
                        <h2 style="margin-bottom: 15px">Add Employe</h2>
                        <label for="matricule">Matricule:</label>
                        <input type="text" id="matricule" name="matricule" required><br><br>

                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" name="firstName" required><br><br>

                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" required><br><br>

                        <label for="birthDate">Birth Date:</label>
                        <input type="date" id="birthDate" name="birthDate" required><br><br>

                        <label for="mobile">Mobile:</label>
                        <input type="tel" id="mobile" name="mobile" required><br><br>

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" required><br><br>

                        <label for="recruitmentDate">Recruitment Date:</label>
                        <input type="date" id="recruitmentDate" name="recruitmentDate" required><br><br>

                        <input type="submit" value="Add Employee">
                        <button type="button" id="closeFormButton">Close</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty employe}">
        <div style="width: 70%">
            <div class="table_title">
                <h1>Employes</h1>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Matricule</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Mobile</th>
                    <th>Email</th>
                    <th>Recruitment Date</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${employe.matricule}</td>
                    <td>${employe.firstName}</td>
                    <td>${employe.lastName}</td>
                    <td>${employe.birthDate}</td>
                    <td>${employe.mobile}</td>
                    <td>${employe.email}</td>
                    <td>${employe.recruitmentDate}</td>
                    <td>
                        <a href="EmployeServlet?action=edit&matricule=<c:out value="${employe.matricule}"/>"
                           class="update-button">Update</a>
                        <a href="EmployeServlet?action=delete&matricule=<c:out value="${employe.matricule}"/>"
                           class="delete-button">Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>
</div>
<script src="<%= request.getContextPath() %>/Resources/js/script.js"></script>
<c:if test="${not empty message}">
    <script>alert("${message}")</script>
</c:if>
</body>
</html>
