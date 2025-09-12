<%@ page pageEncoding="UTF-8" %>

    <div class="form-container">
        <i class="fa-solid fa-xmark" onclick="HideFormAddBook()" style="cursor: pointer; font-size: 30px; margin-top: 0px; margin-left: 443px;"></i>
        <h1 class="form-title">Ajouter un Nouveau Livre</h1>
        <form method="post" action="addBook" class="add-book-form" enctype="multipart/form-data" >
            <div class="form-group">
                <label for="title">Titre <span style="color: red; font-size: 17px;">*</span> :</label>
                <input type="text" name="title" id="title" required class="form-input">
            </div>
            <div class="form-group">
                <label for="author">Auteur <span style="color: red; font-size: 17px;">*</span> :</label>
                <input type="text" name="author" id="author" required class="form-input">
            </div>
            <div class="form-group"> <!-- Class form-group-inline retirée ici -->
                <label for="year">Année de publication <span style="color: red; font-size: 17px;">*</span> :</label>

                <input type="number" name="year" id="year" required class="form-input"><br>
                <span id="annee-error" style="color: red;"></span>
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
                <input type="file" name="image" id="image" class="form-input" placeholder="Ex: https://example.com/cover.jpg">
            </div>
            <div class="form-group">
                <label for="description">Description :</label>
                <textarea name="description" id="description" rows="7" class="form-textarea" placeholder="Entrez la description complète du livre ici..."></textarea>
            </div>
            <div class="form-group1">
                <div>
                    <button type="button" class="annuler" onclick="HideFormAddBook()">Annuler</button>
                </div>
                <div>
                    <input type="submit" class="submit-button" value="Ajouter" />
                </div>
            </div>
        </form>
        <script>
            const year = document.getElementById("year");
            const error_message = document.getElementById("annee-error");

            const annee_max = new Date().getFullYear();
            const annee_min = 1950;

            year.addEventListener('input' , function(event){
                const annee = parseInt(event.target.value);

                if(annee < annee_min || annee > annee_max){
                    error_message.textContent = "veuillez entrer un nombre entre "+annee_min+ " et "+annee_max;
                }else{
                    error_message.textContent = "";
                }
            });
            
        </script>
    </div>


