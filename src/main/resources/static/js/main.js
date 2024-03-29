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


        $(".toggle-health-form").css({"pointer-events": "none"});
    }

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


// Function for formatting date to eg. January 1, 2020
const formatNamedDate = (dates) => {

	dates.forEach(date => {


		const monthNames = ["January", "February", "March", "April", "May", "June",
			  "July", "August", "September", "October", "November", "December"
		];

				const formattedDate = new Date(date.textContent);

				date.innerText = monthNames[formattedDate.getMonth()] + ' ' + formattedDate.getDate() + ', ' + formattedDate.getFullYear();

	});
}

// Formatting date and getting the name of the week from the given date
const formatWeekDay = (dates) => {
	dates.forEach(day => {

		const dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

				const formattedDate = new Date(day.textContent);
				const dateToday = new Date();

				const todayYear = dateToday.getFullYear();
				const todayMonth = dateToday.getMonth() > 9 ? dateToday.getMonth() + 1 : `0${dateToday.getMonth() + 1}`;
				const todayDay = dateToday.getDate() > 9 ? dateToday.getDate() : `0${dateToday.getDate()}`;

				const today = `${todayYear}-${todayMonth}-${todayDay}`;

				if(today === day.textContent) {
					day.innerText = "Today";
				} else {
					day.innerText = dayNames[formattedDate.getDay()];
				}

	});
}

function openSideSlider(){

    $(".side-slider").animate({
        "right": "0",

    });

    $(".side-overlay").show();
    $("#context-menu").removeClass("show").hide();
}

function closeSideSlider(){

    $(".side-slider").animate({
        "right": "-450px",
    }, function() {
        $(".side-slider .side-content").find(".mt-3").remove();
        $(".side-slider .side-content").find("h5 + #tooth-desc").remove();
        $(".side-slider .side-content").find("h5 + #tooth-number").remove();
    });

//    TODO: on close side content must remove children div inside




    $(".side-overlay").hide();
    $("#context-menu").removeClass("show").hide();

}

function openDeleteModal(id) {
    console.log(id);


    $(".delete-modal").css("display", "flex");
    document.querySelector(".delete-modal").setAttribute("data-delete-id", id);
}

function closeDeleteModal() {
    $(".delete-modal").css("display", "none");
    document.querySelector(".delete-modal").removeAttribute("data-delete-id");
}


