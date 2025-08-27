<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un utilisateur - BiblioTech</title>
    <link rel="stylesheet" href="css/users/manageUsers.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/admin/adminNavBar.jsp"/>
    <main class="dashboard-container">
        <h1>Ajouter un utilisateur</h1>

        <c:if test="${not empty sessionScope.error}">
            <div class="alert error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="section-card">
            <h3>Nouveau membre</h3>
            <form action="addUser" method="post" class="add-user-form">
                <div class="form-group">
                    <label for="name">Nom</label>
                    <input type="text" id="name" name="name" placeholder="Nom de l'utilisateur" required>
                </div>
                <div class="form-group">
                    <label for="surname">Prénom</label>
                    <input type="text" id="surname" name="surname" placeholder="Prénom de l'utilisateur" required>
                </div>
                <div class="form-group">
                    <label for="tel_num">Numéro de téléphone</label>
                    <input type="tel" id="tel_num" name="tel_num" placeholder="Numéro de téléphone" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input type="email" id="email" name="email" placeholder="Adresse email" required>
                </div>
                <div class="form-actions">
                    <button type="submit" class="add-btn"><i class="fas fa-user-plus"></i> Ajouter</button>
                    <a href="manageUsers" class="cancel-btn">Annuler</a>
                </div>
            </form>
        </div>
    </main>
</body>
</html>