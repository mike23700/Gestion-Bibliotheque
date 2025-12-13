<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription - BiblioTech</title>
    <link rel="stylesheet" href="css/signUp.css">
    <link rel="icon" type="image/png" href="assets/favicon.png" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <header>
            <div class="logo">
                <a href="/bibliotech">
                    <img src="assets/logo2.png" alt="Logo" class="logo-img">
                </a>
            </div>
            <a href="login" class="btn-login">Se connecter</a>
        </header>

        <main>
            <div class="form-container">
                <h2>Créer un compte BiblioTech</h2>

                <c:if test="${not empty sessionScope.error}">
                    <p class="message-error"><c:out value="${sessionScope.error}"/></p>
                    <c:remove var="error" scope="session"/>
                </c:if>

                <c:if test="${not empty sessionScope.message}">
                    <p class="message-success"><c:out value="${sessionScope.message}"/></p>
                    <c:remove var="message" scope="session"/>
                </c:if>

                <form action="signup" method="POST">

                    <div class="form-group">
                        <label for="username">Nom d'utilisateur :</label>
                        <input type="text" id="username" name="username" required>
                    </div>

                    <div class="form-group">
                        <label for="surname">Prénom :</label>
                        <input type="text" id="surname" name="surname" required>
                    </div>

                    <div class="form-group">
                        <label for="email">Adresse Email :</label>
                        <input type="email" id="email" name="email" required>
                    </div>

                    <div class="form-group">
                        <label for="tel_num">Téléphone (6xxxxxxxx) :</label>
                        <input type="text" id="tel_num" name="tel_num" pattern="6[0-9]{8}" title="Doit commencer par '6' et contenir 9 chiffres." required>
                    </div>

                    <div class="form-group">
                        <label for="password">Mot de passe :</label>
                        <input type="password" id="password" name="password" required>
                    </div>

                    <div class="form-group">
                        <label for="confirm_password">Confirmer le mot de passe :</label>
                        <input type="password" id="confirm_password" name="confirm_password" required>
                    </div>

                    <button type="submit" class="btn-submit">S'inscrire</button>
                </form>

                <a href="login" class="link-login">Déjà un compte ? Connectez-vous</a>
            </div>
        </main>
    </div>
</body>
</html>