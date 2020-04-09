	
const formatNamedDate = (dates) => {
	dates.forEach(date => {
		
		
		const monthNames = ["January", "February", "March", "April", "May", "June",
			  "July", "August", "September", "October", "November", "December"
		];
				
				const formattedDate = new Date(date.textContent);
				
				date.innerText = monthNames[formattedDate.getMonth()] + ' ' + formattedDate.getDate() + ', ' + formattedDate.getFullYear();
				
	});
}

const formatWeekDay = (dates) => {
	dates.forEach(day => {
		
		
		const dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
				
				const formattedDate = new Date(day.textContent);
				const dateToday = new Date();

				const todayYear = dateToday.getFullYear();
				const todayMonth = dateToday.getMonth() > 9 ? dateToday.getMonth() + 1 : `0${dateToday.getMonth() + 1}`;
				const todayDay = dateToday.getDate();



				const today = `${todayYear}-${todayMonth}-${todayDay}`;
				
				if(today === day.textContent) {
					day.innerText = "Today";
				} else {
					day.innerText = dayNames[formattedDate.getDay()];
				}
				
				
	});
}

const navLinks = document.querySelectorAll(".nav-link");
const addProcedures = document.getElementById('addProcedures');

if(location.pathname === "/patient/new") {
    navLinks[0].classList.add("active");
    addProcedures.style.display = "none";
    navLinks.forEach(navLink => {
        navLink.classList.add("disabled");
    });
}

navLinks.forEach(navLink => {

	
	if(navLink.href === location.href) {
		navLink.classList.add("active");
	}


});


const clickableRows = document.querySelectorAll('.clickable-row');

		clickableRows.forEach(clickableRow => {
			const link = clickableRow.getAttribute("data-href");

			clickableRow.addEventListener('click', function() {
				window.location = link;
			});
		});







