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
            <form action="searchBook" method="post">
                <p>Filtrer par :</p>
                <select id="searchType" name="searchType">
                    <option value="disponible" ${param.status eq 'disponible' ? 'selected' : ''}>Disponible</option>
                    <option value="emprunter" ${param.status eq 'emprunter' ? 'selected' : ''}>Emprunter</option>
                </select>
            </form>
        </div>
        <div class="header-container">
            <h1 class="page-title">Liste des Livres</h1>
        </div>
    </nav>
    <div style="height: 50px;"></div>

    <c:if test="${not empty listbooks}">
        <div class="book-grid-container" id="bookGrid" >
            <c:forEach var="book" items="${listbooks}" varStatus="num">
                <div class="book-card">
                    <div class="image-And-icon-container">
                      
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
                                <%-- Si le livre est emprunté PAR l'utilisateur connecté --%>
                                <c:when test="${book.status == 'emprunte'}">
                                    <button class="status-button render-button" onclick="handleBookAction('${book.id_Book}', 'rendre')">Rendre</button>
                                </c:when>
                                <%-- Si le livre est disponible --%>
                                <c:when test="${book.status == 'disponible'}">
                                    <button class="status-button borrow-button" onclick="handleBookAction('${book.id_Book}', 'emprunter')">Emprunter</button>
                                </c:when>
                                <%-- Sinon (emprunté par un autre, réservé, etc.) --%>
                                <c:otherwise>
                                    <button class="status-button reserve-button" onclick="handleBookAction('${book.id_Book}', 'reserver')">Réserver</button>
                                </c:otherwise>
                            </c:choose>
                        </p>
                    </div>
                    <div class="card-full-details">
                        <p><strong>ID:</strong> ${book.id_Book}</p>
                        <p><strong>Auteur:</strong> ${book.author}</p>
                        <p><strong>Catégorie:</strong> ${book.category}</p>
                        <p><strong>Description:</strong> ${book.description}</p>
                        <p><strong>Année:</strong> ${book.year}</p>
                        <p><strong>Date:</strong> ${book.created_at}</p>
                    </div>
                </div>
            </c:forEach>
        </div>

        <a href="javascript:void(0);" class="add-student-button" onclick="ShowFormAddBook()">Ajouter un livre</a>

    </c:if>
    <p id="emptyListMessage" class="empty-list-message" style="display: none;"></p>

    <c:if test="${empty listbooks}">
        <p class="empty-list-message">Aucun livre trouvé dans la bibliothèque.</p>
        <a href="javascript:void(0);" class="add-student-button" onclick="showForm1()">Ajouter le premier livre</a>
    </c:if>

    <div class="overlay"></div>
    <jsp:include page="AddBooks.jsp" />


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

                if (!event.target.closest('.icon-container') && !event.target.closest('.card-full-details')) {
                    detail.classList.remove('active');
                }
            });
        });


        const searchForm = document.getElementById('searchBook');
        const bookGrid = document.getElementById('bookGrid'); 
        const searchMessageContainer = document.getElementById('searchMessageContainer');
        const emptyListMessage = document.getElementById('emptyListMessage'); // Pour le message d'absence de livre initial

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
                    method: 'POST',
                    body: formData,
                })
                .then(response => {

                    if (!response.ok) {
                        return response.text().then(text => {
                            console.error("Erreur (HTTP Status " + response.status + "):", text);
                            throw new Error(`Erreur HTTP ${response.status}: ${text}`);
                        });
                    }
                    return response.text();
                })
                .then(htmlContent => {
                    if (bookGrid) {
                        bookGrid.innerHTML = htmlContent;
                    }

                    const hasBooks = bookGrid && bookGrid.querySelector('.book-card') !== null;
                    
                    if (searchMessageContainer) {
                        if (!hasBooks) { 
                            searchMessageContainer.style.display = 'block';
                            searchMessageContainer.style.color = 'orange';
                            searchMessageContainer.textContent = 'Aucun livre trouvé correspondant à votre recherche.';
                        } else {
                            
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
