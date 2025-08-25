<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tableau de bord Membre</title>
    <link rel="stylesheet" href="css/dashboard.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/member/memberNavBar.jsp"/>
    <div class="dashboard-container">
        <h1>Tableau de Bord Membre</h1>

        <c:if test="${not empty user}">
            <h2 style="text-align: center; margin-bottom: 30px; color: #555;">
                Bienvenue, <c:out value="${user.name}"/> <c:out value="${user.surname}"/> !
            </h2>
        </c:if>

        <div class="dashboard-sections">
            <div class="section-card">
                <h3>Mes emprunts en cours</h3>
                <p class="empty-state">Vous n'avez aucun livre emprunté pour le moment.</p>
            </div>

            <div class="section-card">
                <h3>Mes réservations</h3>
                <p class="empty-state">Vous n'avez aucune réservation en cours.</p>
            </div>
        </div>
    </div>
</body>
</html>
