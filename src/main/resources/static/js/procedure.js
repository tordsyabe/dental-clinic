
// Data for procedure's description

var procedureItems = [];


// Setting toot width dynamically

(function(){
    const upperTeethMapDiv = document.querySelector('#upperTeethMap');

    const teethMaps = document.querySelectorAll(".teeth");

    for(i=0; i < teethMaps.length; i++) {
        teethMaps[i].setAttribute("width", upperTeethMapDiv.offsetWidth);
    }
})();


//TAG TOOTH THAT ARE MISSING ONLOAD
(function(){

    const missingTeeth = document.querySelectorAll("#missing li");
    missingTeeth.forEach(missing => {
        const teeth = document.querySelectorAll("svg g g");
        $(`*[data-tooth-number=${missing.textContent}]`).siblings().each((t, tooth) => {
            $(tooth).css({"stroke":"#303030"});
        });
        $(`*[data-tooth-number=${missing.textContent}]`).attr("data-missing-id", missing.id);
        $(`*[data-tooth-number=${missing.textContent}]`).addClass("missing");
        $(`*[data-tooth-number=${missing.textContent}]`).siblings("text").css("stroke", "none");
        $(`*[data-tooth-number=${missing.textContent}]`).siblings("ellipse").css({"fill":"var(--gray-dark)", "stroke": "none"});
        $(`*[data-tooth-number=${missing.textContent}]`).siblings("#with__procedure").children().css({"fill":"#303030"});

    });
})();

//TAG TOOTH WITH PROCEDURES ON LOAD
(function(){

    const toothWithProcedure = document.querySelectorAll("#procedures li");
    toothWithProcedure.forEach(procedure => {
        const teeth = document.querySelectorAll("svg g g");

        $(`*[data-tooth-number=${procedure.id}]`).addClass("with-procedure");
        $(`*[data-tooth-number=${procedure.id}]`).siblings(".root__base").css({"stroke":"none"});
        $(`*[data-tooth-number=${procedure.id}]`).siblings("#with__procedure").css({"display":"block"});
        $(`*[data-tooth-number=${procedure.id}]`).siblings("ellipse").css({"fill":"var(--success)", "stroke": "none"});

    });
})();

//TAG EXTRACTED TOOTH
(function(){
       const extractedProcedure = document.querySelectorAll("#extracted li");
       extractedProcedure.forEach(extracted => {
            const teeth = document.querySelectorAll("svg g g");

            $(`*[data-tooth-number=${extracted.id}]`).addClass("extracted");
            $(`*[data-tooth-number=${extracted.id}]`).removeClass("with-procedure");
            $(`*[data-tooth-number=${extracted.id}]`).siblings(".root__base").css({"stroke":"none"});
            $(`*[data-tooth-number=${extracted.id}]`).siblings(".crown__line").css({"stroke":"#303030"});
            $(`*[data-tooth-number=${extracted.id}]`).siblings(".crown__base").css({"stroke":"#303030"});
            $(`*[data-tooth-number=${extracted.id}]`).siblings("#with__procedure").css({"display":"block"});
            $(`*[data-tooth-number=${extracted.id}]`).siblings("#with__procedure").children().css({"fill":"#303030"});
            $(`*[data-tooth-number=${extracted.id}]`).siblings("ellipse").css({"fill":"var(--success)", "stroke": "none"});
       });
})();

//toggle missing tooth button
$(".tooth__base").each(function(){
    $(this).on("click", function(e){
          const top = e.pageY -330;
          const left = e.pageX - 100;
          const toothId = this.getAttribute("data-tooth-number");

          const missingToothId = this.getAttribute("data-missing-id");

          if($(this).hasClass("missing")) {
              $("#context-menu").empty().append(`
                    <button class="dropdown-item" type="button" onclick="unTagMissing('${missingToothId}','${toothId}')">Untag missing</button>
              `).css({
                display: "block",
                position: "absolute",
                top: top,
                left: left
              }).addClass("show");
          } else if($(this).hasClass("with-procedure")){
               $("#context-menu").empty().append(`
                       <button class="dropdown-item" type="button" onclick="selectForProcedure(${toothId})">Select for procedure</button>
                       <button class="dropdown-item" type="button" onclick="getToothProcedures(${toothId})">See list of procedures</a>

                 `).css({
                   display: "block",
                   position: "absolute",
                   top: top,
                   left: left
                 }).addClass("show");
          } else if($(this).hasClass("extracted")) {
                $("#context-menu").empty().append(`
                       <button class="dropdown-item" type="button" onclick="getToothProcedures(${toothId})">See list of procedures</a>
                 `).css({
                   display: "block",
                   position: "absolute",
                   top: top,
                   left: left
                 }).addClass("show");

          } else {
              $("#context-menu").empty().append(`
                    <button class="dropdown-item" type="button" onclick="selectForProcedure(${toothId})">Select for procedure</button>
                    <button class="dropdown-item" type="button" onclick="tagAsMissing(${toothId})">Tag as missing</button>
              `).css({
                display: "block",
                position: "absolute",
                top: top,
                left: left
              }).addClass("show");
          }


    });

});

