
// Data for procedure's description

const categoryLists = [
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

(function(){
const categories = document.querySelectorAll('.category-toggle');

   let categoryProcedures = document.querySelector("#procedure-list");

  categories.forEach((category, i) => {
    category.addEventListener("click", function () {


      categoryProcedures.innerHTML = "";
      const processDiv = document.createElement("div");

      categoryLists[i][category.id].forEach((process, i) => {
        processDiv.innerHTML += `
            <div class="d-flex justify-content-between">
                <div>
                    <input id=${process.id} name="description" type="radio" class="with-font" value="${process.desc}" th:field="*{description}" >
                    <label aria-label=${process.cost + "-" + process.id} class="procedureDesc" for=${process.id}>${process.desc}</label>

                </div>

                <p class="text-muted">${process.cost}</p>
                <input class="with-font" id=${process.cost + "-" + process.id} name="cost" type="radio" value="${process.cost}" th:field="*{cost}" >
            </div>`;
      });


      categoryProcedures.appendChild(processDiv);
      setCostAndDesc();


    });
  });
})();

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

    teeth.forEach((tooth, i) => {
        tooth.addEventListener('click', () => {
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
