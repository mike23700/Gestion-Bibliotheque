<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des Réservations</title>
    <link rel="stylesheet" href="css/reservationList.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-container">
            <div class="navbar-left">
                <img src="assets/logo2.png" alt="Logo" class="logo-img">
                <span class="user-name">${user.name} ${user.surname}</span>
            </div>
            <div class="nav-links">
                <a href="adminDashboard">Accueil</a>
                <a href="listBooks">Livres</a>
                <a href="manageUsers">Utilisateurs</a>
                <a href="manageLoans">Emprunts</a>
                <a href="adminListReservations" class="active">Réservations</a>
            </div>
            <a href="logout" class="logout-btn" onclick="return confirm('${user.name}, voulez-vous vraiment vous déconnecter ?');"><i class="fas fa-sign-out-alt"></i></a>
        </div>
    </nav>

    <main class="dashboard-container">
        <h1>Gestion des Réservations</h1>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert success">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.error}">
            <div class="alert error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="top-controls">
            <form action="adminListReservations" method="get" class="filter-form">
                <label for="status">Filtrer par statut :</label>
                <select name="status" id="status" onchange="this.form.submit()">
                    <option value="">Tous</option>
                    <option value="ACTIVE" ${param.status eq 'ACTIVE' ? 'selected' : ''}>Active</option>
                    <option value="FULFILLED" ${param.status eq 'FULFILLED' ? 'selected' : ''}>Terminée</option>
                    <option value="CANCELLED" ${param.status eq 'CANCELLED' ? 'selected' : ''}>Annulée</option>
                </select>
            </form>

            <form action="adminSearchReservations" method="post" class="search-user-form">
                <select name="searchType">
                    <option value="userId">ID Utilisateur</option>
                    <option value="userName">Nom d'utilisateur</option>
                    <option value="bookId">ID du livre</option>
                    <option value="bookName">Titre du livre</option>
                </select>
                <input type="text" name="searchValue" placeholder="Rechercher..." required>
                <button type="submit"><i class="fas fa-search"></i></button>
            </form>
        </div>

        <div class="section-card">
            <h3>Liste des Réservations</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID Réservation</th>
                        <th>Utilisateur</th>
                        <th>Titre du Livre</th>
                        <th>Date de Réservation</th>
                        <th>Statut</th>
                        <th>Actions</th>
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
                                    <td>${res.registration_date}</td>
                                    <td>${res.status}</td>
                                    <td>
                                        <c:if test="${res.status eq 'ACTIVE'}">
                                            <form action="updateReservation" method="post">
                                                <input type="hidden" name="reservationId" value="${res.reservation_id}">
                                                <button type="submit" class="action-btn" onclick="return confirm('Voulez-vous vraiment marquer cette réservation comme terminée ?');">
                                                    <i class="fas fa-check"></i> Terminer
                                                </button>
                                            </form>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align: center;">Aucune réservation trouvée.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>