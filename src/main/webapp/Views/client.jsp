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
            <li><a class="current" href="<%= request.getContextPath() %>/client?action=list">Client</a></li>
            <li><a href="<%= request.getContextPath() %>/employe?action=list">Employe</a></li>
        </ul>
    </div>
    <div class="menu">
        <img src="Resources/images/icon-hamburger.svg" alt="ham" class="burger">
    </div>
</div>

<div class="body_div">
    <c:if test="${not empty message}">
        <div id="alertModal" class="modal">
            <div class="modal-content">
                <span class="close">&times;</span>
                <h2>Alert</h2>
                <p>${message}</p>
            </div>
        </div>
    </c:if>
    <div class="search-form">
        <form action="client?action=get" method="POST">
            <div class="search-input-container">
                <input type="text" class="search-input" name="code" placeholder="Enter Client Code">
                <button type="submit" class="search-button">Search</button>
            </div>
        </form>
    </div>
    <div style="width: 70%">
        <c:if test="${not empty clients}">
            <div class="table-title">
                <h1>Clients List</h1>
                <a class="add-button showFormButton">Add Client</a>
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
                <c:forEach var="client" items="${clients}" varStatus="loop">
                    <tr>
                        <td>${client.code}</td>
                        <td>${client.firstName}</td>
                        <td>${client.lastName}</td>
                        <td>${client.birthDate}</td>
                        <td>${client.mobile}</td>
                        <td>${client.address}</td>
                        <td>
                            <a href="javascript:void(0);" class="update-button"
                               onclick="populateUpdateForm('${client.code}', '${client.firstName}', '${client.lastName}', '${client.birthDate}', '${client.mobile}', '${client.address}')">Update</a>
                            <a href="javascript:void(0);" onclick="populateDelete('${client.code}')"
                               class="delete-button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${not empty client}">
            <div class="table-title">
                <h1>Client</h1>
                <a class="add-button showFormButton">Add Client</a>
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
                        <a href="javascript:void(0);" class="update-button"
                           onclick="populateUpdateForm('${client.code}', '${client.firstName}', '${client.lastName}', '${client.birthDate}', '${client.mobile}', '${client.address}')">Update</a>
                        <a href="javascript:void(0);" onclick="populateDelete('${client.code}')"
                           class="delete-button">Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty clients and empty client}">
            <div class="table-title">
                <h1>Client</h1>
                <a class="add-button showFormButton">Add Client</a>
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
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                    </td>
                </tr>
                </tbody>
            </table>
        </c:if>
    </div>
    <div class="Add-Popup">
        <div id="overlay" class="overlay"></div>
        <div id="popupForm" class="popup">
            <form action="client?action=insert" method="post">
                <h2 style="margin-bottom: 15px; text-align: center;">Add Client</h2>
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
    <div class="Update-Popup">
        <div id="updateOverlay" class="overlay"></div>
        <div id="updatePopupForm" class="popup">
            <form action="client?action=update" method="post">
                <h1 style="margin-bottom: 15px; text-align: center;">Update Client</h1>
                <label for="code">CODE:</label>
                <input type="text" id="updateCode" name="code" readonly><br><br>

                <label for="firstName">First Name:</label>
                <input type="text" id="updateFirstName" name="firstName" required><br><br>

                <label for="lastName">Last Name:</label>
                <input type="text" id="updateLastName" name="lastName" required><br><br>

                <label for="birthDate">Birth Date:</label>
                <input type="date" id="updateBirthDate" name="birthDate" required><br><br>

                <label for="mobile">Mobile:</label>
                <input type="tel" id="updateMobile" name="mobile" required><br><br>

                <label for="address">Address:</label>
                <input type="text" id="updateAddress" name="address" required><br><br>

                <input type="submit" value="Update Client">
                <button type="button" id="closeUpdateFormButton">Close</button>
            </form>
        </div>
    </div>
    <div class="delete-popup">
        <form action="client?action=delete" method="post">
            <div id="confirmationBox" class="delete-modal">
                <input type="hidden" name="code" id="delete-code">
                <div class="delete-modal-content">
                    <h2>Confirmation</h2>
                    <p>Are you sure you want to delete this client?</p>
                    <div class="delete-button-container">
                        <button type="submit" class="delete-modal-button" id="confirmDeleteButton">Confirm</button>
                        <a class="delete-modal-button" id="cancelDeleteButton">Cancel</a>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<script src="<%= request.getContextPath() %>/Resources/js/script.js"></script>
</body>
</html>
