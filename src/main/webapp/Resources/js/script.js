document.addEventListener('DOMContentLoaded', function () {
    const showFormButtons = document.querySelectorAll('.showFormButton');
    const closeFormButton = document.getElementById('closeFormButton');
    const popupForm = document.getElementById('popupForm');
    const overlay = document.getElementById('overlay');
    // update form
    // const showUpdateForm = document.getElementById('update-button');
    const closeUpdateFormButton = document.getElementById('closeUpdateFormButton');
    const updatePopupForm = document.getElementById('updatePopupForm');
    const updateOverlay = document.getElementById('updateOverlay');
    const cancelDeleteButton = document.getElementById('cancelDeleteButton');
    const confirmationBox = document.getElementById('confirmationBox');

    closeUpdateFormButton.addEventListener('click', function () {
        updatePopupForm.style.display = 'none';
        updateOverlay.style.display = 'none';
    });

    // Close the popup and overlay when clicking on the overlay
    updateOverlay.addEventListener('click', function () {
        updatePopupForm.style.display = 'none';
        updateOverlay.style.display = 'none';
    });


    showFormButtons.forEach(function (button) {
        button.addEventListener('click', function () {
            popupForm.style.display = 'block';
            overlay.style.display = 'block'; // Show the overlay
        });
    });

    closeFormButton.addEventListener('click', function () {
        popupForm.style.display = 'none';
        overlay.style.display = 'none';
    });

    // Close the popup and overlay when clicking on the overlay
    overlay.addEventListener('click', function () {
        popupForm.style.display = 'none';
        overlay.style.display = 'none';
    });

    const alertModal = document.getElementById('alertModal');
    if (alertModal) {
        setTimeout(function () {
            alertModal.style.display = 'none';
        }, 2500)
        const closeIcon = document.querySelector('.close');
        closeIcon.addEventListener('click', function () {
            alertModal.style.display = 'none';
        });
        window.addEventListener('click', function (event) {
            if (event.target === alertModal) {
                alertModal.style.display = 'none';
            }
        });
    }

    cancelDeleteButton.addEventListener('click', function () {
        document.getElementById('confirmationBox').style.display = 'none';
    });
    window.addEventListener('click', function (event) {
        if (event.target === confirmationBox) {
            document.getElementById('confirmationBox').style.display = 'none';
        }
    });
});

function populateUpdateForm(code, firstName, lastName, birthDate, mobile, address) {
    document.getElementById('updateCode').value = code;
    document.getElementById('updateFirstName').value = firstName;
    document.getElementById('updateLastName').value = lastName;
    document.getElementById('updateBirthDate').value = birthDate;
    document.getElementById('updateMobile').value = mobile;
    document.getElementById('updateAddress').value = address;
    document.getElementById('updatePopupForm').style.display = 'block';
    document.getElementById('updateOverlay').style.display = 'block';
}

function populateDelete(code) {
    const confirmationBox = document.getElementById('confirmationBox');
    document.getElementById('delete-code').value = code;
    confirmationBox.style.display = "flex";
    confirmationBox.style.justifyContent = 'center';
    confirmationBox.style.alignItems = 'center';
}

function populateUpdate(matricule, firstName, lastName, birthDate, mobile, email, recruitmentDate) {
    document.getElementById('updateMatricule').value = matricule;
    document.getElementById('updateFirstName').value = firstName;
    document.getElementById('updateLastName').value = lastName;
    document.getElementById('updateBirthDate').value = birthDate;
    document.getElementById('updateMobile').value = mobile;
    document.getElementById('updateEmail').value = email;
    document.getElementById('updateRecruitmentDate').value = recruitmentDate;
    document.getElementById('updatePopupForm').style.display = 'block';
    document.getElementById('updateOverlay').style.display = 'block';
}

function populate(matricule) {
    const confirmationBox = document.getElementById('confirmationBox');
    document.getElementById('delete-matricule').value = matricule;
    confirmationBox.style.display = "flex";
    confirmationBox.style.justifyContent = 'center';
    confirmationBox.style.alignItems = 'center';
}