function selectForProcedure(toothId) {
    hideDropdown();
    console.log(toothId);
//    $(`*[data-tooth-number=${toothId}]`).css("fill", "rgba(225, 225, 225, 0.2)");
    const radioButton = document.querySelector(`input[id=tooth${toothId}]`);
    radioButton.checked = "true";
}

function hideDropdown() {
     $("#context-menu").removeClass("show").hide();
}

function tagAsMissing(toothId) {

        const patientId = window.location.pathname.split("/").pop();

        $.ajax({
           type: "POST",
           url: "/api/missing-tooth",
           contentType: "application/json",
           data: JSON.stringify({
                toothId: toothId,
                patientId: patientId
           }),
           success: function(data) {
               hideDropdown();
               const radioButton = document.querySelector(`input[id=tooth${toothId}]`);

                const teeth = document.querySelectorAll("svg g g");


               if($(`*[data-tooth-number=${toothId}]`).hasClass("with-procedure")) {
                     $(`*[data-tooth-number=${toothId}]`).siblings().each((t, tooth) => {
                         $(tooth).css({"stroke":"#303030"});
                     });
                     $(`*[data-tooth-number=${toothId}]`).attr("data-missing-id", data.uuid);
                     $(`*[data-tooth-number=${toothId}]`).siblings(".root__base").css("display","none");
                     $(`*[data-tooth-number=${toothId}]`).addClass("missing");
                     $(`*[data-tooth-number=${toothId}]`).siblings("text").css("stroke", "none");
                     $(`*[data-tooth-number=${toothId}]`).siblings("#with__procedure").css("display", "block");
                     $(`*[data-tooth-number=${toothId}]`).siblings("#with__procedure").children().css({"fill":"#303030"});
                     $(`*[data-tooth-number=${toothId}]`).siblings("ellipse").css("fill", "var(--gray-dark)");
                     radioButton.checked = false;
               } else {
                    $(`*[data-tooth-number=${toothId}]`).siblings().each((t, tooth) => {
                      $(tooth).css({"stroke":"#303030"});
                  });
                  $(`*[data-tooth-number=${toothId}]`).attr("data-missing-id", data.uuid);
                  $(`*[data-tooth-number=${toothId}]`).addClass("missing");
                  $(`*[data-tooth-number=${toothId}]`).siblings("text").css("stroke", "none");
                  $(`*[data-tooth-number=${toothId}]`).siblings("#with__procedure").css("display", "none");
                  $(`*[data-tooth-number=${toothId}]`).siblings("ellipse").css("fill", "var(--gray-dark)");
                  radioButton.checked = false;
               }


           },
           error: function(e){
                console.log(e);
           }
        });
}

function unTagMissing(missingToothId, toothId) {

    console.log(missingToothId)

    $.ajax({
        type: "DELETE",
        url: "/api/missing-tooth/" + missingToothId,
        contentType: "application/json",
        success: function() {
        hideDropdown();
            const teeth = document.querySelectorAll("svg g g");
            const radioButton = document.querySelector(`input[id=tooth${toothId}]`);

            if($(`*[data-tooth-number=${toothId}]`).hasClass("with-procedure")) {
                $(`*[data-tooth-number=${toothId}]`).removeClass("missing");
                $(`*[data-tooth-number=${toothId}]`).siblings("#with__procedure").css("display", "block");
                $(`*[data-tooth-number=${toothId}]`).siblings(".crown__base").css("stroke", "#fff");
                $(`*[data-tooth-number=${toothId}]`).siblings(".crown__line").css("stroke", "#fff");
                $(`*[data-tooth-number=${toothId}]`).siblings(".root__base").css("stroke", "none");
                $(`*[data-tooth-number=${toothId}]`).siblings("ellipse").css("fill", "var(--success)");
                $(`*[data-tooth-number=${toothId}]`).siblings("#with__procedure").children().css({"fill":"#999999"});
                radioButton.checked = false;
            } else {
                $(`*[data-tooth-number=${toothId}]`).removeClass("missing");
                $(`*[data-tooth-number=${toothId}]`).siblings("ellipse").css("fill", "var(--info)");
                $(`*[data-tooth-number=${toothId}]`).siblings("text").css("stroke", "none");
                $(`*[data-tooth-number=${toothId}]`).siblings(".root__base").css("stroke", "gray");
                $(`*[data-tooth-number=${toothId}]`).siblings(".crown__base").css("stroke", "#fff");
                $(`*[data-tooth-number=${toothId}]`).siblings(".crown__line").css("stroke", "#fff");
                radioButton.checked = false;
            }
        },
        error: function(e) {
            console.log(e);
        }
    });



}
//GETTING PER TOOTH PROCEDURES AJAX
function getToothProcedures(toothNumber) {

    const patientId = window.location.pathname.split("/").pop();

    console.log(toothNumber);

    $.ajax({
        type: "POST",
        url: "/api/procedures/tooth",
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify({
            patientId: patientId,
            toothId: toothNumber
        }),
        success: function(data) {

            $(".side-procedures .side-content").find("h5").after(`
                <span id="tooth-desc" class="text-muted text-capitalize">${data[0].toothDto.description}</span>
                <span id="tooth-number" class="badge badge-pill badge-info">${data[0].toothDto.number}</span>
            `)


            data.forEach(procedure => {

                $(".side-procedures .side-content").append(`

                    <div class="mt-3">


                        <div class="card">
                            <div class="card-body">

                                        <div class="d-flex justify-content-between align-items-center">
                                            <span class="text-capitalize">${procedure.description}</span>
                                            <span class="small text-muted pl-2" id="procedure-date">${procedure.dateCreated}</span>

                                        </div>
                                        <div class="d-flex justify-content-between align-items-end">
                                            <div>
                                                <span style="color: var(--info);" class="text-capitalize">${procedure.category}</span><br />
                                            </div>

                                            <span class="small">Upcoming</span>
                                        </div>


                            </div>
                        </div>

                    </div>
                `);


            const procedureDates = document.querySelectorAll('.side-procedures .side-content #procedure-date');

            formatNamedDate(procedureDates);
            })
        },
        error: function(e) {
            console.log(e);
        },
    })


    openSideProcedures();

}


