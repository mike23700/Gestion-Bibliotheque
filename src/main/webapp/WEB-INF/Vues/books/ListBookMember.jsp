<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Livres</title>
    <link rel="stylesheet" href="css/ListBook.css">
</head>
<body>
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
                        <p class="card-year">Publié en <c:out value="${book.year}" /></p>
                    </div>
                    <div class="card-full-details">
                        <p><strong>Auteur:</strong><c:out value="${book.author}" /></p>
                        <p><strong>Catégorie:</strong><c:out value="${book.category}" /></p>
                        <p><strong>Description:</strong> <c:out value="${book.description}" /></p>
                        <p><strong>Statut:</strong> <c:out value="${book.status}" /></p>
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
