const categories = document.querySelectorAll('.category-toggle');


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

       let categoryProcedures = document.querySelector("#procedure-list");

      categories.forEach((category, i) => {
        category.addEventListener("click", function () {
          categoryProcedures.innerHTML = "";
          const processDiv = document.createElement("div");

          categoryLists[i][category.id].forEach((proc, i) => {
            processDiv.innerHTML += `
                            <div class="d-flex justify-content-between">
                            <div>
                                <input id=${proc.id} name="process" type="radio" class="with-font" value="${proc.desc}"/>
                                <label for=${proc.id}>${proc.desc}</label>

                                </div>
                                <p class="text-muted">${proc.cost}</p>
                            </div>

                `;
          });

          categoryProcedures.appendChild(processDiv);
        });
      });

      function save() {
            console.log('saving');
      }


