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
            <form id="searchBook" action="searchBook" method="post" >
                <p>Rechercher par: </p>
                <select id="searchType" name="searchType">
                    <option value="title">Titre</option>
                    <option value="author">Auteur</option>
                    <option value="year">Année</option>
                    <option value="category">Catégorie</option>
                </select>
                <input type="search" placeholder="Rechercher..." name="searchValue" required>
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
    <p id="emptyListMessage" class="empty-list-message" style="display: none;"></p>

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

        function toggleBookDetails(iconElement) {
            event.stopPropagation();

            const bookCard = iconElement.closest('.book-card');
            if (!bookCard) {
                console.error("Impossible de trouver le parent '.book-card' pour l'icône cliquée.");
                return;
            }

            const fullDetails = bookCard.querySelector('.card-full-details');
            if (!fullDetails) {
                console.error("Impossible de trouver '.card-full-details' dans la carte de livre.");
                return;
            }

            fullDetails.classList.toggle('active');
        }

        document.addEventListener('click', function(event) {
            const openDetails = document.querySelectorAll('.card-full-details.active');
            openDetails.forEach(detail => {
                // Si l'élément cliqué n'est pas l'icône et n'est pas un détail ouvert, le fermer
                if (!event.target.closest('.icon-container') && !event.target.closest('.card-full-details')) {
                    detail.classList.remove('active');
                }
            });
        });


        const searchForm = document.getElementById('searchBook');
        const bookGrid = document.querySelector('.book-grid-container');
        const searchMessageContainer = document.getElementById('searchMessageContainer');
        const emptyListMessage = document.getElementById('emptyListMessage'); // Pour le message d'absence de livre initial

        if (searchForm) {
            searchForm.addEventListener('submit', function(event) {
                event.preventDefault(); // Empêche le rechargement de la page

                // Masquer le message d'absence de livre initial si visible
                if (emptyListMessage) {
                    emptyListMessage.style.display = 'none';
                }

                const formData = new FormData(searchForm);
                const searchType = formData.get('searchType');
                const searchValue = formData.get('searchValue');

                // Endpoint de votre Servlet/Controller pour la recherche
                const searchUrl = searchForm.action + "?searchType=" + encodeURIComponent(searchType) + "&searchValue=" + encodeURIComponent(searchValue);

                bookGrid.innerHTML = ''; // Efface les livres actuels pendant la recherche

                fetch("searchBook", {
                    method: 'POST', // La recherche est souvent une requête GET
                    headers: {
                        'Accept': 'text/html' // Indique que nous attendons du HTML en retour
                    }
                })
                .then(response => {
                    // Si le serveur renvoie une erreur (par exemple 500)
                    if (!response.ok) {
                        return response.text().then(text => {
                            console.log("erreur (500)");
                            throw new Error(`Erreur HTTP ${response.status}: ${text}`);
                        });
                    }
                    return response.text(); // Attendez du HTML en retour
                })
                .then(htmlContent => {
                    // Mettez à jour le conteneur de la grille des livres avec le nouveau HTML
                    bookGrid.innerHTML = htmlContent;
                    searchMessageContainer.style.display = 'none'; // Masquer le message de chargement

                    if (htmlContent.trim() === '') { // Si la réponse HTML est vide
                         console.log("reponse vide");
                    } else {
                        // Vérifiez si le contenu contient un message "Aucun livre trouvé"
                        const tempDiv = document.createElement('div');
                        tempDiv.innerHTML = htmlContent;
                        if (tempDiv.querySelector('.empty-list-message')) {
                            console.log("liste vide");
                        }
                    }
                })
                .catch(error => {
                    console.error('Erreur lors de la recherche:', error);
                    console.log("erreur lors de la recherhce");
                    // Optionnel: Recharger la liste initiale des livres ou un message d'erreur plus détaillé
                });
            });
        }
    </script>
</body>
</html>
