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
                   <div class="dashboard-label"><h3>Espace Membre</h3></div>
               </div>
        </div>

        <div class="nav-links">
            <a href="memberDashboard">Accueil</a>
            <a href="borrowedBooks">Mes emprunts</a>
            <a href="memberListReservations">Mes réservations</a>
            <a href="listBooks">Rechercher des livres</a>
        </div>

        <div class="nav-actions">
            <a href="userInfo" class="profile-btn"><i class="fas fa-user"></i></a>
            <a href="logout" class="logout-btn" onclick="return confirm('${user.name}, voulez-vous vraiment vous déconnecter ?');"><i class="fas fa-sign-out-alt"></i></a>
        </div>
    </div>
</nav>