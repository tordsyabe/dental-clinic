
// Data for procedure's description

const procedureItems = [
    {
      surgery: [
        { id: 1, desc: "Permanent tooth extraction", cost: "450" },
        { id: 2, desc: "Wisdom tooth extraction", cost: "5000" },
        { id: 3, desc: "Surgical tooth extraction", cost: "2500" },
      ],
    },

    {
      rootCanal: [
        { id: 1, desc: "Amazing root canal", cost: "8000" },
        { id: 2, desc: "Simple root canal", cost: "3000" },
      ],
    },

    {
      hygiene: [
        { id: 1, desc: "Cleaning", cost: "1000" },
        { id: 2, desc: "Plaque removal", cost: "1500" },
      ],
    },
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
            costRadioButton.checked = "true";
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

    console.log(numbers);

    numbers.forEach(number => {

        newRoot[number -1];
        roots[number -1].style.display = "none";
        teeth[number -1].style.display = "none";

    });

    teeth.forEach((tooth, i) => {

        tooth.addEventListener('click', () => {
            const radioButton = document.querySelector(`input[id=${tooth.id}]`);
            radioButton.checked = "true";
        });
    });

    console.log("FROM TOOTH SELECTION");

})();

// Setting toot width dynamically

(function(){
    const upperTeethMapDiv = document.querySelector('#upperTeethMap');

    const teethMaps = document.querySelectorAll(".teeth");

    for(i=0; i < teethMaps.length; i++) {
        teethMaps[i].setAttribute("width", upperTeethMapDiv.offsetWidth);
    }
})();
