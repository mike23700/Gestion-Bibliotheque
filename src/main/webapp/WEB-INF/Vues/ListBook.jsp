<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Livres</title>
    <style>

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 20px;
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

        /* Conteneur de la Grille des Livres */
        .book-grid-container {
            display: grid; /* Utilise CSS Grid pour la disposition en grille */
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr)); /* Colonnes responsives */
            gap: 25px; /* Espacement entre les cartes */
            padding: 20px;
            max-width: 1200px;
            margin: 0 auto; /* Centrer la grille */
        }

        /* Style de Chaque Carte de Livre */
        .book-card {
            background-color: #ffffff;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.1); /* Ombre plus prononcée */
            overflow: hidden; /* Cache le contenu qui déborde avant le survol */
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out; /* Transitions douces */
            display: flex; /* Utilise flexbox pour l'agencement interne des détails */
            flex-direction: column;
            position: relative; /* Pour le positionnement du contenu complet */
        }

        /* Effet au Survol de la Carte */
        .book-card:hover {
            transform: translateY(-5px) scale(1.02); /* Légère élévation et agrandissement */
            box-shadow: 0 8px 25px rgba(0, 0, 0, 0.2);
            z-index: 10; /* Assure que la carte survolée est au-dessus */
        }

        /* Conteneur de l'Image */
        .card-image-wrapper {
            width: 100%;
            padding-top: 150%; /* Ratio 2:3 pour les images verticales (si vos images sont au même ratio) */
            position: relative;
            background-color: #eee; /* Couleur de fond pour les images manquantes */
        }

        /* Image du Livre */
        .book-card-image {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            object-fit: cover; /* Recadre l'image pour couvrir le conteneur */
            border-bottom: 1px solid #eee;
        }

        /* Détails Initiaux (toujours visibles) */
        .card-initial-details {
            padding: 15px;
            text-align: center;
            background-color: #fdfdfd;
        }

        .card-title {
            font-size: 1.2em;
            margin: 0 0 8px 0;
            color: #2c3e50;
        }

        .card-year {
            font-size: 0.9em;
            color: #7f8c8d;
            margin: 0;
        }

        /* Détails Complets (affichés au survol) */
        .card-full-details {
            position: absolute; /* Positionne au-dessus de la carte */
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(44, 62, 80, 0.95); /* Fond semi-transparent bleu foncé */
            color: white;
            padding: 20px;
            box-sizing: border-box; /* Inclut le padding dans la largeur/hauteur */
            opacity: 0; /* Caché par défaut */
            visibility: hidden; /* Caché pour les lecteurs d'écran */
            transform: translateY(100%); /* Commence en dehors de la vue */
            transition: opacity 0.4s ease-in-out, transform 0.4s ease-in-out, visibility 0.4s ease-in-out;
            display: flex; /* Utilise flexbox pour centrer le contenu */
            flex-direction: column;
            justify-content: center;
            align-items: flex-start; /* Alignement du texte à gauche */
            overflow-y: auto; /* Permet le défilement si le contenu est trop long */
        }

        /* Effet au Survol : Affiche les Détails Complets */
        .book-card:hover .card-full-details {
            opacity: 1;
            visibility: visible;
            transform: translateY(0); /* Glisse en place */
        }

        .card-full-details p {
            margin-bottom: 5px;
            font-size: 0.85em;
        }

        .card-full-details strong {
            color: #ecf0f1; /* Gris clair pour les labels */
            font-weight: 600;
        }

        /* Message si la liste est vide */
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

        /* Style pour le bouton "Ajouter un étudiant" */
        .add-student-button {
            display: block; /* Prend toute la largeur */
            width: fit-content; /* S'adapte au contenu */
            padding: 12px 25px;
            margin: 40px auto; /* Centrer le bouton */
            background-color: #2ecc71; /* Vert */
            color: white;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            transition: background-color 0.3s ease, transform 0.2s ease;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .add-student-button:hover {
            background-color: #27ae60; /* Vert plus foncé au survol */
            transform: translateY(-2px); /* Légère élévation au survol */
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
                        <p><strong>Auteur:</strong> <c:out value="${book.author}" /></p>
                        <p><strong>Catégorie:</strong> <c:out value="${book.category}" /></p>
                        <p><strong>Description:</strong> <c:out value="${book.description}" /></p>
                        <p><strong>Statut:</strong> <c:out value="${book.status}" /></p>
                        <p><strong>Nb Emprunts:</strong> <c:out value="${book.loan_count}" /></p>
                        <p><strong>Créé le:</strong> <fmt:formatDate value="${book.created_at}" pattern="yyyy-MM-dd HH:mm"/></p>
                        <%-- Vous pouvez ajouter plus de détails ici si nécessaire --%>
                    </div>
                </div>
            </c:forEach>
        </div>
        <a href="javascript:void(0);" class="add-student-button" onclick="showForm1()">Ajouter un livre</a> <%-- Changé "étudiant" à "livre" --%>

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
