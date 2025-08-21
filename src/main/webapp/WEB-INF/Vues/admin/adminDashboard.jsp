<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Administrateur - BiblioTech</title>
    <link rel="stylesheet" href="css/adminDashboard.css">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
</head>
<body>

    <nav class="navbar">
        <div class="navbar-container">
            <span class="logo">BiblioTech - Admin</span>
            <div class="nav-links">
                <a href="adminDashboard">Accueil</a>
                <a href="manageBooks">Gérer les livres</a>
                <a href="manageUsers">Gérer les utilisateurs</a>
                <a href="manageLoans">Gérer les emprunts</a>
                <a href="manageReservations">Gérer les réservations</a>
            </div>
            <a href="logout" class="logout-btn">Déconnexion</a>
        </div>
    </nav>

    <div class="dashboard-container">
        <h1>Tableau de Bord Administrateur</h1>

        <c:if test="${not empty user}">
            <h2 style="text-align: center; margin-bottom: 30px; color: #555;">
                Bienvenue, <c:out value="${user.name}"/> <c:out value="${user.surname}"/> !
            </h2>
        </c:if>

        <div class="dashboard-stats">
            <div class="stat-card">
                <h3>Livres Totaux</h3>
            </div>
            <div class="stat-card">
                <h3>Livres Empruntés</h3>
            </div>
            <div class="stat-card">
                <h3>Livres Reservés</h3>
                <p>${reservationCount}</p>
            </div>
            <div class="stat-card">
                <h3>Utilisateurs Enregistrés</h3>
                <p>${memberCount}</p>
            </div>
        </div>

    </div>

</body>
</html>