// Bootstrap's form validation

(function() {
	  'use strict';
	  window.addEventListener('load', function() {
		// Fetch all the forms we want to apply custom Bootstrap validation styles to
		var forms = document.getElementsByClassName('needs-validation');
		// Loop over them and prevent submission
		var validation = Array.prototype.filter.call(forms, function(form) {
		  form.addEventListener('submit', function(event) {
			if (form.checkValidity() === false) {
			  event.preventDefault();
			  event.stopPropagation();
			}
			form.classList.add('was-validated');
		  }, false);
		});
	  }, false);
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
				const todayDay = dateToday.getDate();

				const today = `${todayYear}-${todayMonth}-${todayDay}`;

				if(today === day.textContent) {
					day.innerText = "Today";
				} else {
					day.innerText = dayNames[formattedDate.getDay()];
				}

	});
}
//Complaint Section
(function(){
    let complaintFormShow = false;

    const complaintFormCard = document.querySelector('.complaintFormCard');
    const addComplaintBtn = document.querySelector('#addComplaint');
    const complaintSubmitBtn = document.querySelector('#complaintSubmitBtn');

    const complaintForm = document.querySelector('#complaintForm');

    const complaintList = document.querySelector('.complaint-list');

    addComplaint.addEventListener('click', () => {

        complaintFormShow = !complaintFormShow;
        if(complaintFormShow === false) {
            addComplaintBtn.classList.remove('fa-minus');
            complaintFormCard.style.display = "none";
        } else {
            addComplaintBtn.classList.add('fa-minus');
            complaintFormCard.style.display = "flex";
        }
    });

    if(complaintFormShow === false) {
        addComplaintBtn.classList.remove('fa-minus');
        complaintFormCard.style.display = "none";
    } else {
        addComplaintBtn.classList.add('fa-minus');
        complaintFormCard.style.display = "flex";
    }

    complaintSubmitBtn.addEventListener('click', () => {
        const complaintDesc = document.getElementById('complaintDesc').value;
        const complaintDate = document.getElementById('complaintDate').value;
        const newItem = document.createElement("div");
        newItem.classList.add('card');
        newItem.classList.add('mt-3');

        newItem.innerHTML = `
            <div class="card-body">
                <div class="row">
                    <div class="col-sm-10">
                        <p class="mb-0">${complaintDesc}</p>
                        <small class="text-muted">${complaintDate}</small>
                    </div>
                    <div class="col-sm-2 d-flex justify-content-center align-items-center">
                        <i class="fa fa-pencil icon-button mr-3"></i>
                        <i class="fa fa-trash icon-button"></i>

                    </div>
                </div>

            </div>
        `;

        complaintList.insertBefore(newItem, complaintList.childNodes[0]);
        complaintFormShow = false;
        addComplaintBtn.classList.remove('fa-minus');
        complaintFormCard.style.display = "none";

        const noComplaintP = document.querySelector('#noComplaintP');

        if(noComplaintP !== null) {
            complaintList.removeChild(noComplaintP);
        }
    });

})();
