<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar">
    <div class="navbar-container">
        <div class="navbar-left">
            <a href="adminDashboard">
                <img src="assets/logo2.png" alt="Logo" class="logo-img">
            </a>
            <div class="user-info">
                <span class="user-name">${user.name} ${user.surname}</span>
                <div class="dashboard-label"><h3>Administrateur</h3></div>
            </div>
        </div>


        <div class="nav-links">
            <a href="adminDashboard" class="${activePage == 'accueil' ? 'active' : ''}" >Accueil</a>
            <a href="listBooks"  class="${activePage == 'livres' ? 'active' : ''}" >Livres</a>
            <a href="manageUsers" class="${activePage == 'utilisateurs' ? 'active' : ''}" >Utilisateurs</a>
            <a href="listLoan" class="${activePage == 'emprunts' ? 'active' : ''}" >Emprunts</a>
            <a href="adminListReservations" class="${activePage == 'reservations' ? 'active' : ''}" >Réservations</a>
        </div>

        <a class="logout-btn" onclick="showPopupDeconnexion()"><i class="fas fa-sign-out-alt"></i></a>
    </div>
</nav>