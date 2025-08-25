const modal = document.getElementById("modal");
const btn = document.getElementById("add-user-btn");
const span = document.getElementsByClassName("close")[0];
const addFormUrl = "addUser";

btn.onclick = function() {
    modal.style.display = "block";

    fetch(addFormUrl)
        .then(response => response.text())
        .then(html => {
            const parser = new DOMParser();
            const doc = parser.parseFromString(html, 'text/html');
            const form = doc.querySelector('.add-user-form');
            if (form) {
                document.getElementById('modal-body').innerHTML = '';
                document.getElementById('modal-body').appendChild(form);
            } else {
                document.getElementById('modal-body').innerHTML = "<p>Erreur : le formulaire n'a pas pu être chargé.</p>";
            }
        });
}

span.onclick = function() {
    modal.style.display = "none";
}

window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}