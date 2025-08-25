<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestion des utilisateurs</title>
    <link rel="stylesheet" href="css/manageUsers.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/admin/adminNavBar.jsp"/>
    <main class="dashboard-container">
        <h1>Gestion des utilisateurs</h1>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert success">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.error}">
            <div class="alert error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

<div class="top-controls">
    <form action="searchUser" method="post" class="search-user-form">
        <select name="type">
            <option value="user_id">ID Utilisateur</option>
            <option value="name">Nom</option>
        </select>
        <input type="text" name="input" placeholder="Rechercher..." required>
        <button type="submit"><i class="fas fa-search"></i></button>
    </form>

    <a href="addUser" class="add-user-btn"><i class="fas fa-user-plus"></i> Ajouter</a>
</div>

        <div class="section-card">
            <h3>Liste des utilisateurs</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>Téléphone</th>
                        <th>Email</th>
                        <th>Date d'inscription</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="u" items="${userList}">
                        <tr>
                            <td>${u.user_id}</td>
                            <td>${u.name}</td>
                            <td>${u.surname}</td>
                            <td>${u.tel_num}</td>
                            <td>${u.email}</td>
                            <td>${u.registration_date}</td>
                            <td>
                                <form action="deleteUser" method="post" onsubmit="return confirm('Voulez-vous vraiment supprimer cet utilisateur ?');">
                                    <input type="hidden" name="user_id" value="${u.user_id}">
                                    <button type="submit" class="delete-btn"><i class="fas fa-trash-alt"></i></button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty userList}">
                        <tr>
                            <td colspan="6" style="text-align:center;">Aucun utilisateur trouvé</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </main>
</body>
</html>