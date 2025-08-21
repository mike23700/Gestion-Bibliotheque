<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Membre - BiblioTech</title>
    <link rel="stylesheet" href="css/memberDashboard.css">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
</head>
<body>

    <nav class="navbar">
        <div class="navbar-container">
            <span class="logo">BiblioTech - Membre</span>
            <div class="nav-links">
                <a href="memberDashboard">Accueil</a>
                <a href="borrowedBooks">Mes emprunts</a>
                <a href="myReservations">Mes réservations</a>
                <a href="listBooks">Rechercher des livres</a>
            </div>
            <a href="logout" class="logout-btn">Déconnexion</a>
        </div>
    </nav>

    <div class="dashboard-container">
        <h1>Tableau de Bord Membre</h1>

        <c:if test="${not empty user}">
            <h2 style="text-align: center; margin-bottom: 30px; color: #555;">
                Bienvenue, <c:out value="${user.name}"/> <c:out value="${user.surname}"/> !
            </h2>
        </c:if>

        <div class="dashboard-sections">
            <div class="section-card">
                <h3>Mes emprunts en cours</h3>
                <p class="empty-state">Vous n'avez aucun livre emprunté pour le moment.</p>
                <!-- Ici, vous afficherez la liste des livres empruntés dynamiquement -->
            </div>

            <div class="section-card">
                <h3>Mes réservations</h3>
                <p class="empty-state">Vous n'avez aucune réservation en cours.</p>
                <!-- Ici, vous afficherez la liste des réservations dynamiquement -->
            </div>
        </div>
    </div>
</body>
</html>