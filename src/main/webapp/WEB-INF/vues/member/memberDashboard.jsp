<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de Bord Membre - BiblioTech</title>
    <link rel="css/memberDashboard.css">
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <style>
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f0f4f8;
            margin: 0;
            padding: 0;
            color: #333;
        }

        .navbar {
            background-color: #007bff;
            color: white;
            padding: 15px 0;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
        }

        .navbar-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 20px;
        }

        .logo {
            font-size: 1.5rem;
            font-weight: bold;
        }

        .nav-links a {
            color: white;
            text-decoration: none;
            margin-left: 25px;
            font-weight: 500;
            transition: color 0.3s ease;
        }

        .nav-links a:hover {
            color: #e9ecef;
        }

        .logout-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 8px 15px;
            border-radius: 5px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

        .logout-btn:hover {
            background-color: #c82333;
        }

        .dashboard-container {
            max-width: 1200px;
            margin: 40px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.05);
        }

        .dashboard-container h1 {
            color: #333;
            text-align: center;
            margin-bottom: 40px;
        }

        .dashboard-sections {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .section-card {
            background-color: #f9f9f9;
            padding: 25px;
            border-radius: 8px;
            box-shadow: 0 1px 5px rgba(0, 0, 0, 0.05);
        }

        .section-card h3 {
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-bottom: 20px;
            color: #007bff;
        }

        .empty-state {
            color: #777;
            text-align: center;
        }
    </style>
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

        <c:if test="${not empty sessionScope.user}">
            <h2 style="text-align: center; margin-bottom: 30px; color: #555;">
                Bienvenue, <c:out value="${sessionScope.user.name}"/> <c:out value="${sessionScope.user.surname}"/> !
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