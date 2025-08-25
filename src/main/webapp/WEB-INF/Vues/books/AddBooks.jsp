<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ajouter un Nouveau Livre - Visuel</title>
    <style>
        /* Styles Généraux du Corps */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            background-color: #f0f2f5;
            color: #333;
            line-height: 1.6;
        }

        /* Conteneur principal du formulaire */
        .form-container {
            max-width: 450px; /* Réduction de la largeur maximale du formulaire */
            margin: 40px auto; /* Centre le formulaire horizontalement et réduit l'espace */
            padding: 20px; /* Réduction de l'espacement interne */
            background-color: #ffffff;
            border-radius: 10px; /* Coins légèrement moins arrondis */
            box-shadow: 0 6px 20px rgba(0, 0, 0, 0.12); /* Ombre ajustée */
        }

        /* Titre du formulaire */
        .form-title {
            text-align: center;
            color: #2c3e50;
            margin-bottom: 25px; /* Espacement ajusté */
            font-size: 1.8em; /* Réduction de la taille du titre */
            font-weight: 700;
        }

        /* Chaque groupe de champ (label + input/select/textarea) */
        .form-group {
            margin-bottom: 15px; /* Espacement réduit entre les groupes de champs */
        }

        /* Labels des champs */
        .form-group label {
            display: block; /* Chaque label prend sa propre ligne et est aligné à gauche */
            margin-bottom: 6px; /* Espacement réduit */
            color: #34495e;
            font-weight: 600;
            font-size: 0.9em; /* Réduction de la taille de la police des labels */
            text-align: left; /* Assure l'alignement à gauche pour tous les labels */
        }

        /* Styles pour les champs de saisie de texte, nombre et URL */
        .form-input,
        .form-select,
        .form-textarea {
            width: 100%;
            padding: 10px; /* Réduction de l'espacement interne des champs */
            border: 1px solid #dcdcdc;
            border-radius: 6px; /* Coins arrondis ajustés */
            font-size: 0.95em; /* Réduction de la taille de la police des champs */
            color: #555;
            background-color: #f8f8f8;
            box-sizing: border-box;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        /* Effet au focus des champs */
        .form-input:focus,
        .form-select:focus,
        .form-textarea:focus {
            border-color: #3498db;
            box-shadow: 0 0 0 2px rgba(52, 152, 219, 0.2); /* Ombre ajustée */
            outline: none;
            background-color: #ffffff;
        }

        /* Spécifique pour la zone de texte multiligne */
        .form-textarea {
            resize: vertical;
            min-height: 80px; /* Hauteur minimale réduite */
        }

        /* Groupe de boutons radio */
        .radio-group {
            display: flex;
            gap: 15px; /* Espacement réduit */
            align-items: center;
            margin-top: 8px; /* Espacement ajusté */
        }

        .radio-group input[type="radio"] {
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
            width: 18px; /* Réduction de la taille des radios */
            height: 18px; /* Réduction de la taille des radios */
            border: 2px solid #95a5a6;
            border-radius: 50%;
            position: relative;
            cursor: pointer;
            outline: none;
            transition: border-color 0.3s ease, background-color 0.3s ease;
        }

        .radio-group input[type="radio"]:checked {
            border-color: #3498db;
            background-color: #3498db;
        }

        .radio-group input[type="radio"]:checked::before {
            content: '';
            display: block;
            width: 8px; /* Réduction de la taille du point central */
            height: 8px; /* Réduction de la taille du point central */
            background-color: white;
            border-radius: 50%;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

        .radio-group label {
            margin-bottom: 0;
            cursor: pointer;
            font-weight: normal;
            font-size: 0.95em; /* Taille de la police des labels radio ajustée */
        }

        /* Bouton de soumission */
        .submit-button {
            display: block;
            width: 100%;
            padding: 12px 15px; /* Réduction de l'espacement interne */
            background-color: #2ecc71;
            color: white;
            border: none;
            border-radius: 6px; /* Coins arrondis ajustés */
            font-size: 1em; /* Taille de la police du bouton ajustée */
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s ease, transform 0.2s ease, box-shadow 0.3s ease;
            box-shadow: 0 3px 8px rgba(46, 204, 113, 0.25); /* Ombre ajustée */
        }

        .submit-button:hover {
            background-color: #27ae60;
            transform: translateY(-1px); /* Effet de "pop-up" réduit */
            box-shadow: 0 4px 10px rgba(46, 204, 113, 0.35);
        }

        /* --- Styles pour les champs Année et Catégorie --- */

        /* Réduction de la largeur de l'input Année */
        #year {
            max-width: 100px; /* Limite la largeur maximale de l'input */
        }

        /* Réduction de la largeur du select Catégorie */
        #category {
            max-width: 180px; /* Limite la largeur maximale du select */
        }
        
        /* Ajustement si nécessaire pour les petits écrans (maintenir la réactivité) */
        @media (max-width: 500px) {
            #year, #category {
                max-width: 100%; /* Permet aux champs de reprendre toute la largeur */
            }
        }

    </style>
</head>
<body>
    <div class="form-container">
        <h1 class="form-title">Ajouter un Nouveau Livre</h1>
        <form method="post" action="addBook" class="add-book-form" enctype="multipart/form-data" >
            <div class="form-group">
                <label for="title">Titre :</label>
                <input type="text" name="title" id="title" required class="form-input">
            </div>
            <div class="form-group">
                <label for="author">Auteur :</label>
                <input type="text" name="author" id="author" required class="form-input">
            </div>
            <div class="form-group"> <!-- Class form-group-inline retirée ici -->
                <label for="year">Année de publication :</label>
                <!-- Année maximale définie sur l'année actuelle -->
                <input type="number" name="year" id="year" required class="form-input">
            </div>
            <div class="form-group"> <!-- Class form-group-inline retirée ici -->
                <label for="category">Catégorie :</label>
                <select id="category" name="category" class="form-select">
                    <option value="fiction">Fiction</option>
                    <option value="roman">Roman</option>
                    <option value="horreur">Horreur</option>
                    <option value="litteraire">Littéraire</option>
                    <option value="non-fiction">Non-Fiction</option>
                    <option value="science-fiction">Science-Fiction</option>
                    <option value="fantastique">Fantastique</option>
                    <option value="biographie">Biographie</option>
                </select>
            </div>
            <div class="form-group">
                <label for="image">Image :</label>
                <input type="file" name="image" id="image" required class="form-input" placeholder="Ex: https://example.com/cover.jpg">
            </div>
            <div class="form-group">
                <label for="description">Description :</label>
                <textarea name="description" id="description" rows="7" class="form-textarea" placeholder="Entrez la description complète du livre ici..."></textarea>
            </div>
            <div class="form-group">
                <label>Statut :</label>
                <div class="radio-group">
                    <input type="radio" name="status" id="emprunte" value="emprunté">
                    <label for="emprunte">Emprunté</label>
                    <input type="radio" name="status" id="disponible" value="disponible" checked>
                    <label for="disponible">Disponible</label>
                </div>
            </div>
            <div class="form-group">
                <button type="submit" class="submit-button">Ajouter le Livre</button>
            </div>
        </form>
    </div>

</body>
</html>
