<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Réservations</title>
    <link rel="stylesheet" href="css/reservations/reservationList.css">
    <link rel="stylesheet" href="css/users/memberNavBar.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/member/memberNavBar.jsp"/>
    <main class="dashboard-container">
        <h1>Historique</h1>

        <div class="section-card">
            <h3>Historique de réservations</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID Réservation</th>
                        <th>Titre du Livre</th>
                        <th>Date de Réservation</th>
                        <th>Date d'expiration</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty reservations}">
                            <c:forEach var="res" items="${reservations}">
                                <tr>
                                    <td>${res.reservation_id}</td>
                                    <td>${res.book_title}</td>
                                    <td>${res.formattedDateRegister}</td>
                                    <td>${res.formattedDueRegister}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="4" style="text-align: center;">Vous n'avez jamais reservé de livres.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>