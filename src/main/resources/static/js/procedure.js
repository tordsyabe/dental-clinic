
// Data for procedure's description

const procedureItems = [
    {
      surgery: [
        { id: 1, desc: "odontectomy", cost: "5000" },
      ],
    },

    {
      extraction: [
        { id: 1, desc: "adult exo", cost: "650" },
        { id: 2, desc: "pedo exo", cost: "650" },
      ],
    },

    {
      hygiene: [
        { id: 1, desc: "oral prophylaxis", cost: "1000" }
      ],
    },
    {
      prosthetics: [
        { id: 1, desc: "PJC", cost: "1000" },
        { id: 2, desc: "fixed bridge", cost: "1000" },
        { id: 3, desc: "veneers", cost: "1000" },
        { id: 4, desc: "partial denture", cost: "1000" },
        { id: 5, desc: "complete denture", cost: "1000" }
      ],
    },
        {
        restoration: [
            { id: 1, desc: "light cured filling", cost: "1000" },
            { id: 2, desc: "temporary filling", cost: "1000" },
            { id: 3, desc: "whitening", cost: "1000" }
            ],
        },
        {
        orthodontics: [
            { id: 1, desc: "ortho appliance", cost: "1000" },
            { id: 2, desc: "braces", cost: "15000" }
        ],
        },
        {
        endodontics: [
            { id: 1, desc: "mono rooted (RCT)", cost: "1000" },
            { id: 2, desc: "bi-rooted", cost: "1000" },
            { id: 3, desc: "tri-rooted", cost: "1000" }
        ],
        },
        {
                diagnosis: [
                    { id: 1, desc: "consultation", cost: "250" },
                ],
                }
  ];

// Dynamic change of procedure's description base on category change
$(document).ready(function() {

    $(".categories").find(".category-toggle").first().attr("checked", true);
    $("#procedure-items").find("input[name='description']").first().attr("checked", true);
    $("#procedure-items").find("input[name='cost']").first().attr("checked", true);


    $(".categories").find("label").each(function(i, label){
        $(this).on("click", () => {

          const procedures = $("#procedure-items").find("#procedure-items-list");

          console.log(procedures);


          const processes = document.createElement("div");
          processes.className = "p-5";
          processes.setAttribute("id", "procedure-items-list");

          procedureItems[i][label.getAttribute("for")].forEach((process, i) => {
            processes.innerHTML += `
                <div class="d-flex justify-content-between">
                        <input required id=${process.id} name="description" type="radio" class="with-font" value="${process.desc}" th:field="*{description}" >
                        <label aria-label=${process.cost + "-" + process.id} class="procedureDesc" for=${process.id}>${process.desc}</label>

                    <p class="text-muted">${process.cost}</p>
                    <input required class="with-font" id=${process.cost + "-" + process.id} name="cost" type="radio" value="${process.cost}" th:field="*{cost}" >
                </div>`;
          });

          $(procedures).replaceWith(processes);
          $("#procedure-items").find("input[name='description']").first().attr("checked", true);
          $("#procedure-items").find("input[name='cost']").first().attr("checked", true);

          setCostAndDesc()
        });
    });

});

//FILL procedure list for 1st category
(function(){

    const category = $("input[name='category']").attr("id");
    console.log(category);

    const items = procedureItems[0][category];
    console.log(procedureItems[0][category]);

    items.forEach(item => {
            $("#procedure-items-list").append(
                `<div class="d-flex justify-content-between">
                        <input required id=${item.id} name="description" type="radio" class="with-font" value="${item.desc}" th:field="*{description}" >
                        <label aria-label=${item.cost + "-" + item.id} class="procedureDesc" for=${item.id}>${item.desc}</label>

                    <p class="text-muted">${item.cost}</p>
                    <input required class="with-font" id=${item.cost + "-" + item.id} name="cost" type="radio" value="${item.cost}" th:field="*{cost}" >
                </div>`
            );
    });


})();




// Selecting cost and description
    function setCostAndDesc() {

    const procedureLabels = document.querySelectorAll('.procedureDesc');
    procedureLabels.forEach((procedureLabel, i) => {
        procedureLabel.addEventListener('click', () => {
            const dataName = procedureLabel.getAttribute("aria-label");
            const costRadioButton = document.querySelector(`input[id="${dataName}"]`);
            costRadioButton.checked = true;
        });
    });
}

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

function getToothProcedures(toothNumber) {

    const patientId = window.location.pathname.split("/").pop();

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
                <span class="text-muted text-capitalize">${data[0].toothDto.description}</span>
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



function openSideProcedures(){

    $(".side-procedures").animate({
        "right": "0",

    });

    $(".side-overlay").show();
    $("#context-menu").removeClass("show").hide();
}

function closeSideProcedures(){

    $(".side-procedures").animate({
        "right": "-450px",
    }, function() {
        $(".side-procedures .side-content").find(".mt-3").remove();
        $(".side-procedures .side-content").find("h5 + span").remove();
    });




    $(".side-overlay").hide();
    $("#context-menu").removeClass("show").hide();

}

