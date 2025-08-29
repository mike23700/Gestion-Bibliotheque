<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <title>Liste des Livres</title>
    <link rel="stylesheet" href="css/books/ListBookMember.css">
    <link rel="stylesheet" href="css/users/memberNavBar.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/member/memberNavBar.jsp"/>
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
                <input type="search" placeholder="Rechercher..." name="searchValue" >
                <input type="submit" value="Rechercher">
            </form>
        </div>
        <div>
            <form>
                <p>Filtrer par :</p>
                <select id="searchType" name="searchType">
                    <option value="title">Titre</option>
                    <option value="author">Auteur</option>
                    <option value="year">Année</option>
                    <option value="category">Catégorie</option>
                    <option value="disponible">Disponible</option>
                    <option value="emprunter">Emprunter</option>
                </select>
            </form>
        </div>
        <div class="header-container">
            <h1 class="page-title">Liste des Livres</h1>
        </div>
    </nav>
    <div style="height: 50px;"></div>

    <!-- Grille des livres -->
    <c:if test="${not empty listbooks}">
        <div class="book-grid-container" id="bookGrid" >
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
                        <p class="card-year">
                            <c:choose>
                                <c:when test="${book.status == 'disponible'}">
                                    <form action="status" method="post">
                                        <input type="hidden" name="id_book" id="" value="${book.id_Book}">
                                        <input type="hidden" name="action" id="" value="emprunté">
                                        <input type="submit" value="Emprunter">
                                    </form>
                                </c:when>
                                <c:when test="${book.status == 'emprunté'}">
                                    <form action="status" method="post">
                                        <input type="hidden" name="id_book" id="" value="${book.id_Book}">
                                        <input type="hidden" name="action" id="" value="reserver">
                                        <input type="submit" value="Reserver" >
                                    </form>
                                </c:when>
                                <c:when test="${book.status == 'reserver'}">
                                    <button disabled >Reserver</button>
                                </c:when>
                            </c:choose>
                        </p>
                    </div>
                    <div class="card-full-details">
                        <p><strong>Auteur:</strong> ${book.author}</p>
                        <p><strong>Catégorie:</strong> ${book.category}</p>
                        <p><strong>Description:</strong> ${book.description}</p>
                        <p><strong>Année:</strong> ${book.year}</p>
                    </div>
                </div>
            </c:forEach>
        </div>

    </c:if>
    <p id="emptyListMessage" class="empty-list-message" style="display: none;"></p>

    <!-- Si la liste est vide -->
    <c:if test="${empty listbooks}">
        <p class="empty-list-message">Aucun livre trouvé dans la bibliothèque.</p>
        <a href="javascript:void(0);" class="add-student-button" onclick="showForm1()">Ajouter le premier livre</a>
    </c:if>


    <script>

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

                if (!event.target.closest('.icon-container') && !event.target.closest('.card-full-details')) {
                    detail.classList.remove('active');
                }
            });
        });


        const searchForm = document.getElementById('searchBook');
        const bookGrid = document.getElementById('bookGrid');
        const searchMessageContainer = document.getElementById('searchMessageContainer');
        const emptyListMessage = document.getElementById('emptyListMessage');

        if (emptyListMessage && bookGrid) {
            const initialBookCardsCount = bookGrid.querySelectorAll('.book-card').length;
            if (initialBookCardsCount === 0) {
                emptyListMessage.textContent = 'Aucun livre trouvé dans la bibliothèque.';
                emptyListMessage.style.display = 'block';
            } else {
                emptyListMessage.style.display = 'none';
            }
        }


        if (searchForm) {
            searchForm.addEventListener('submit', function(event) {
                event.preventDefault();


                if (emptyListMessage) emptyListMessage.style.display = 'none';
                if (searchMessageContainer) searchMessageContainer.style.display = 'none';


                if (bookGrid) {
                   bookGrid.innerHTML = '';
                }

                const formData = new FormData(searchForm);



                fetch("searchBook", {
                    method: 'POST', // La recherche est souvent une requête GET
                    body: formData,
                })
                .then(response => {
                    // Si le serveur renvoie une erreur (par exemple 500)
                    if (!response.ok) {
                        return response.text().then(text => {
                            console.error("Erreur (HTTP Status " + response.status + "):", text);
                            throw new Error(`Erreur HTTP ${response.status}: ${text}`);
                        });
                    }
                    return response.text(); // Attendez du HTML en retour
                })
                .then(htmlContent => {
                    if (bookGrid) {
                        bookGrid.innerHTML = htmlContent; // Mette à jour le conteneur de la grille
                    }

                    // Vérifie si des livres ont été trouvés APRÈS la mise à jour de la grille
                    const hasBooks = bookGrid && bookGrid.querySelector('.book-card') !== null;

                    if (searchMessageContainer) {
                        if (!hasBooks) { // Si aucun livre n'est présent dans la grille
                            searchMessageContainer.style.display = 'block';
                            searchMessageContainer.style.color = 'orange';
                            searchMessageContainer.textContent = 'Aucun livre trouvé correspondant à votre recherche.';
                        } else {
                            // Si des livres ont été trouvés, masquer le message
                            searchMessageContainer.style.display = 'none';
                        }
                    }
                })
                .catch(error => {
                   console.error('Erreur lors de la recherche AJAX:', error);
                    if (searchMessageContainer) {
                        searchMessageContainer.style.display = 'block';
                        searchMessageContainer.style.color = 'red';
                        searchMessageContainer.textContent = `Erreur lors de la recherche: ${error.message}. Veuillez réessayer.`;
                    }
                });
            });
        }

    </script>
</body>
</html>
