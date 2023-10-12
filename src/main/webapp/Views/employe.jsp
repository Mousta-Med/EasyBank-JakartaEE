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
            <li><a href="<%= request.getContextPath() %>/client?action=list">Client</a></li>
            <li><a class="current" href="<%= request.getContextPath() %>/employe?action=list">Employe</a></li>
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
        <form action="employe?action=get" method="POST">
            <div class="search-input-container">
                <input type="text" class="search-input" name="matricule" placeholder="Enter Employe Matricule">
                <button type="submit" class="search-button">Search</button>
            </div>
        </form>
    </div>
    <c:if test="${not empty employes}">
        <div style="width: 70%">
            <div class="table-title">
                <h1>Employes List</h1>
                <a class="showFormButton add-button">Add Employe</a>
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
                            <a href="javascript:void(0);" class="update-button"
                               onclick="populateUpdate('${employe.matricule}', '${employe.firstName}', '${employe.lastName}', '${employe.birthDate}', '${employe.mobile}', '${employe.email}', '${employe.recruitmentDate}')">Update</a>
                            <a href="javascript:void(0);" onclick="populate('${employe.matricule}')"
                               class="delete-button">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${not empty employe}">
        <div style="width: 70%">
            <div class="table-title">
                <h1>Employes</h1>
                <a class="showFormButton add-button">Add Employe</a>
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
                        <a href="javascript:void(0);" class="update-button"
                           onclick="populateUpdate('${employe.matricule}', '${employe.firstName}', '${employe.lastName}', '${employe.birthDate}', '${employe.mobile}', '${employe.email}', '${employe.recruitmentDate}')">Update</a>
                        <a href="javascript:void(0);" onclick="populate('${employe.matricule}')"
                           class="delete-button">Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </c:if>
    <c:if test="${empty employes and empty employe}">
        <div style="width: 70%">
            <div class="table-title">
                <h1>Employes</h1>
                <a class="showFormButton add-button">Add Employe</a>
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
                    <td></td>
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
        </div>
    </c:if>
    <div class="Add-Popup">
        <div id="overlay" class="overlay"></div>
        <div id="popupForm" class="popup">
            <form action="employe?action=insert" method="post">
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
    <div class="Update-Popup">
        <div id="updateOverlay" class="overlay"></div>
        <div id="updatePopupForm" class="popup">
            <form action="employe?action=update" method="post">
                <h1 style="margin-bottom: 15px; text-align: center;">Update Employe</h1>
                <label for="matricule">Matricule:</label>
                <input type="text" id="updateMatricule" name="matricule" readonly><br><br>

                <label for="firstName">First Name:</label>
                <input type="text" id="updateFirstName" name="firstName" required><br><br>

                <label for="lastName">Last Name:</label>
                <input type="text" id="updateLastName" name="lastName" required><br><br>

                <label for="birthDate">Birth Date:</label>
                <input type="date" id="updateBirthDate" name="birthDate" required><br><br>

                <label for="mobile">Mobile:</label>
                <input type="tel" id="updateMobile" name="mobile" required><br><br>

                <label for="email">Email:</label>
                <input type="email" id="updateEmail" name="email" required><br><br>

                <label for="recruitmentDate">Recruitment Date:</label>
                <input type="date" id="updateRecruitmentDate" name="recruitmentDate"
                       required><br><br>
                <input type="submit" value="Update Employee">
                <button type="button" id="closeUpdateFormButton">Close</button>
            </form>
        </div>
    </div>
    <div class="delete-popup">
        <form action="employe?action=delete" method="post">
            <div id="confirmationBox" class="delete-modal">
                <input type="hidden" name="matricule" id="delete-matricule">
                <div class="delete-modal-content">
                    <h2>Confirmation</h2>
                    <p>Are you sure you want to delete this employe?</p>
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
