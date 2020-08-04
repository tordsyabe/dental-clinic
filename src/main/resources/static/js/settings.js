(function() {
    $(".settings-item-list").click(function(){
        $(this).siblings().removeClass("active");
    });
})();



(function(){

    $("#settings-lists .settings-item-list").first().addClass("active");
    $("#categories-lists .settings-item-list").first().addClass("active");
    $(".categories-tab-pane").first().addClass("active show");



})();

//SAVE new/updated dental procedure

$(".dental-procedure-form").submit(function(event) {
    event.preventDefault();

    let dentalProcedureObj = {};

    const inputs = $(this).serializeArray();
    $.each(inputs, function(i, input){
        dentalProcedureObj[input.name] = input.value;
    });

    console.log(dentalProcedureObj);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/api/dental-procedures",
        data: JSON.stringify(dentalProcedureObj),
        success: function(data) {

            if(dentalProcedureObj.uuid === "") {
                        $(`#list-${dentalProcedureObj.categoryId}`).find(".card-body").append(`
                             <div class="row mt-2" id="${data.uuid}">
                                   <div class="col-5">
                                       <span class="text-capitalize procedure-description">${data.description}</span>
                                   </div>
                                   <div class="col-4">
                                       <span class="procedure-cost">${data.cost}</span>
                                   </div>
                                   <div class="col-3">
                                       <i class="fa fa-edit icon-button" onclick="handleEditDentalProcedure('${data.uuid}')"></i>
                                       <i class="fa fa-trash icon-button" onclick="openDeleteModal('${data.uuid}')"></i>
                                   </div>
                               </div>
                        `);
            } else {
                        $(`div#${data.uuid}`).find(".procedure-description").text(data.description);
                        $(`div#${data.uuid}`).find(".procedure-cost").text(data.cost);
            }


            $(`*[data-dental-form="${dentalProcedureObj.categoryId}"]`).get(0).reset();
            $(`*[data-dental-form="${dentalProcedureObj.categoryId}"]`).find("input[name=uuid]").val("");

            toastr.success("Successfully added dental procedure", "Success");


        },
        error: function(e) {
            console.log(e);
            toastr.error("Error saving dental procedure", "Error");
        }
    });
});

function handleEditDentalProcedure(dentalProcedureId) {



    const description = $(`div#${dentalProcedureId}`).find(".procedure-description").text();
    const cost = $(`div#${dentalProcedureId}`).find(".procedure-cost").text();

    const form = $(`div#${dentalProcedureId}`).parents(".categories-tab-pane").find("form");

    $(form).find("input[name=uuid]").val(dentalProcedureId);
    $(form).find("input[name=description]").val(description);
    $(form).find("input[name=cost]").val(cost);

    console.log(description, cost);
}

//custome upload for bootstrap
$(".custom-file-input").on("change", function() {
    var fileName = $(this).val().split("\\").pop();
    $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});



function handleDeleteDentalProcedure() {
    const dentalProcedureId = document.querySelector(".delete-modal").getAttribute("data-delete-id");

    if(!dentalProcedureId !== null) {
        $.ajax({
            type: "DELETE",
            url: "/api/dental-procedures/" + dentalProcedureId,
            contentType: "application/json",
            success: function() {
                $(`div#${dentalProcedureId}`).remove();
                toastr.success("Successfully delete dental procedure", "Success");
                closeDeleteModal();
            },
            error: function(e) {
                console.log(e);
                toastr.error("Error deleting dental procedure", "Error");
            }
        });
    }
}