<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Résultats de Recherche</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reservationList.css">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-container">
            <div class="navbar-left">
                <img src="${pageContext.request.contextPath}/assets/logo2.png" alt="Logo" class="logo-img">
                <span class="user-name">${user.name} ${user.surname}</span>
            </div>
            <div class="nav-links">
                <a href="${pageContext.request.contextPath}/adminDashboard">Accueil</a>
                <a href="${pageContext.request.contextPath}/listBooks">Livres</a>
                <a href="${pageContext.request.contextPath}/manageUsers">Utilisateurs</a>
                <a href="${pageContext.request.contextPath}/manageLoans">Emprunts</a>
                <a href="${pageContext.request.contextPath}/adminListReservations" class="active">Réservations</a>
            </div>
            <a href="${pageContext.request.contextPath}/logout" class="logout-btn" onclick="return confirm('${user.name}, voulez-vous vraiment vous déconnecter ?');"><i class="fas fa-sign-out-alt"></i></a>
        </div>
    </nav>

    <main class="dashboard-container">
        <h1>Résultats de Recherche de Réservations</h1>

        <a href="${pageContext.request.contextPath}/adminListReservations" class="back-link">&larr; Retour à la gestion des réservations</a>

        <div class="section-card">
            <h3>Résultats de la Recherche</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID Réservation</th>
                        <th>Nom du Membre</th>
                        <th>Titre du Livre</th>
                        <th>Date de Réservation</th>
                        <th>Statut</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty reservations}">
                            <c:forEach var="res" items="${reservations}">
                                <tr>
                                    <td>${res.reservation_id}</td>
                                    <td>${res.user_name}</td>
                                    <td>${res.book_title}</td>
                                    <td>
                                        <fmt:formatDate value="${res.reservation_date}" pattern="dd/MM/yyyy HH:mm"/>
                                    </td>
                                    <td>${res.status}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="text-align: center;">Aucune réservation trouvée pour votre recherche.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>