const setImage = (event) => {


	const file = document.querySelector('#for-upload');

	file.src = URL.createObjectURL(event.target.files[0]);

	$("#filename").val(event.target.files[0].name);
	openSideProcedures();


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