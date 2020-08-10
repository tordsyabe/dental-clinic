const setImage = (event) => {


	const file = document.querySelector('#for-upload');

	file.src = URL.createObjectURL(event.target.files[0]);

	$("#filename").val(event.target.files[0].name);
	openSideSlider();


};

$("#uploadFileForm").submit(function(){
   $("input[type=submit]", this).attr("disabled", true);
   $("input[type=submit]", this).val("Uploading...")
});

function handleDeletePatientFile() {

    const fileId = document.querySelector(".delete-modal").getAttribute("data-delete-id");

    if(fileId !== null) {
        $.ajax({
            type: "DELETE",
            url: "/api/patient-documents/" + fileId,
            contentType: "application/json",
            success: function() {
                $(`*[data-patient-file=${fileId}]`).remove();

                if($("#patient-document-list").children().length === 0) {
                    const url = window.location.pathname;

                    const patientId = url.substring(url.lastIndexOf('/') + 1);

                    $("#patient-document-list").replaceWith(`
                         <div class="d-flex flex-column justify-content-center align-items-center">
                                <img src="/images/docs.svg" width="60px">
                                <p class="mt-4">Let's add some documents</p>
                                <p class="text-muted small">Keep a record of patient documents for references.</p>
                            </div>
                    `);
                }

                toastr.success("Successfully deleted patient document", "Success");
                closeDeleteModal();
            },
            error: function(e) {
                console.log(e);
                toastr.error("Error deleting dental procedure", "Error");
            }
        });
    }

}