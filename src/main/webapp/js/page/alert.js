function initAlert() {
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach((alert) => {
        setTimeout(() => {
            alert.classList.add('d-none');
        }, 8000);
    });
}

window.addEventListener('load', () => {
    initAlert();
});