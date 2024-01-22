
function initSelect() {
    const select = document.querySelector("[data-multiple-select]");
    if (select) {
        const dataValue = select.getAttribute('data-multiple-select');
        const input = document.querySelector("[data-multiple-select-input='"+dataValue+"']");
        if (input) {
            select.addEventListener('change', () => {
                let opts = [];
                for (const option of select.options) {
                    if (option.selected) {
                        opts.push(option);
                    }
                }
                input.value = "";
                for (const opt of opts) {
                    input.value += opt.text + " | ";
                }
            });
        }
    }
}

window.addEventListener('load', () => {
    initSelect();
});