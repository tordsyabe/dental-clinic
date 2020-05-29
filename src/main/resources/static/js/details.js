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

//Handle delete complaint
const handleDeleteComplaint = (id) => {

    $.ajax({
        type: "DELETE",
        url: "/api/complaints/" + id,
        contentType: "application/json",
        success: function() {
           $(`*[data-comp-id="${id}"]`).remove();
           if($(".complaint-list").children().length === 0) {
               $(".complaint-list").prepend('<h5 class="text-muted mt-3 pl-2" id="noComplaintP">No Complaints</h5>');
           }

           toastr.success("Complaint successfully deleted", "Success");
        },
        error: function(e) {

            toastr.error("There was an error on deleting the complaint", "Error");
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
                cost: invoiceCost,
                dateCreated: Date.now()
            }),
            success: function(result) {
                console.log(result);
                //remove invoice form from DOM
                $(`*[data-invoice-form-id="${formId}"]`).remove();


                const procedureCard = $(`*[data-procedure-id="${result.id}"]`);

                    procedureCard.find("i#procedureIcon").removeClass("fa fa-circle fa-lg")
                    .addClass("fa fa-check-circle fa-lg")
                    .css("color", "#3498db");

                    procedureCard.find(".col-3").addClass("d-flex justify-content-end align-items-center pr-4");

                    procedureCard.find("#create-invoice-btn").replaceWith(`
                        <i class="fa fa-print icon-button"></i>
                    `);

                toastr.success("Procedure invoiced", "Success");

            },
            error: function(e) {
                console.log(e);
            }
        });

    return false;
}

//toggle-health-form and submit
$(document).ready(function() {
    $('.toggle-health-form').each(function(index, toggle){
        $(toggle).on("click", function() {
            $(toggle).toggleClass("fa-plus fa-minus");

            if($(toggle).hasClass("fa-plus")) {
                $(".healthNoteForms").eq(index).css("display", "none");
            } else {
                $(".healthNoteForms").eq(index).css("display", "block");
            }


        });

            $(".healthNoteForms").eq(index).find("form").submit(function(event) {
                   event.preventDefault();

                   let formObj = {};
                   let endPoint = "";

                   const inputs = $(this).serializeArray();
                   console.log("INPUTS", inputs);
                   $.each(inputs, function(i, input){
                        formObj[input.name] = input.value;
                   });

                   console.log("FORM OBJ",formObj);
                   console.log("FORM ID",this.id);
                   switch(this.id) {
                        case "allergyForm":
                            endPoint = "allergies";
                            break;
                        case "complaintForm":
                            endPoint = "complaints";
                            break;
                        case "medicalHistoryForm":
                            endPoint = "medicalhistories";
                            break;
                        default:
                            endPoint = "";

                   }

                   $.ajax({
                        type: "POST",
                        contentType: "application/json",
                        url: "/api/" + endPoint,
                        data: JSON.stringify(formObj),
                        success: function(data) {
                            console.log(data);
                            console.log("FROM POST AJAX");

                            switch(endPoint) {

                                case "complaints":
                                console.log("FROM COMPLAINTS CASE");
                                    $(".complaint-list").prepend(`
                                    <div class="card mt-3" data-comp-id=${data.id}>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-10">
                                                    <p class="mb-0">${data.description}</p>
                                                    <small class="text-muted">${data.dateCreated}</small>
                                                </div>
                                                <div class="col-sm-2 d-flex justify-content-center align-items-center">
                                                    <i class="fa fa-trash icon-button"
                                                        onclick="handleDeleteComplaint(${data.id})"
                                                        style="display: none;"></i>

                                                </div>
                                            </div>

                                       </div>
                                    </div>
                                    `);

                                    showComplaintActionBtn();
                                    if($(".complaint-list").length > 0) {
                                        $("#noComplaintP").remove();
                                    }

                                    toastr.success("Complaint added", "Success");

                                    break;
                                 case "allergies":
                                    console.log("FROM ALLERGIES CASE");
                                     if($(".allergy-list").find("ul").length > 0) {
                                          $(".allergy-list").find("ul").append(`
                                              <li class="py-1" data-allergy-id=${data.id} onclick="handleDeleteAllergy(${data.id})">
                                                 <i class="fa fa-check-circle pr-2"></i>
                                                 <span>${data.description}</span>
                                              </li>
                                          `);
                                     } else {
                                        $(".allergy-list").append(`
                                        <ul class="mt-2" style="list-style: none; padding-left: 30px;" >
                                            <li class="py-1" data-allergy-id=${data.id} onclick="handleDeleteAllergy(${data.id})">
                                                 <i class="fa fa-check-circle pr-2"></i>
                                                 <span>${data.description}</span>
                                            </li>
                                        </ul>
                                        `);
                                     }

//                                     showAllergyDeleteBtn();
                                     toastr.success("Allergy added", "Success");
                                     if($(".allergy-list").length > 0) {
                                         $("#noAllergiesP").remove();
                                     }

                                     break;
                                     case "medicalhistories":
                                     console.log("FROM MEDICAL HISTORY CASE");
                                      if($(".medical-history-list").find("ul").length > 0) {
                                           $(".medical-history-list").find("ul").append(`
                                               <li class="py-1" data-medicalhistory-id=${data.id} onclick="handleDeleteMedicalHistory(${data.id})">
                                                  <i class="fa fa-check-circle pr-2"></i>
                                                  <span>${data.description}</span>
                                               </li>
                                           `);
                                      } else {
                                         $(".medical-history-list").append(`
                                         <ul class="mt-2" style="list-style: none; padding-left: 30px;" >
                                             <li class="py-1" data-medicalhistory-id=${data.id} onclick="handleDeleteMedicalHistory(${data.id})">
                                                  <i class="fa fa-check-circle pr-2"></i>
                                                  <span>${data.description}</span>
                                             </li>
                                         </ul>
                                         `);
                                      }

 //                                     showAllergyDeleteBtn();
                                      toastr.success("Medical History added", "Success");
                                      if($(".medical-history-list").length > 0) {
                                          $("#noMedicalHistoryP").remove();
                                      }

                                      break;
                                default:

                            }


                            $(".healthNoteForms").eq(index).css("display", "none");
                            $(toggle).removeClass("fa-minus");
                            $(toggle).addClass("fa-plus");
                            $(".healthNoteForms").eq(index).find("form").get(0).reset();
                        },
                         error: function(e) {
                            console.log(e);
                            toastr.error(`There was an error adding ${endPoint}`, "Error");
                         }
                   });
            });
    });
});

