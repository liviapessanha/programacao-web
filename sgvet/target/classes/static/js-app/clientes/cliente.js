document.addEventListener("DOMContentLoaded", function () {
	deleteButtons(".delete-btn", "/api/v1/clientes", "cliente");
	
	const form = document.getElementById("formClienteModal");
	const modalTitle = document.querySelector(".modal-title");
	const modalSalvarBtn = document.getElementById("modalSalvarBtn");
	const editButtons = document.querySelectorAll(".edit-btn");
	
	const novoButton = document.querySelector('button[data-bs-target="#novoClienteModal"]');
	
	novoButton.addEventListener("click", function () {
		modalTitle.textContent = "Novo Cliente";
		modalSalvarBtn.textContent = "Salvar";
		
		form.reset();
		
		form.querySelector('input[name="id"]').value = "";
	})
	
	editButtons.forEach(button => {
			button.addEventListener("click", function () {
				modalTitle.textContent = "Editar Cliente";
				modalSalvarBtn.textContent = "Atualizar";
				
				const id = this.getAttribute("data-id");
				const nome = this.getAttribute("data-nome");
				const email = this.getAttribute("data-email");
				const senha = this.getAttribute("data-senha");
				const telefone = this.getAttribute("data-telefone");
				const cpf = this.getAttribute("data-cpf");
				const endereco = this.getAttribute("data-endereco");
				
				//preenchendo os campos no modal
				form.querySelector('input[name="nome"]').value = nome;
				form.querySelector('input[name="email"]').value = email;
				form.querySelector('input[name="senha"]').value = senha;
				form.querySelector('input[name="telefone"]').value = telefone;
				form.querySelector('input[name="cpf"]').value = cpf;
				form.querySelector('input[name="endereco"]').value = endereco;
				form.querySelector('input[name="id"]').value = id;
				
				
				new bootstrap.Modal(document.getElementById('novoClienteModal')).show();
		});
	});
});
