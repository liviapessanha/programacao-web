package br.edu.iff.ccc.bsi.sgvet.controller.apirest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.services.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/clientes")
@Tag(name = "ClienteRest", description = "API para gerenciamento de clientes. Inclui operações CRUD.")
public class ClienteController {
	
	@Autowired
	public ClienteService clienteServ;
	
	@Operation(
			summary = "Retorna todos os clientes.",
			description = "Endpoint para listar todos os clientes cadastrados no sistema."
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200"),
		@ApiResponse(
				responseCode = "500",
				description = "Erro interno do servidor."
				)
	})
	@GetMapping
	public List<Cliente> getAll() {
		return clienteServ.getAll();
	}
	
	@Operation(summary = "Busca um cliente por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Nenhum cliente encontrado."),
	})
	@GetMapping("/{id}")
	public Cliente getById(@PathVariable Long id) {
		return clienteServ.getById(id);
	}
	
	@Operation(summary = "Cria um novo cliente.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cliente criado com sucesso."),
		@ApiResponse(responseCode = "400", description = "Dados inválidos."),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente create(@RequestBody Cliente cliente) {
		cliente.setId(null);
		return clienteServ.save(cliente);
	}
	
	@Operation(summary = "Atualiza um cliente existente.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Cliente não atualizado."),
	})
	@PutMapping("/{id}")
	public Cliente update(
		@PathVariable Long id,
		@RequestBody Cliente cliente
	) {
		cliente.setId(id);
		return clienteServ.save(cliente);
	}
	
	@Operation(summary = "Exclui um cliente por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Cliente excluído com sucesso."),
		@ApiResponse(responseCode = "404", description = "Cliente não encontrado."),
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void delete(@PathVariable Long id) {
		clienteServ.delete(id);
	}
}