//DELETING ALLERGY BY ID AJAX

const handleDeleteAllergy = (id) => {

    $.ajax({
            type: "DELETE",
            url: "/api/allergies/" + id,
            contentType: "application/json",
            success: function() {
               $(`*[data-allergy-id="${id}"]`).remove();
               if($(".allergy-list ul").children().length === 0) {
                   $(".allergy-list").prepend('<h5 class="text-muted mt-3 pl-2" id="noAllergiesP">No Allergies</h5>');
               }

               toastr.success("Allergy successfully deleted", "Success");

            },
            error: function(e) {
                console.log(e);
                toastr.error("There was an error on deleting the allergy", "Error");
            }
        });

}

//DELETING MEDICAL HISTORY AJAX
const handleDeleteMedicalHistory = (id) => {
    $.ajax({
        type: "DELETE",
        url: "/api/medicalhistories/" + id,
        contentType: "application/json",
        success: function() {
            $(`*[data-medicalhistory-id="${id}"]`).remove();
            if($(".medical-history-list ul").children().length === 0) {
               $(".medical-history-list").prepend('<h5 class="text-muted mt-3 pl-2" id="noMedicalHistoryP">No Medical History</h5>');
            }

            toastr.success("Medical history successfully deleted", "Success");
        },
        error: function(e) {
            console.log(e);
            toastr.error("There was an error on deleting the medical history", "Error");
        }
    });
}

//HOVER on complaint cards for edit and delete button
function showComplaintActionBtn() {
    $(".complaint-list .card").mouseenter(function(){
        $(this).find("i").css("display", "block");
    });

    $(".complaint-list .card").mouseleave(function(){
        $(this).find("i").css("display", "none");
    });
};

(showComplaintActionBtn)();


//PROCEDURE DELETE AJAX
const handleDeleteProcedure = (id) => {
    $.ajax({
        type: "DELETE",
        url: "/api/procedures/" + id,
        contentType: "application/json",
        success: function() {
            console.log("Deleted");
            $(`*[data-procedure-item="${id}"]`).remove();

            if($("#procedure-card-item").find(".card-body").children().length === 0) {
                const url = window.location.pathname;

                const patientId = url.substring(url.lastIndexOf('/') + 1);

               $("#procedure-card-item").find(".card-body").append(`
                    <div class="d-flex flex-column justify-content-center align-items-center"
                                 style="height: 100%;">
                                <h5 class="text-muted mb-5">No Procedures</h5>
                                <a class="btn btn-info" href="/patient/procedures/${patientId}"
                                   id="addProcedures">Add Procedures</a>
                            </div>
               `);
            }

            console.log($("#procedure-card-item ").find(".card-body"));

            toastr.success("Procedure successfully deleted", "Success");
        },
        error: function(e) {
            console.log(e);
            toastr.error("There was an error deleting procedure", "Error");
        }
    });
}
