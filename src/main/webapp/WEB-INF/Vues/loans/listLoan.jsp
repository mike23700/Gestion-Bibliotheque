<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Réservations</title>
    <link rel="stylesheet" href="css/loans/listAdminLoans.css">
    <link rel="stylesheet" href="css/users/adminNavBar.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/admin/adminNavBar.jsp"/>
    <main class="dashboard-container">
        <h1>Gestion Des Emprunts</h1>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert success">${sessionScope.message}</div>
            <c:remove var="message" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.error}">
            <div class="alert error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

<div class="top-controls">
    <form action="listLoan" method="get" class="filter-form">
        <label for="status">Filtrer par statut :</label>
        <select name="status" id="status" onchange="this.form.submit()">
            <option value="" ${empty param.status ? 'selected' : ''}>Tous</option>
            <option value="null" ${param.status eq 'null' ? 'selected' : ''}>Non retourné</option>
        </select>
    </form>

    <form action="listLoan" method="get" class="search-user-form">
        <select name="searchType">
            <option value="userId" ${param.searchType eq 'userId' ? 'selected' : ''}>ID Utilisateur</option>
            <option value="userName" ${param.searchType eq 'userName' ? 'selected' : ''}>Nom d'utilisateur</option>
            <option value="bookName" ${param.searchType eq 'bookName' ? 'selected' : ''}>Titre du livre</option>
        </select>

        <div class="search-input-container">
            <input type="text" name="searchValue" placeholder="Rechercher..."
                   required value="${param.searchValue != null ? param.searchValue : ''}">
            <button type="button" class="clear-search-btn"><i class="fas fa-times"></i></button>
        </div>

        <button type="submit"><i class="fas fa-search"></i></button>
    </form>
</div>

        <div class="section-card">
            <h3>Liste emprunts</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID Emprunts</th>
                        <th>Emprunteur</th>
                        <th>Titre du Livre</th>
                        <th>Date d'emprunts</th>
                        <th>Date limit</th>
                        <th>Date retour</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty loans}">
                            <c:forEach var="loan" items="${loans}">
                                <tr>
                                    <td>${loan.loan_id}</td>
                                    <td>${loan.username}</td>
                                    <td>${loan.book_title}</td>
                                    <td>${loan.formattedBorrowDate}</td>
                                    <td>${loan.formattedDueDate}</td>
                                    <td>${loan.formattedReturnDate}</td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align: center;">Aucun résultat pour cette recherche.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

                <script>
                    document.addEventListener('DOMContentLoaded', function() {
                        const searchForm = document.querySelector('.search-user-form');
                        const searchInput = searchForm.querySelector('input[name="searchValue"]');
                        const clearBtn = searchForm.querySelector('.clear-search-btn');

                        function toggleClearBtn() {
                            if (searchInput.value.length > 0) {
                                clearBtn.style.display = 'block';
                            } else {
                                clearBtn.style.display = 'none';
                            }
                        }

                        searchInput.addEventListener('input', toggleClearBtn);
                        clearBtn.addEventListener('click', function() {
                            searchInput.value = '';
                            toggleClearBtn();
                            searchForm.submit();
                        });

                        toggleClearBtn();
                    });
                </script>
    </main>
</body>
</html>