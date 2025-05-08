document.addEventListener("DOMContentLoaded", function () {
	const deleteButtons = document.querySelectorAll(".delete-btn");
	
	deleteButtons.forEach(button => {
		button.addEventListener("click", function () {
			const clienteId = this.getAttribute("data-id");
			
			if(confirm("Tem certeza que deseja excluir este cliente?")) {
				fetch(`/api/v1/clientes/${clienteId}`, {
					method: "DELETE"
				})
				.then(response => {
					if(response.ok) {
						/*this.closest("tr").remove();*/
						location.reload();
					} else {
						alert("Erro ao deletar cliente.");
					}
				})
				.catch(error => {
					console.error("Erro na requisição DELETE: ", error);
					alert("Erro inesperado.");
				});
			}
		});
	});
});