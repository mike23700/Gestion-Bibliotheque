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
        .dashboard-stats {
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            margin-bottom: 40px;
        }
        .stat-card {
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 1px 5px rgba(0, 0, 0, 0.05);
            text-align: center;
            min-width: 200px;
            margin: 10px;
        }
        .stat-card h3 {
            color: #007bff;
            margin-bottom: 5px;
        }
        .stat-card p {
            font-size: 1.5rem;
            font-weight: bold;
        }
    </style>
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

        <c:if test="${not empty sessionScope.user}">
            <h2 style="text-align: center; margin-bottom: 30px; color: #555;">
                Bienvenue, <c:out value="${sessionScope.user.name}"/> <c:out value="${sessionScope.user.surname}"/> !
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
                <h3>Utilisateurs Enregistrés</h3>
            </div>
        </div>

    </div>

</body>
</html>