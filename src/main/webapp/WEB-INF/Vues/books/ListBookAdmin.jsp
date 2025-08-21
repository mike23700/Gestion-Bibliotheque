<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Livres</title>
    <link rel="stylesheet" href="css/ListBook.css">
    <link rel="icon" href="assets/logo.png" type="image/png">
</head>
<body>
    <nav class="navbar">
        <div class="navbar-left">
            <a href="#" class="navbar-logo">
                <img src="assets/logo.png" alt="Logo Bibliothèque">
            </a>
        </div>
        <div class="navbar-right">
            <ul class="nav-links">
                <li><a href="#" class="nav-item">Accueil</a></li>
                <li><a href="#" class="nav-item">Emprunts</a></li>
                <li><a href="#" class="nav-item">Utilisateurs</a></li>
                <li><a href="#" class="nav-item">Contact</a></li>
            </ul>
        </div>
    </nav>

    <div class="header-container">
        <h1 class="page-title">Liste des Livres</h1>
    </div>

    <%-- Vérifie si la liste de livres n'est pas vide --%>
    <c:if test="${not empty listbooks}">
        <div class="book-grid-container">
            <c:forEach var="book" items="${listbooks}" varStatus="num">
                <div class="book-card">
                    <div class="card-image-wrapper">
                        <img src="${book.image}" alt="Couverture du livre" class="book-card-image" onerror="this.onerror=null;this.src='https://placehold.co/200x300/e0e0e0/555555?text=Pas+d%27image';"/>
                    </div>
                    <div class="card-initial-details">
                        <h3 class="card-title"><c:out value="${book.title}" /></h3>
                        <p class="card-year">Statut: <c:out value="${book.status}" /></p>
                    </div>
                    <div class="card-full-details">
                        <p><strong>ID:</strong> <c:out value="${book.id_Book}" /></p>
                        <p><strong>Auteur:</strong><c:out value="${book.author}" /></p>
                        <p><strong>Catégorie:</strong><c:out value="${book.category}" /></p>
                        <p><strong>Description:</strong> <c:out value="${book.description}" /></p>
                        <p><strong>Année:</strong><c:out value="${book.year}" /></p>
                        <p><strong>Nb Emprunts:</strong><c:out value="${book.loan_count}" /></p>
                        <p><strong>Créé le:</strong>${book.created_at}</p>
                        <%-- Vous pouvez ajouter plus de détails ici si nécessaire --%>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a href="javascript:void(0);" class="add-student-button" onclick="showForm1()">Ajouter un livre</a>
    </c:if>

    <%-- Si la liste est vide --%>
    <c:if test="${empty listbooks}">
        <p class="empty-list-message">Aucun livre trouvé dans la bibliothèque.</p>
        <a href="javascript:void(0);" class="add-student-button" onclick="showForm1()">Ajouter le premier livre</a>
    </c:if>

    <%-- Script pour définir showForm1() --%>
    <script>
        function showForm1() {
            alert("Le formulaire d'ajout de livre s'afficherait ici !");
            // window.location.href = '${pageContext.request.contextPath}/chemin/vers/votre/addBookForm.jsp';
        }
    </script>
</body>
</html>
