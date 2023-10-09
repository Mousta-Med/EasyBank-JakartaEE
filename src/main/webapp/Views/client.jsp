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
    <div class="search-form">
        <form action="ClientServlet?action=get" method="POST">
            <input type="text" class="search-input" name="code" placeholder="Enter Client Code">
            <button type="submit" class="search-button">Search</button>
        </form>
    </div>
    <c:if test="${not empty clients or not empty message}">
        <div style="width: 70%">
            <div class="table_title">
                <h1>Clients List</h1>
                <a id="showFormButton" class="add-button">Add Client</a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>CODE</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Mobile</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="client" items="${clients}">
                    <tr>
                        <td>${client.code}</td>
                        <td>${client.firstName}</td>
                        <td>${client.lastName}</td>
                        <td>${client.birthDate}</td>
                        <td>${client.mobile}</td>
                        <td>${client.address}</td>
                        <td>
                            <a href="ClientServlet?action=edit&code=<c:out value="${client.code}"/>"
                               class="update-button">Update</a>
                            <a href="ClientServlet?action=delete&code=<c:out value="${client.code}"/>"
                               class="delete-button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <div>
                <div id="overlay" class="overlay"></div>
                <div id="popupForm" class="popup">
                    <form action="ClientServlet?action=insert" method="post">
                        <h2 style="margin-bottom: 15px">Add Client</h2>
                        <label for="code">CODE:</label>
                        <input type="text" id="code" name="code" required><br><br>

                        <label for="firstName">First Name:</label>
                        <input type="text" id="firstName" name="firstName" required><br><br>

                        <label for="lastName">Last Name:</label>
                        <input type="text" id="lastName" name="lastName" required><br><br>

                        <label for="birthDate">Birth Date:</label>
                        <input type="date" id="birthDate" name="birthDate" required><br><br>

                        <label for="mobile">Mobile:</label>
                        <input type="tel" id="mobile" name="mobile" required><br><br>

                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" required><br><br>

                        <input type="submit" value="Add Client">
                        <button type="button" id="closeFormButton">Close</button>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty client}">
        <div style="width: 70%">
            <div class="table_title">
                <h1>Client</h1>
            </div>
            <table>
                <thead>
                <tr>
                    <th>CODE</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Birth Date</th>
                    <th>Mobiel</th>
                    <th>Address</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${client.code}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                    <td>${client.mobile}</td>
                    <td>${client.address}</td>
                    <td>
                        <a href="ClientServlet?action=edit&code=<c:out value="${client.code}"/>" class="update-button">Update</a>
                        <a href="ClientServlet?action=delete&code=<c:out value="${client.code}"/>"
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
