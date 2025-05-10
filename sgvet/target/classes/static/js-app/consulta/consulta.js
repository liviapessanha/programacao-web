document.querySelectorAll(".edit-btn").forEach(button => {
    button.addEventListener("click", function () {
        const id = this.getAttribute("data-id");
		
        window.location.href = `/consultas/editar?id=${id}`;
    });
});