
    // Get the modal
    var modal = document.getElementById('logoutModal');

    // Get the button that opens the modal
    var btn = document.getElementById("logoutButton");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close-btn")[0];

    // Get the "Cancel" button in the modal footer
    var cancelBtn = document.getElementsByClassName("modal-cancel-btn")[0];

    // When the user clicks the button, open the modal
    btn.onclick = function(event) {
        event.preventDefault(); // Prevents the anchor tag from navigating
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks the "Cancel" button, close the modal
    cancelBtn.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }