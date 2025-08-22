<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Résultats de la Recherche - BiblioTech</title>
    <link rel="stylesheet" href="css/manageUsers.css">
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
                <a href="manageUsers" class="active">Utilisateurs</a>
                <a href="manageLoans">Emprunts</a>
                <a href="manageReservations">Réservations</a>
            </div>
            <a href="logout" class="logout-btn" onclick="return confirm('${user.name}, voulez-vous vraiment vous déconnecter ?');"><i class="fas fa-sign-out-alt"></i></a>
        </div>
    </nav>

    <main class="dashboard-container">
        <h1>Résultats de la recherche</h1>

        <div class="top-actions">
            <a href="manageUsers" class="back-btn"><i class="fas fa-arrow-left"></i> Retour à la gestion</a>
        </div>

        <div class="section-card">
            <c:if test="${empty requestScope.result}">
                <div class="alert info">Aucun utilisateur ne correspond à votre recherche.</div>
            </c:if>

            <c:if test="${not empty requestScope.result}">
                <table class="user-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Date d'inscription</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="u" items="${requestScope.result}">
                            <tr>
                                <td>${u.user_id}</td>
                                <td>${u.name}</td>
                                <td>${u.surname}</td>
                                <td>    ${u.registration_date}</td>
                                <td>
                                    <form action="deleteUser" method="post" onsubmit="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">
                                        <input type="hidden" name="user_id" value="${u.user_id}">
                                        <button type="submit" class="delete-btn"><i class="fas fa-trash-alt"></i></button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </main>
</body>
</html>