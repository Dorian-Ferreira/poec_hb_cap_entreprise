function initFormProfile() {
    const buttons = document.querySelectorAll("[data-hide-show-button]");
    if (buttons) {
        buttons.forEach((button) => {
            const dataAttr = button.getAttribute("data-hide-show-button");
            const container = document.querySelector("[data-hide-show-container='"+dataAttr+"']");
            if (container) {
                button.addEventListener('click', () => {
                    container.classList.toggle("d-none");
                });
            }
        });
    }
}

window.addEventListener('load', () => {
    initFormProfile();
});