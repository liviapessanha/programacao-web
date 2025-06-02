const racasPorEspecie = {
	    "Cachorro": ["Labrador", "Poodle", "Bulldog", "Beagle", "Pastor Alemão", "Golden Retriever"],
	    "Gato": ["Siamês", "Persa", "Maine Coon", "Sphynx", "Angorá"],
	    "Pássaro": ["Canário", "Periquito", "Papagaio", "Calopsita", "Arara"],
	    "Coelho": ["Lionhead", "Angorá", "Mini Rex", "Flemish Giant"],
	    "Hamster": ["Sírio", "Anão Russo", "Roborovski", "Chinês"],
	    "Tartaruga": ["Tigre d'água", "Jabutí", "Tartaruga-mordedora"],
	    "Peixe": ["Betta", "Guppy", "Neon", "Oscar", "Molinésia"],
	    "Outro": ["Outra Raça"]
	  };
	  
	  function filtrarRacas() {
	      const especieSelect = document.getElementById("floatingEspecie");
	      const racaSelect = document.getElementById("floatingRaca");
	      const racaContainer = document.getElementById("racaContainer");
	      const especie = especieSelect.value;

	      racaSelect.innerHTML = '<option value="" disabled selected>Selecione a raça</option>';

	      // Se espécie for selecionada, mostra o campo raça e preenche
	      if (especie && racasPorEspecie[especie]) {
	        document.getElementById('racaContainer').classList.remove('section-raca');
	        racasPorEspecie[especie].forEach(raca => {
	          const option = document.createElement("option");
	          option.value = raca;
	          option.text = raca;
	          racaSelect.appendChild(option);
	        });
	      } else {
	        document.getElementById('racaContainer').classList.add('section-raca');
	      }
	    }

		
document.addEventListener("DOMContentLoaded", function () {
	deleteButtons(".delete-btn", "/api/v1/animais", "animal");
		
	const form = document.getElementById("formAnimalModal");
	const modalTitle = document.querySelector(".modal-title");
	const modalSalvarBtn = document.getElementById("modalSalvarBtn");
	const editButtons = document.querySelectorAll(".edit-btn");
	
	const novoButton = document.querySelector('button[data-bs-target="#novoAnimalModal"]');
		
		novoButton.addEventListener("click", function () {
		modalTitle.textContent = "Novo Animal";
		modalSalvarBtn.textContent = "Salvar";
			
		form.reset();
			
		form.querySelector('input[name="id"]').value = "";
	});
	
	editButtons.forEach(button => {
				button.addEventListener("click", function () {
				modalTitle.textContent = "Editar Animal";
				modalSalvarBtn.textContent = "Atualizar";
					
				const id = this.getAttribute("data-id");
				const nome = this.getAttribute("data-nome");
				const especie = this.getAttribute("data-especie");
				const raca = this.getAttribute("data-raca");
				const idade = this.getAttribute("data-idade");
				const sexo = this.getAttribute("data-sexo");
				const peso = this.getAttribute("data-peso");
				const cor = this.getAttribute("data-cor");
				const observacao = this.getAttribute("data-observacao");
				const cliente = this.getAttribute("data-cliente");
					

				form.querySelector('input[name="id"]').value = id;
				
				form.querySelector('input[name="nome"]').value = nome;
				form.querySelector('input[name="idade"]').value = idade;
				form.querySelector('input[name="peso"]').value = peso;
				form.querySelector('input[name="cor"]').value = cor;
				form.querySelector('textarea[name="observacao"]').value = observacao;
				
				const especieSelect = form.querySelector('select[name="especie"]');
				especieSelect.value = especie;
				
				const changeEvent = new Event('change');
				especieSelect.dispatchEvent(changeEvent);
				
				setTimeout(() => {
				       const racaSelect = form.querySelector('select[name="raca"]');
				       racaSelect.value = raca;
				}, 200);
						
				form.querySelector('select[name="sexo"]').value = sexo;
				/*form.querySelector('select[name="raca"]').value = raca;*/
				form.querySelector('select[name="cliente"]').value = cliente;
				
				filtrarRacas();
					
				new bootstrap.Modal(document.getElementById('novoAnimalModal')).show();
		});
	});
});









