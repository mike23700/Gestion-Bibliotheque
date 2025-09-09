<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar">
    <link rel="stylesheet" href="css/users/userInfoModal.css">
    <div class="navbar-container">
        <div class="navbar-left">
               <a href="listBooks">
                   <img src="assets/logo2.png" alt="Logo" class="logo-img">
               </a>
               <div class="user-info">
                   <span class="user-name">${user.name} ${user.surname}</span>
                   <div class="dashboard-label"><h3>Espace Membre</h3></div>
               </div>
        </div>

        <div class="nav-links">
            <a href="listBooks">Accueil</a>
            <a href="memberListLoans">Mes emprunts</a>
            <a href="memberListReservations">Mes réservations</a>
        </div>

        <div class="nav-actions">
            <a href="#" class="profile-btn" onclick="openModal('userInfo'); return false;"><i class="fas fa-user-circle"></i></a>
            <a href="logout" class="logout-btn" onclick="return confirm('${user.name} ${user.surname}, voulez-vous vraiment vous déconnecter ?');"><i class="fas fa-sign-out-alt"></i></a>

            <div id="modal" class="modal">
                <div class="modal-content">
                    <div id="modal-body"></div>
                </div>
            </div>
        </div>
    </div>
</nav>
<script>
    function openModal(url) {
        const modal = document.getElementById("modal");
        fetch(url)
            .then(response => response.text())
            .then(html => {
                document.getElementById("modal-body").innerHTML = html;
                modal.style.display = "block";
            })
            .catch(err => console.error("Erreur modal :", err));
    }

    window.addEventListener('click', function(event) {
        const modal = document.getElementById("modal");
        const profileBtn = document.querySelector('.profile-btn');
        if (modal.style.display === "block" && !profileBtn.contains(event.target) && !modal.contains(event.target)) {
            modal.style.display = "none";
            document.getElementById("modal-body").innerHTML = "";
        }
    });
</script>