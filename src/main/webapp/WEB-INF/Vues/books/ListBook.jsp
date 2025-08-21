<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Livres</title>
    <link rel="stylesheet" href="css/ListBook.css">
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 10px;
            background-color: #f0f2f5; /* Fond léger et doux */
            color: #333;
            line-height: 1.6;
        }

        .page-title {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 40px;
            font-size: 2.8em;
            font-weight: 600;
        }

        .book-grid-container {
            display: grid; /* Utilise CSS Grid pour la disposition en grille */
            grid-template-columns: repeat(auto-fill, minmax(180px, 1fr)); /* Colonnes responsives */
            gap: 20px; /* Espacement entre les cartes */
            padding: 20px;
            max-width: 1800px;
            margin: 0;
        }

        .book-card {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1);
            overflow: hidden;
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
            display: flex;
            flex-direction: column;
            position: relative;
        }

        .book-card:hover {
            transform: translateY(-5px) scale(1.02);
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
            z-index: 10;
        }

        /* Conteneur de l'Image */
        .card-image-wrapper {
            width: 100%;
            padding-top: 140%; /* Exemple: ratio 1:1.4 (plus haut que carré) */
            position: relative;
            background-color: #eee;
        }

        .book-card-image {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover;
            border-bottom: 1px solid #eee;
        }

        .card-initial-details {
            /* --- MODIFICATION ICI : Réduit le padding pour compenser l'image plus haute --- */
            padding: 5px; /* Ancien: 8px. Diminué pour compacter les détails */
            text-align: center;
            background-color: #fdfdfd;
        }

        .card-title {
            font-size: 1.1em; /* Légèrement réduit si nécessaire pour le compactage */
            margin: 0; /* Ancien: 3px 0. Supprimé les marges pour un affichage plus compact */
            color: #2c3e50;
        }

        .card-year {
            font-size: 0.8em; /* Légèrement réduit si nécessaire pour le compactage */
            color: #7f8c8d;
            margin: 0;
        }

        .card-full-details {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(44, 62, 80, 0.95);
            color: white;
            padding: 20px;
            box-sizing: border-box;
            opacity: 0;
            visibility: hidden;
            transform: translateY(100%);
            transition: opacity 0.4s ease-in-out, transform 0.4s ease-in-out, visibility 0.4s ease-in-out;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: flex-start;
            overflow-y: auto;
        }

        .book-card:hover .card-full-details {
            opacity: 1;
            visibility: visible;
            transform: translateY(0);
        }

        .card-full-details p {
            margin-bottom: 5px;
            font-size: 0.85em;
        }

        .card-full-details strong {
            color: #ecf0f1;
            font-weight: 600;
        }

        .empty-list-message {
            text-align: center;
            color: #888;
            margin-top: 50px;
            font-size: 1.2em;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.08);
            width: 50%;
            margin-left: auto;
            margin-right: auto;
        }

        .add-student-button {
            display: block;
            width: fit-content;
            padding: 12px 25px;
            margin: 40px auto;
            background-color: #2ecc71;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .add-student-button:hover {
            background-color: #27ae60;
            transform: translateY(-2px);
        }
    </style>
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
                        <p><strong>ID:</strong> <c:out value="${book.id_Book}" /></p>
                        <p><strong>Auteur:</strong><c:out value="${book.author}" /></p>
                        <p><strong>Catégorie:</strong><c:out value="${book.category}" /></p>
                        <p><strong>Description:</strong> <c:out value="${book.description}" /></p>
                        <p><strong>Statut:</strong> <c:out value="${book.status}" /></p>
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
