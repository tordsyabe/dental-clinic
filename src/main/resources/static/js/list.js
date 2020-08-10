// Making table row clickable and href to patients details
(function(){

const clickableRows = document.querySelectorAll('.clickable-row');

    clickableRows.forEach(clickableRow => {
        const link = clickableRow.getAttribute("data-href");

        clickableRow.addEventListener('click', function() {
            window.location = link;
        });
    });
})();