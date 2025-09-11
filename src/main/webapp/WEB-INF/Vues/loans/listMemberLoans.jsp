<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mes Réservations</title>
    <link rel="stylesheet" href="css/loans/listMemberLoans.css">
    <link rel="stylesheet" href="css/users/memberNavBar.css">
    <link rel="stylesheet" href="css/users/returnConfirm.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <jsp:include page="/WEB-INF/Vues/member/memberNavBar.jsp"/>
    <main class="dashboard-container">
        <h1>Mes Emprunts</h1>
        <a href="loanHistory"><i class="fa fa-history"></i><h3>Historique</h3></a>

        <c:if test="${not empty sessionScope.message}">
            <div class="alert success">${sessionScope.message} <i class="fa-solid fa-circle-check"></i></div>
            <c:remove var="message" scope="session"/>
        </c:if>
        <c:if test="${not empty sessionScope.error}">
            <div class="alert error">${sessionScope.error}</div>
            <c:remove var="error" scope="session"/>
        </c:if>

        <div class="section-card">
            <h3>Liste de mes emprunts</h3>
            <table class="user-table">
                <thead>
                    <tr>
                        <th>ID Emprunts</th>
                        <th>Titre du Livre</th>
                        <th>Date d'emprunts</th>
                        <th>Date limit</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${not empty loans}">
                            <c:forEach var="l" items="${loans}">
                                <tr>
                                    <td>${l.loan_id}</td>
                                    <td>${l.book_title}</td>
                                    <td>${l.formattedBorrowDate}</td>
                                    <td>${l.formattedDueDate}</td>
                                    <td>
                                        <button type="button"
                                                class="return-btn"
                                                onclick="openReturnModal('${l.loan_id}', '${l.book_title}')">
                                            Rendre
                                        </button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="text-align: center;">Vous n'avez emprunter aucun livre.</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

                        <div id="return-modal" class="modal" style="display:none;">
                            <div class="return-modal-content">
                                <span class="return-close-btn" onclick="closeReturnModal()">&times;</span>
                                <div id="return-modal-body"></div>
                            </div>
                        </div>

    </main>
    <script>
        window.onload = function() {
            var messageDiv = document.querySelector(".alert");
            if (messageDiv) {
                messageDiv.style.display = 'block';
                
            
                setTimeout(function() {
                    messageDiv.style.opacity = '0';
                    setTimeout(function() {
                        messageDiv.style.display = 'none';
                    }, 500);
                }, 5000);
            }
        };

        function openReturnModal(loanId, title) {
            let modalBody = document.getElementById("return-modal-body");

            let htmlContent = '';
            htmlContent += '<h3>Confirmation</h3>';
            htmlContent += '<p>Voulez-vous rendre le livre <b>' + title + '</b> ?</p>';
            htmlContent += '<form action="returnBook" method="post">';
            htmlContent += '    <input type="hidden" name="loanId" value="' + loanId + '">';
            htmlContent += '    <div class="return-actions">';
            htmlContent += '        <button type="button" onclick="closeReturnModal()">Annuler</button>';
            htmlContent += '        <button type="submit" class="return-btn">Rendre</button>';
            htmlContent += '    </div>';
            htmlContent += '</form>';

            modalBody.innerHTML = htmlContent;
            document.getElementById("return-modal").style.display = "flex";
        }

        function closeReturnModal() {
            document.getElementById("return-modal").style.display = "none";
            document.getElementById("return-modal-body").innerHTML = "";
        }

    </script>
</body>
</html>