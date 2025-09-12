document.addEventListener('DOMContentLoaded', function () {
    const telEl = document.getElementById('tel_num');
    const emailEl = document.getElementById('email');
    const form = document.querySelector('.add-user-form');

    const telRegex = /^6[0-9]{8}$/;
    const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}$/;

    function ensureError(el, id) {
        let err = document.getElementById(id);
        if (!err) {
            err = document.createElement('small');
            err.id = id;
            err.style.color = 'red';
            err.style.display = 'block';
            err.style.marginTop = '6px';
            el.parentNode.appendChild(err);
        }
        return err;
    }

    if (telEl) {
        const telErr = ensureError(telEl, 'tel-error');
        telEl.addEventListener('input', function () {
            const v = telEl.value.trim();
            if (v === '') {
                telErr.textContent = '';
            } else if (!telRegex.test(v)) {
                telErr.textContent = 'Format attendu : 6xxxxxxxx (9 chiffres).';
            } else {
                telErr.textContent = '';
            }
        });
    } else {
        console.warn('formValidation.js: élément #tel_num introuvable.');
    }

    if (emailEl) {
        const emailErr = ensureError(emailEl, 'email-error');
        emailEl.addEventListener('input', function () {
            const v = emailEl.value.trim();
            if (v === '') {
                emailErr.textContent = '';
            } else if (!emailRegex.test(v)) {
                emailErr.textContent = 'Format attendu : exemple@gmail.com';
            } else {
                emailErr.textContent = '';
            }
        });
    } else {
        console.warn('formValidation.js: élément #email introuvable.');
    }

    if (form) {
        form.addEventListener('submit', function (e) {
            const telVal = telEl ? telEl.value.trim() : '';
            const emailVal = emailEl ? emailEl.value.trim() : '';
            let hasError = false;

            if (telEl && !telRegex.test(telVal)) {
                hasError = true;
                ensureError(telEl, 'tel-error').textContent = 'Le numéro doit être au format 6xxxxxxxx (9 chiffres).';
            }

            if (emailEl && !emailRegex.test(emailVal)) {
                hasError = true;
                ensureError(emailEl, 'email-error').textContent = 'L\'adresse email doit être au format correct (ex. mike@gmail.com).';
            }

            if (hasError) {
                e.preventDefault();
                // focus sur le premier champ invalide
                if (telEl && !telRegex.test(telVal)) telEl.focus();
                else if (emailEl && !emailRegex.test(emailVal)) emailEl.focus();
            }
        });
    } else {
        console.warn('formValidation.js: formulaire .add-user-form introuvable.');
    }
});
