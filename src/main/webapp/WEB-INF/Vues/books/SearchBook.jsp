<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Livres</title>
    <link rel="stylesheet" href="css/books/ListBook.css">
    <link rel="stylesheet" href="css/books/AddBook.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <!-- Barre de navigation principale -->
    <nav class="navbar">
        <div class="navbar-left">
            <a href="#" class="navbar-logo">
                <img src="assets/logo2.png" alt="Logo Bibliothèque">
            </a>
        </div>
        <div class="contenair-icon-deconnexion">
            <a href="#" class="icon-deconnexion"> <i class="fa-solid fa-arrow-right-from-bracket"></i> </a>
        </div>
    </nav>
    <div style="height: 50px;"></div>

    <!-- Barre de navigation du menu/recherche -->
    <nav class="navbar-menu">
        <div>
            <form action="searchBook" method="post" id="">
                <p>Rechercher par: </p>
                <select id="searchType" name="searchType">
                    <option value="title">Titre</option>
                    <option value="author">Auteur</option>
                    <option value="year">Année</option>
                    <option value="category">Catégorie</option>
                </select>
                <input type="search" placeholder="Rechercher...">
                <input type="submit" value="Rechercher">
            </form>
        </div>
        <div>
            <a href="#" class="nav-item">Filtre</a>
        </div>
        <div class="header-container">
            <h1 class="page-title">Liste des Livres</h1>
        </div>
    </nav>
    <div style="height: 50px;"></div>

    <!-- Grille des livres -->
    <c:if test="${not empty listbooks}">
        <div class="book-grid-container">
            <c:forEach var="book" items="${listbooks}" varStatus="num">
                <div class="book-card">
                    <div class="image-And-icon-container">
                        <!-- L'icône pour afficher les détails au clic -->
                        <div class="icon-container" onclick="toggleBookDetails(this)">
                            <i class="fa-solid fa-ellipsis-vertical"></i>
                        </div>
                        <div class="card-image-wrapper">
                            <img src="${pageContext.request.contextPath}/${book.image}" alt="Couverture du livre" class="book-card-image" onerror="this.onerror=null;this.src='https://placehold.co/200x300/e0e0e0/555555?text=Pas+d%27image';"/>
                        </div>
                    </div>
                    <div class="card-initial-details">
                        <strong><p class="card-title">${book.title}</p></strong>
                        <p class="card-year">${book.status}</p>
                    </div>
                    <!-- Détails complets du livre, masqués par défaut, affichés au clic de l'icône -->
                    <div class="card-full-details">
                        <p></p>
                        <p><strong>ID:</strong> ${book.id_Book}</p>
                        <p><strong>Auteur:</strong> ${book.author}</p>
                        <p><strong>Catégorie:</strong> ${book.category}</p>
                        <p><strong>Description:</strong> ${book.description}</p>
                        <p><strong>Année:</strong> ${book.year}</p>
                        <p><strong>Nb Emprunts:</strong> ${book.loan_count}</p>
                        <p><strong>Créé le:</strong> ${book.created_at}</p>
                        <%-- Vous pouvez ajouter plus de détails ici si nécessaire --%>
                    </div>
                </div>
            </c:forEach>
        </div>

        <a href="javascript:void(0);" class="add-student-button" onclick="ShowFormAddBook()">Ajouter un livre</a>

    </c:if>

    <!-- Si la liste est vide -->
    <c:if test="${empty listbooks}">
        <p class="empty-list-message">Aucun livre trouvé dans la bibliothèque.</p>
        <a href="javascript:void(0);" class="add-student-button" onclick="showForm1()">Ajouter le premier livre</a>
    </c:if>

    <div class="overlay"></div>
    <jsp:include page="AddBooks.jsp" />

    <!-- Script JavaScript pour gérer l'affichage des détails -->
    <script>
        function ShowFormAddBook() {
            document.querySelector(".form-container").style.display = "block";
            document.querySelector(".overlay").style.display = "block";
        }

        function HideFormAddBook() {
            document.querySelector(".form-container").style.display = "none";
            document.querySelector(".overlay").style.display = "none";
        }

        /**
         * Bascule l'affichage des détails complets d'une carte de livre.
         * L'icône est passée comme élément déclencheur.
         * @param {HTMLElement} iconElement L'élément <div> de l'icône cliquée.
         */
        function toggleBookDetails(iconElement) {
            // Empêche la propagation du clic pour éviter d'autres événements potentiels
            event.stopPropagation();

            // Trouver la carte de livre parente
            const bookCard = iconElement.closest('.book-card');
            if (!bookCard) {
                console.error("Impossible de trouver le parent '.book-card' pour l'icône cliquée.");
                return;
            }

            // Trouver les détails complets à l'intérieur de cette carte
            const fullDetails = bookCard.querySelector('.card-full-details');
            if (!fullDetails) {
                console.error("Impossible de trouver '.card-full-details' dans la carte de livre.");
                return;
            }

            // Basculer la classe 'active' pour afficher/masquer les détails
            fullDetails.classList.toggle('active');

            // Optionnel : fermer les autres détails ou ajouter une classe pour indiquer que c'est ouvert
            // Vous pouvez ajouter ici une logique pour fermer tous les autres détails ouverts
            // document.querySelectorAll('.card-full-details.active').forEach(detail => {
            //     if (detail !== fullDetails) {
            //         detail.classList.remove('active');
            //     }
            // });
        }

        // Pour fermer les détails si l'utilisateur clique n'importe où ailleurs sur la page
        document.addEventListener('click', function(event) {
            const openDetails = document.querySelectorAll('.card-full-details.active');
            openDetails.forEach(detail => {
                // Si l'élément cliqué n'est pas l'icône et n'est pas un détail ouvert, le fermer
                if (!event.target.closest('.icon-container') && !event.target.closest('.card-full-details')) {
                    detail.classList.remove('active');
                }
            });
        });
    </script>
</body>
</html>
