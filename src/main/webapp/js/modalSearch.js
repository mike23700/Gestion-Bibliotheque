document.addEventListener("DOMContentLoaded", function() {
    const searchForm = document.getElementById('search-user-form');
    const searchModal = document.getElementById('search-modal');
    const searchResultsBody = document.getElementById('search-results-body');
    const closeBtn = searchModal.querySelector('.close-btn');

    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            e.preventDefault();

            const formData = new FormData(searchForm);
            const searchParams = new URLSearchParams(formData).toString();

            fetch(searchForm.action + '?' + searchParams, { method: 'POST' })
                .then(response => response.text())
                .then(html => {
                    const parser = new DOMParser();
                    const doc = parser.parseFromString(html, 'text/html');
                    const resultsSection = doc.querySelector('.section-card');

                    if (resultsSection) {
                         searchResultsBody.innerHTML = '';
                         searchResultsBody.appendChild(resultsSection);

                         searchModal.style.display = 'block';
                     } else {
                         searchResultsBody.innerHTML = '<div class="alert info">Aucun résultat trouvé.</div>';
                         searchModal.style.display = 'block';
                     }
                })
                .catch(error => {
                    console.error('Erreur lors de la recherche:', error);
                    searchResultsBody.innerHTML = '<div class="alert error">Erreur lors du chargement des résultats.</div>';
                    searchModal.style.display = 'block';
                });
        });
    }

    if (closeBtn) {
        closeBtn.onclick = function() {
            searchModal.style.display = 'none';
        }
    }
    window.onclick = function(event) {
        if (event.target == searchModal) {
            searchModal.style.display = 'none';
        }
    }
});