$(document).mouseup(function(e){

    const contextMenu = $("#context-menu");

//    const sideProcedures = $(".side-procedures");

    if(!contextMenu.is(e.target) && contextMenu.has(e.target).length === 0) {
        $("#context-menu").removeClass("show").hide();
    }

//    if(!sideProcedures.is(e.target) && sideProcedures.has(e.target).length === 0) {
//        closeSideProcedures();
//    }
});

$(document).keyup(function(e){

    const contextMenu = $("#context-menu");

    if(e.key === "Escape") {
        $("#context-menu").removeClass("show").hide();
    }
});

(function(){

    $.ajax({
        type: "GET",
        url: "/api/dental-procedure-categories",
        contentType: "application/json",
        dataType: "json",
        success: function(data) {
            procedureItems = data;

            data.forEach(dentalProcedure => {
                $(".categories").append(`
                    <input type="radio" id=${dentalProcedure.name} name="category" value=${dentalProcedure.name} class="category-toggle">
                        <label class="text-center" for=${dentalProcedure.name} onclick="handleChangeProcedureOptions('${dentalProcedure.name}')">
                            <div class="card text-center">
                                <div class="card-body d-flex justify-content-center align-items-center flex-column">

                                    <img src="/images/dental.svg" width="50px" />
                                    <span class="small text-capitalize mt-2">${dentalProcedure.name}</span>

                                </div>
                            </div>
                        </label>

                `);

            });

                const firstCategory = procedureItems[0].dentalProceduresDto;

                firstCategory.forEach(item => {
                        $("#procedure-items-list").append(
                            `<div class="d-flex justify-content-between">
                                    <input required id=${item.uuid} name="description" type="radio" class="with-font" value="${item.description}" th:field="*{description}" >
                                    <label onclick="selectCost('${item.uuid}', '${item.cost}')" class="procedureDesc text-capitalize" for=${item.uuid}>${item.description}</label>

                                <p class="text-muted">${item.cost}</p>
                                <input required class="with-font" id=${item.uuid} name="cost" type="radio" value="${item.cost}" th:field="*{cost}" >
                            </div>`
                        );
                });

                    $(".categories").find(".category-toggle").first().attr("checked", true);
                    $("#procedure-items").find("input[name='description']").first().attr("checked", true);
                    $("#procedure-items").find("input[name='cost']").first().attr("checked", true);

        },
        error: function(e) {
            console.log(e);
        }
    });
})();


// Dynamic change of procedure's description base on category change
function handleChangeProcedureOptions(category){

    $("#procedure-items").find("#procedure-items-list").children().remove();

    const procedure = procedureItems.find(procedure => procedure.name === category);


    procedure.dentalProceduresDto.forEach(procedure => {

        $("#procedure-items").find("#procedure-items-list").append(`
              <div class="d-flex justify-content-between">
                      <input required id=${procedure.uuid} name="description" type="radio" class="with-font" value="${procedure.description}">
                      <label class="procedureDesc text-capitalize" onclick="selectCost('${procedure.uuid}', '${procedure.cost}')" for=${procedure.uuid}>${procedure.description}</label>

                  <p class="text-muted">${procedure.cost}</p>
                  <input required id=${procedure.uuid + "-" + procedure.cost} class="with-font" name="cost" type="radio" value="${procedure.cost}">
              </div>
        `);

    });

    $(".categories").find(".category-toggle").first().attr("checked", true);
    $("#procedure-items-list").find("input[name='description']").first().attr("checked", true);
    $("#procedure-items-list").find("input[name='cost']").first().attr("checked", true);

}

// Selecting cost and description
function selectCost(costId, cost) {
    $("#procedure-items-list").find(`#${costId}-${cost}`).attr("checked", true);

}


