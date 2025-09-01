<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Réservations</title>
    <link rel="stylesheet" href="css/loans/listMemberLoans.css">
    <link rel="stylesheet" href="css/users/adminNavBar.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/admin/adminNavBar.jsp"/>
    <main class="dashboard-container">
        <h1>Gestion Des Emprunts</h1>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert success">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.error}">
            <div class="alert error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="section-card">
            <h3>Liste emprunts</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID Emprunts</th>
                        <th>Emprunteur</th>
                        <th>Titre du Livre</th>
                        <th>Date d'emprunts</th>
                        <th>Date limit</th>
                        <th>Date retour</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty loans}">
                            <c:forEach var="loan" items="${loans}">
                                <tr>
                                    <td>${loan.loan_id}</td>
                                    <td>${loan.username}</td>
                                    <td>${loan.book_title}</td>
                                    <td>${loan.formattedBorrowDate}</td>
                                    <td>${loan.formattedDueDate}</td>
                                    <td>${loan.formattedReturnDate}</td>
                                    <td>${loan.status}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7" style="text-align: center;">Vous n'avez emprunter aucun livre.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>