// Adding ".active" class on the current page location
(function() {
    const current = location.pathname.split("/")[2];
    const navLinks = document.querySelectorAll(".nav-link.main");

    for(i = 0; i < navLinks.length; i++) {
        if(navLinks[i].getAttribute("href").indexOf(current) !== -1) {
            navLinks[i].classList.add("active");
        }
    }

    if(current === "new") {
        navLinks[0].classList.add("active");
        addProcedures.style.display = "none";
        navLinks.forEach(navLink => {
            navLink.classList.add("disabled");
        });
    }

})();


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

(function(){
    const sidebarLinks = document.querySelectorAll('#sticky-sidebar a');

    const current = location.pathname.split("/")[1];
    const currentPaths = location.pathname.split("/");

    for(i = 0; i < sidebarLinks.length; i++) {
        if(current === "") {
            return;
        }
        if(sidebarLinks[i].getAttribute("href").indexOf(current) !== -1) {
            sidebarLinks[i].firstElementChild.classList.add("active-icon");
        }
    }

})();






