<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de bord Admin</title>
    <link rel="stylesheet" href="css/users/dashboard.css">
    <link rel="stylesheet" href="css/users/adminNavBar.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/admin/adminNavBar.jsp"/>
    <main>
        <h1>Bienvenue sur le tableau de bord</h1>
        <div class="dashboard-stats"> <div class="stat-card">
        <a href="listBooks">
          <h3>Livres Totaux</h3>
        </a>
        </div>
        <div class="stat-card">
        <a href="manageLoan">
          <h3>Livres Empruntés</h3>
        </a>
        </div> <div class="stat-card">
        <a href="adminListReservations">
          <h3>Livres Reservés</h3>
        </a>
          <p>${reservationCount}</p>
        </div>
        <div class="stat-card">
        <a href="manageUsers">
          <h3>Membres Enregistrés</h3>
        </a>
          <p>${memberCount}</p>
        </div>
        </div>
    </main>
</body>
</html>
