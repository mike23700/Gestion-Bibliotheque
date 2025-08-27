<%-- /WEB-INF/jsp/bookCardsFragment.jsp --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
    <c:when test="${not empty searchResults}">
        <c:forEach var="book" items="${searchResults}" varStatus="num">
            <div class="book-card">
                <div class="image-And-icon-container">
                    <div class="icon-container" onclick="toggleBookDetails(this)">
                        <i class="fa-solid fa-ellipsis-vertical"></i>
                    </div>
                    <div class="card-image-wrapper">
                        <img src="${pageContext.request.contextPath}/${book.image}" alt="Couverture du livre" class="book-card-image" onerror="this.onerror=null;this.src='https://placehold.co/200x300/e0e0e0/555555?text=Livre';"/>
                    </div>
                </div>
                <div class="card-initial-details">
                    <strong><p class="card-title">${book.title}</p></strong>
                    <p class="card-year">${book.status}</p>
                </div>
                <div class="card-full-details">
                    <p></p>
                    <p><strong>ID:</strong> ${book.id_Book}</p>
                    <p><strong>Auteur:</strong> ${book.author}</p>
                    <p><strong>Catégorie:</strong> ${book.category}</p>
                    <p><strong>Description:</strong> ${book.description}</p>
                    <p><strong>Année:</strong> ${book.year}</p>
                    <p><strong>Nb Emprunts:</strong> ${book.loan_count}</p>
                    <p><strong>Créé le:</strong> ${book.created_at}</p>
                </div>
            </div>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <p class="empty-list-message" style="grid-column: 1 / -1; text-align: center; width: 100%;">Aucun livre trouvé correspondant à votre recherche.</p>
    </c:otherwise>
</c:choose>