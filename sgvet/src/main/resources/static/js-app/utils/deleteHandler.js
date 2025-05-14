function deleteButtons(selector, apiBaseUrl, itemName = "item") {
	const deleteButtons = document.querySelectorAll(selector);
	
	deleteButtons.forEach(button => {
		button.addEventListener("click", function () {
			const itemId = this.getAttribute("data-id");
				
			if(confirm(`Tem certeza que deseja excluir este ${itemName}?`)) {
				fetch(`${apiBaseUrl}/${itemId}`, {
					method: "DELETE"
				})
				.then(response => {
					if(response.ok) {
						location.reload();
					} else {
						alert(`Erro ao deletar ${itemName}.`);
					}
				})
				.catch(error => {
					console.error("Erro na requisição DELETE: ", error);
					alert("Erro inesperado.");
				});
			}
		});
	});
}