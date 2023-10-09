document.addEventListener('DOMContentLoaded', function() {
    const showFormButton = document.getElementById('showFormButton');
    const closeFormButton = document.getElementById('closeFormButton');
    const popupForm = document.getElementById('popupForm');
    const overlay = document.getElementById('overlay');

    showFormButton.addEventListener('click', function() {
        popupForm.style.display = 'block';
        overlay.style.display = 'block'; // Show the overlay
    });

    closeFormButton.addEventListener('click', function() {
        popupForm.style.display = 'none';
        overlay.style.display = 'none'; // Hide the overlay
    });

    // Close the popup and overlay when clicking on the overlay
    overlay.addEventListener('click', function() {
        popupForm.style.display = 'none';
        overlay.style.display = 'none';
    });
});
