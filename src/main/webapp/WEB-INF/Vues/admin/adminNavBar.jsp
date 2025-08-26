<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar">
    <div class="navbar-container">
<div class="navbar-left">
    <a href="index.jsp">
        <img src="assets/logo2.png" alt="Logo" class="logo-img">
    </a>

    <div class="user-info">
        <span class="user-name">${user.name} ${user.surname}</span>
        <div class="dashboard-label"><h3>Administrateur</h3></div>
    </div>
</div>


            <div class="nav-links">
                <a href="adminDashboard">Accueil</a>
                <a href="listBooks">Livres</a>
                <a href="manageUsers" class="active">Utilisateurs</a>
                <a href="manageLoans">Emprunts</a>
                <a href="adminListReservations">Réservations</a>
            </div>

        <a href="logout"
           class="logout-btn"
           onclick="return confirm('${user.name} ${usersurname} voulez-vous vraiment vous déconnecter ?');">
            <i class="fas fa-sign-out-alt"></i>
        </a>
    </div>
</nav>
