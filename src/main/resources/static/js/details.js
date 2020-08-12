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


//Handle delete complaint
const handleDeleteComplaint = (id) => {

    $(`[data-complaint-delete-form="${id}"]`).find(".btn-danger").addClass("disabled").html(`
        <div class="spinner-border spinner-border-sm" role="status">
          <span class="sr-only">Deleting</span>
        </div>
    `);

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

//Open delete form for procedures
function showProcedureDeleteForm(formId) {

    $(`*[data-procedure-delete-form=${formId}]`).slideDown().css({
        "opacity": "1",
        "transition": "opacity 100ms ease-in-out 250ms"
    });

    $(`[data-create-invoice-btn=${formId}]`).css("pointer-events", "none");
    $(`[data-print-invoice-btn=${formId}]`).css("pointer-events", "none");
}

//Close delete form for procedures
function closeProcedureDeleteForm(formId) {

    $(`*[data-procedure-delete-form=${formId}]`).slideUp().css({
        "opacity": "0",
        "transition": "opacity 150ms ease-in-out"
    });
    $(`[data-create-invoice-btn=${formId}]`).css("pointer-events", "auto");
    $(`[data-print-invoice-btn=${formId}]`).css("pointer-events", "auto");
}



//Open create invoice  form for procedures
function showProcedureInvoiceForm(formId) {
    $(`*[data-invoice-form=${formId}]`).slideDown().css({
        "opacity": "1",
        "transition": "opacity 100ms ease-in-out 250ms"
    });

    $(`*[data-delete-procedure-btn=${formId}]`).css("pointer-events", "none");
}

//Close create invoice form for procedures
function closeProcedureInvoiceForm(formId) {
    $(`*[data-invoice-form=${formId}]`).slideUp().css({
         "opacity": "0",
         "transition": "opacity 150ms ease-in-out"
     });

    $(`[data-delete-procedure-btn=${formId}]`).css("pointer-events", "auto");
}

//handle submit of create invoice for procedures
$(document).ready(function() {
$("[data-invoice-form]").each(function(index, form){


    $(this).find("form").submit(function(event) {
       event.preventDefault();
       console.log("THIS FORM", $(this));

       const form = $(this).serializeArray();

       $(this).find("#invoiceBtn").addClass("disabled").html(`
               <div class="spinner-border spinner-border-sm" role="status">
                 <span class="sr-only">Invoicing</span>
               </div>
           `);

      let invoiceObj = {};

      $.each(this, function(i, input){
           invoiceObj[input.name] = input.value;
      });

      console.log(invoiceObj);

              $.ajax({
                  type: "POST",
                  contentType: "application/JSON",
                  url: "/api/invoices",
                  dataType: "json",
                  data: JSON.stringify({
                      datePaid: invoiceObj.paymentDate,
                      cost: invoiceObj.cost,
                      procedureId: invoiceObj.procedureId,
                      paymentType: invoiceObj.paymentType
                  }),
                  success: function(result) {

                      const procedureCard = $(`*[data-procedure-card="${result.procedureDto.uuid}"]`);

                          if(result.paymentType === 'full' || result.paymentType === 'discounted') {
                              $(`*[data-invoice-form="${invoiceObj.procedureId}"]`).remove();
                              console.log($(".partial-payment").length > 0);
                              if(procedureCard.find(".badge-warning").length > 0) {
                                  procedureCard.find(".badge-warning").remove();

                              }

                               procedureCard.find("i#procedureIcon").removeClass("fa fa-circle fa-lg")
                                      .addClass("fa fa-check-circle fa-lg")
                                      .css("color", "#3498db");

                                      procedureCard.find(".col-3").addClass("d-flex justify-content-end align-items-center pr-4");

                                      procedureCard.find("[data-create-invoice-btn]").replaceWith(`
                                          <i class="fa fa-print icon-button"></i>
                                      `);

                                      procedureCard.find("[data-delete-procedure-btn]").css("pointer-events", "auto");
                          } else {
                              closeProcedureInvoiceForm(result.procedureDto.uuid);
                              procedureCard.find("form").find("#invoiceBtn").removeClass("disabled").html("Invoice");
                              procedureCard.find("#payment-details").append(`
                                  <small class="text-muted partial-payment">Partial payment: ${result.cost} as of ${result.datePaid}</small>

                              `);

                              procedureCard.find("#procedure-action").prepend(`
                                      <span class="badge badge-warning"
                                            style="position: absolute; top: -35%; right: -10%">
                                          partially paid
                                      </span>
                              `);
                          }

                      toastr.success("Procedure invoiced", "Success");

                  },
                  error: function(e) {
                      toastr.error("There was an error on creating invoice", "Error");
                  }
              });


    });
});
});


function openComplaintDeleteForm(complaintId) {
    console.log(complaintId);

    $(`*[data-complaint-delete-form=${complaintId}]`).slideDown().css({
        "opacity": "1",
        "transition": "opacity 100ms ease-in-out 250ms"
    });
}

function closeComplaintDeleteForm(complaintId) {

    $(`*[data-complaint-delete-form=${complaintId}]`).slideUp().css({
        "opacity": "0",
        "transition": "opacity 150ms ease-in-out"
    });
}

function openAllergyDeleteForm(allergyId) {
    console.log(allergyId);

    $(`*[data-allergy-delete-form=${allergyId}]`).slideDown().css({
        "opacity": "1",
        "transition": "opacity 100ms ease-in-out 250ms"
    });
}

function closeAllergyDeleteForm(allergyId) {

    $(`*[data-allergy-delete-form=${allergyId}]`).slideUp().css({
        "opacity": "0",
        "transition": "opacity 150ms ease-in-out"
    });
}

function openMedHistDeleteForm(medHistId) {
    $(`*[data-med-hist-delete-form=${medHistId}]`).slideDown().css({
        "opacity": "1",
        "transition": "opacity 100ms ease-in-out 250ms"
    });
}

function closeMedHistDeleteForm(medHistId) {
    $(`*[data-med-hist-delete-form=${medHistId}]`).slideUp().css({
        "opacity": "0",
        "transition": "opacity 150ms ease-in-out"
    });
}

//toggle-health-form and submit
$(document).ready(function() {
    $('.toggle-health-form').each(function(index, toggle){
        $(toggle).on("click", function() {
            $(toggle).toggleClass("fa-plus fa-minus");

                $(".healthNoteForms").eq(index).slideToggle().css({
                    "opacity":"1",
                    "transition": "opacity 100ms ease-in-out 250ms"
                });
        });

            $(".healthNoteForms").eq(index).find("form").submit(function(event) {
                   event.preventDefault();

                   let formObj = {};
                   let endPoint = "";

                   const inputs = $(this).serializeArray();
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
                                    <div class="card mb-3" data-comp-id="${data.uuid}">
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-10">
                                                    <p class="mb-0">${data.description}</p>
                                                    <small class="text-muted">${data.dateCreated}</small>
                                                </div>
                                                <div class="col-sm-2 d-flex justify-content-center align-items-center">
                                                    <i class="fa fa-trash icon-button"
                                                        onclick="openComplaintDeleteForm('${data.uuid}')"
                                                        style="display: none;"></i>

                                                </div>
                                                <div class="col-sm-12" data-complaint-delete-form="${data.uuid}">
                                                        <span class="text-muted small">Are you sure you want to delete this procedure?</span>
                                                        <div style="float: right;">
                                                            <a class="btn btn-secondary btn-sm" onclick="closeComplaintDeleteForm('${data.uuid}')">Cancel</a>
                                                            <a class="btn btn-danger btn-sm" onclick="handleDeleteComplaint('${data.uuid}')">Delete</a>
                                                        </div>


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
                                              <li class="py-1" data-allergy-id=${data.uuid}>
                                                 <i class="fa fa-check-circle pr-2"></i>
                                                 <span class="allergy-desc" onclick="openAllergyDeleteForm('${data.uuid}')">${data.description}</span>

                                                  <div  data-allergy-delete-form="${data.uuid}">
                                                     <span class="text-muted small">Are you sure you want to delete this procedure?</span>
                                                     <div style="float: right;">
                                                         <a class="btn btn-secondary btn-sm" onclick="closeAllergyDeleteForm('${data.uuid}')">Cancel</a>
                                                         <a class="btn btn-danger btn-sm" onclick="handleDeleteAllergy('${data.uuid}')">Delete</a>
                                                     </div>
                                                 </div>
                                              </li>
                                          `);
                                     } else {
                                        $(".allergy-list").append(`
                                        <ul class="mt-2" style="list-style: none; padding-left: 30px;" >
                                            <li class="py-1" data-allergy-id=${data.uuid} onclick="handleDeleteAllergy('${data.uuid}')">
                                                 <i class="fa fa-check-circle pr-2"></i>
                                                 <span class="allergy-desc">${data.description}</span>
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
                                               <li class="py-1" data-medicalhistory-id=${data.uuid} onclick="handleDeleteMedicalHistory('${data.uuid}')">
                                                  <i class="fa fa-check-circle pr-2"></i>
                                                  <span class="med-hist-desc" onclick="openMedHistDeleteForm('${data.uuid}')">${data.description}</span>

                                                  <div data-med-hist-delete-form="${data.uuid}">
                                                      <span class="text-muted small">Are you sure you want to delete this procedure?</span>
                                                      <div style="float: right;">
                                                          <a class="btn btn-secondary btn-sm" onclick="closeMedHistDeleteForm('${data.uuid}')">Cancel</a>
                                                          <a class="btn btn-danger btn-sm" onclick="handleDeleteMedHist('${data.uuid}')">Delete</a>
                                                      </div>
                                                  </div>
                                               </li>
                                           `);
                                      } else {
                                         $(".medical-history-list").append(`
                                         <ul class="mt-2" style="list-style: none; padding-left: 30px;" >
                                             <li class="py-1" data-medicalhistory-id=${data.uuid} onclick="handleDeleteMedicalHistory('${data.uuid}')">
                                                  <i class="fa fa-check-circle pr-2"></i>
                                                  <span class="med-hist-desc">${data.description}</span>
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

  $(`[data-allergy-delete-form="${id}"]`).find(".btn-danger").addClass("disabled").html(`
        <div class="spinner-border spinner-border-sm" role="status">
          <span class="sr-only">Deleting</span>
        </div>
    `);

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
const handleDeleteMedHist = (id) => {

      $(`[data-med-hist-delete-form="${id}"]`).find(".btn-danger").addClass("disabled").html(`
            <div class="spinner-border spinner-border-sm" role="status">
              <span class="sr-only">Deleting</span>
            </div>
        `);

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
const deleteProcedure = (procedureId) => {

        $(`[data-procedure-delete-form="${procedureId}"]`).find(".btn-danger").addClass("disabled").html(`
            <div class="spinner-border spinner-border-sm" role="status">
              <span class="sr-only">Deleting</span>
            </div>
        `);

    $.ajax({
        type: "DELETE",
        url: "/api/procedures/" + procedureId,
        contentType: "application/json",
        success: function() {
            $(`*[data-procedure="${procedureId}"]`).remove();

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


            toastr.success("Procedure successfully deleted", "Success");
        },
        error: function(e) {
            console.log(e);
            toastr.error("There was an error deleting procedure", "Error");
        }
    });
}

$("#uploadProfileForm").submit(function(){
   $("input[type=submit]", this).attr("disabled", true);
   $("#profileUploadBtn").text("Uploading...")
});
