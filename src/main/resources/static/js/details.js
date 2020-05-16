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
				const todayDay = dateToday.getDate() > 9 ? dateToday.getDate() : `0${dateToday.getDate()}`;

				const today = `${todayYear}-${todayMonth}-${todayDay}`;

				if(today === day.textContent) {
					day.innerText = "Today";
				} else {
					day.innerText = dayNames[formattedDate.getDay()];
				}

	});
}

//HOVER on complaint cards for edit and delete button
function displayActionBtn() {
    console.log("CALLED");
    const complaintCards = [...document.querySelector('.complaint-list').children];

    complaintCards.forEach(card => {
        card.addEventListener('mouseenter', () => {
            card.childNodes[1].childNodes[1].childNodes[3].childNodes[1].style.display = "inline-block";
            card.childNodes[1].childNodes[1].childNodes[3].childNodes[3].style.display = "inline-block";
        });

        card.addEventListener('mouseleave', () => {
                    card.childNodes[1].childNodes[1].childNodes[3].childNodes[1].style.display = "none";
                    card.childNodes[1].childNodes[1].childNodes[3].childNodes[3].style.display = "none";
                });
    });
}
//Invoking HOVER on complaint cards for edit and delete button
(displayActionBtn)();

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

    complaintSubmitBtn.addEventListener('click', (e) => {

        e.preventDefault();
        const complaintDesc = document.getElementById('complaintDesc').value;
        const complaintDate = document.getElementById('complaintDate').value;
        const comPatientId = document.getElementById('comPatientId').value;

        const noComplaintP = document.querySelector('#noComplaintP');

        if(noComplaintP !== null) {
            complaintList.removeChild(noComplaintP);
        }


        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "/api/complaints",
            data: JSON.stringify(
                {
                    patientId: comPatientId,
                    description: complaintDesc,
                    date: complaintDate
                }
            ),
            cache: false,
            timeout: 600000,
            success: function(data) {

                complaintFormShow = false;
                addComplaintBtn.classList.remove('fa-minus');
                complaintFormCard.style.display = "none";

                console.log("DATA", data);
                const newItem = document.createElement("div");
                    newItem.classList.add('card');
                    newItem.classList.add('mt-3');
                    newItem.setAttribute('data-comp-id', data.id);

                    newItem.innerHTML = `
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-10">
                                    <p class="mb-0">${data.description}</p>
                                    <small class="text-muted">${data.date}</small>
                                </div>
                                <div class="col-sm-2 d-flex justify-content-center align-items-center">
                                    <i class="fa fa-pencil icon-button" style="display: none;"></i>
                                    <i class="fa fa-trash icon-button"
                                        onclick="handleDeleteComplaint(${data.id})"
                                        style="display: none;"></i>

                                </div>
                            </div>

                        </div>
                    `;

                    complaintList.insertBefore(newItem, complaintList.childNodes[0]);
                    displayActionBtn();

            },
            error: function(e) {
                console.log("ERROR : ", e);
            }
        });
    });

})();

//Handle delete complaint
const handleDeleteComplaint = (id) => {

    $.ajax({
        type: "DELETE",
        url: "/api/complaints/" + id,
        success: function() {
           $(`*[data-comp-id="${id}"]`).remove();
           if($(".complaint-list").children().length === 0) {
               $(".complaint-list").prepend('<p class="text-muted mt-3" id="noComplaintP">No Complaints</p>');
           }
        },
        error: function(e) {
            console.log("tae");
        }
    });
}

(function(){
    const profileImage = document.querySelector('#profile-image');
    const changeP = document.querySelector('.avatar + p');

    profileImage.addEventListener('mouseenter', () => {
        changeP.style.display = "inline-block";
    });

    profileImage.addEventListener('mouseleave', () => {
        changeP.style.display = "none";
    });
})();

const loadFile = (event) => {
	const profileImage = document.querySelector('#profile-image');
	const profileUploadLabel = document.querySelector('#profileUploadBtn');


	profileImage.src = URL.createObjectURL(event.target.files[0]);
	profileUploadLabel.style.display = "inline-block";

};
//Open create invoice  form for procedures
(function() {
    const createInvoiceBtn = document.querySelectorAll('#create-invoice-btn');

    createInvoiceBtn.forEach(createInvBtn => {
        createInvBtn.addEventListener('click', () => {

            const procedureId = createInvBtn.getAttribute('data-procedure-id');
            const invoiceForm = document.querySelector(`[data-invoice-form-id="${procedureId}"]`);

            invoiceForm.style.display = "inline-block";

        });
    });
})();

//Close create invoice form for procedures
const handleCloseInvoiceForm = (invoiceFormId) => {
    const invoiceForm = document.querySelector(`[data-invoice-form-id="${invoiceFormId}"]`);
    invoiceForm.style.display = "none";

}

//handle submit of create invoice for procedures

const submitProcedureInvoice = (event, formId) => {
    event.preventDefault();

    const invoiceFormToSubmit = document.querySelector(`#invoice-form-${formId}`);

    const invoiceCost = invoiceFormToSubmit.elements[1].value;
    const invoiceDate = invoiceFormToSubmit.elements[2].value;



        $.ajax({
            type: "PUT",
            contentType: "application/JSON",
            url: "/api/procedures/" + formId,
            dataType: "json",
            data: JSON.stringify({
                datePaid: invoiceDate,
                cost: invoiceCost
            }),
            success: function(result) {
                console.log(result);
                //remove invoice form from DOM
                $(`*[data-invoice-form-id="${formId}"]`).remove();


                const procedureCard = $(`*[data-procedure-card="${result.id}"]`);

                    procedureCard.find("i").removeClass("fa fa-circle fa-lg")
                    .addClass("fa fa-check-circle fa-lg")
                    .css("color", "#3498db");

                    procedureCard.find("a#create-invoice-btn").remove();

            },
            error: function(e) {
                console.log(e);
            }
        });

    return false;
}