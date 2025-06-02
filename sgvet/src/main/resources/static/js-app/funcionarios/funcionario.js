document.addEventListener("DOMContentLoaded", function () {
	
	deleteButtons(".delete-btn", "/api/v1/funcionarios", "funcionário");
	
	const form = document.getElementById("formFuncionarioModal");
	const modalTitle = document.querySelector(".modal-title");
	const modalSalvarBtn = document.getElementById("modalSalvarBtn");
	const editButtons = document.querySelectorAll(".edit-btn");
	const novoButton = document.querySelector('button[data-bs-target="#novoFuncionarioModal"]');
	
	novoButton.addEventListener("click", function () {
		modalTitle.textContent = "Novo Funcionário";
		modalSalvarBtn.textContent = "Salvar";
		
		form.reset();
		
		form.querySelector('input[name="id"]').value = "";
	})
	
	editButtons.forEach(button => {
			button.addEventListener("click", function () {
				modalTitle.textContent = "Editar Funcionario";
				modalSalvarBtn.textContent = "Atualizar";
				
				const id = this.getAttribute("data-id");
				const nome = this.getAttribute("data-nome");
				const email = this.getAttribute("data-email");
				const senha = this.getAttribute("data-senha");
				const telefone = this.getAttribute("data-telefone");
				const cargo = this.getAttribute("data-cargo");
				const horario_trabalho = this.getAttribute("data-horario_trabalho");
				
				//preenchendo os campos no modal
				form.querySelector('input[name="nome"]').value = nome;
				form.querySelector('input[name="email"]').value = email;
				form.querySelector('input[name="senha"]').value = senha;
				form.querySelector('input[name="telefone"]').value = telefone;
				form.querySelector('input[name="cargo"]').value = cargo;
				form.querySelector('input[name="horario_trabalho"]').value = horario_trabalho;
				form.querySelector('input[name="id"]').value = id;
				
				
				new bootstrap.Modal(document.getElementById('novoFuncionarioModal')).show();
		});
	});
});
