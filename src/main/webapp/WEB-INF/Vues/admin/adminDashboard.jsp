<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de bord Admin</title>
    <link rel="stylesheet" href="css/dashboard.css">
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
                <a href="manageBooks">Livres</a>
                <a href="manageUsers">Utilisateurs</a>
                <a href="manageLoans">Emprunts</a>
                <a href="manageReservations">Réservations</a>
            </div>

            <a href="logout" class="logout-btn" onclick="return confirm('${user.name}, voulez-vous vraiment vous déconnecter ?');"><i class="fas fa-sign-out-alt"></i></a>
        </div>
    </nav>

    <main>
        <h1>Bienvenue sur le tableau de bord</h1>
        <div class="dashboard-stats"> <div class="stat-card">
          <h3>Livres Totaux</h3>
        </div>
        <div class="stat-card">
          <h3>Livres Empruntés</h3>
        </div> <div class="stat-card">
          <h3>Livres Reservés</h3>
          <p>${reservationCount}</p>
        </div>
        <div class="stat-card">
          <h3>Utilisateurs Enregistrés</h3>
          <p>${memberCount}</p>
        </div>
        </div>
    </main>
</body>
</html>
