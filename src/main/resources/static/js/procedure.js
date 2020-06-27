
// Data for procedure's description

const procedureItems = [
    {
      surgery: [
        { id: 1, desc: "odontectomy", cost: "5000" }
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
            { id: 2, desc: "braces", cost: "15000" },
            { id: 3, desc: "veneers", cost: "1000" },
            { id: 4, desc: "partial denture", cost: "1000" },
            { id: 5, desc: "complete denture", cost: "1000" }
        ],
        },
        {
        endodontics: [
            { id: 1, desc: "mono rooted (RCT)", cost: "1000" },
            { id: 2, desc: "bi-rooted", cost: "1000" },
            { id: 3, desc: "tri-rooted", cost: "1000" }
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

// Tooth Selection toggle
(function(){
    const teeth = document.querySelectorAll('.tooth__base');
    const roots = document.querySelectorAll('.root__base');
    const crowns = document.querySelectorAll('.crown__base');
    const newRoot = document.querySelectorAll('.replace__root');
    const teethNumbers = [...document.querySelectorAll('.teeth_number')];

    const numbers = teethNumbers.map(toothNum => toothNum.textContent);

    numbers.forEach(number => {

        newRoot[number -1];
        roots[number -1].style.display = "none";
        teeth[number -1].style.display = "none";

    });

    teeth.forEach((tooth, i) => {

        tooth.addEventListener('click', () => {
        console.log(tooth.id);
            const radioButton = document.querySelector(`input[id=${tooth.id}]`);
            radioButton.checked = "true";
        });
    });


})();

// Setting toot width dynamically

(function(){
    const upperTeethMapDiv = document.querySelector('#upperTeethMap');

    const teethMaps = document.querySelectorAll(".teeth");

    for(i=0; i < teethMaps.length; i++) {
        teethMaps[i].setAttribute("width", upperTeethMapDiv.offsetWidth);
    }
})();

(function(){

    const missingTeeth = document.querySelectorAll("#missing li");
    missingTeeth.forEach(missing => {
        const teeth = document.querySelectorAll("svg g g");
        $('[data-test="the_exact_value"]')
        $(`*[data-tooth-number=${missing.id}]`).siblings().each((t, tooth) => {
            $(tooth).css({"stroke":"#303030"});
        });

        $(`*[data-tooth-number=${missing.id}]`).addClass("missing");
        $(`*[data-tooth-number=${missing.id}]`).siblings("text").css("stroke", "none");
        $(`*[data-tooth-number=${missing.id}]`).siblings("ellipse").css({"fill":"var(--danger)", "stroke": "none"});

    });
})();

//toggle missing tooth button
$(".tooth__base").each(function(){
    $(this).on("click", function(e){
          const top = e.pageY -330;
          const left = e.pageX - 100;
          const toothId = this.getAttribute("data-tooth-number");

          if($(this).hasClass("missing")) {
              $("#context-menu").empty().append(`
                    <button class="dropdown-item" type="button" onclick="unTagMissing(${toothId})">Untag missing</button>
              `).css({
                display: "block",
                position: "absolute",
                top: top,
                left: left
              }).addClass("show");
          } else {
              $("#context-menu").empty().append(`
                    <button class="dropdown-item" type="button" onclick="hideDropdown()">Select for procedure</button>
                    <button class="dropdown-item" type="button" onclick="hideDropdown()">See list of procedures</button>
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

function hideDropdown() {
     $("#context-menu").removeClass("show").hide();
}

function tagAsMissing(toothId) {

    console.log(toothId);
    $("#context-menu").removeClass("show").hide();
    const teeth = document.querySelectorAll("svg g g");
    console.log(teeth);
    $(`*[data-tooth-number=${toothId}]`).siblings().each((t, tooth) => {
        $(tooth).css({"stroke":"#303030"});
    });
    $(`*[data-tooth-number=${toothId}]`).addClass("missing");
    $(`*[data-tooth-number=${toothId}]`).siblings("text").css("stroke", "none");
    $(`*[data-tooth-number=${toothId}]`).siblings("ellipse").css("fill", "var(--danger)");
     const radioButton = document.querySelector(`input[id=tooth${toothId}]`);
                radioButton.checked = false;

}

function unTagMissing(toothId) {
    console.log(toothId);
    $("#context-menu").removeClass("show").hide();
    const teeth = document.querySelectorAll("svg g g");
        $(`*[data-tooth-number=${toothId}]`).removeClass("missing");
        $(`*[data-tooth-number=${toothId}]`).siblings("ellipse").css("fill", "var(--info)");
        $(`*[data-tooth-number=${toothId}]`).siblings("text").css("stroke", "none");
        $(`*[data-tooth-number=${toothId}]`).siblings(".root__base").css("stroke", "gray");
        $(`*[data-tooth-number=${toothId}]`).siblings(".crown__base").css("stroke", "#fff");
        $(`*[data-tooth-number=${toothId}]`).siblings(".crown__line").css("stroke", "#fff");
         const radioButton = document.querySelector(`input[id=tooth${toothId}]`);
                    radioButton.checked = false;

}