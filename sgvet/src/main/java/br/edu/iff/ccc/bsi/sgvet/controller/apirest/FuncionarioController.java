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

import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.services.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/funcionarios")
@Tag(name = "FuncionarioRest", description = "API para gerenciamento de funcionários. Inclui operações CRUD.")
public class FuncionarioController {

	@Autowired
	public FuncionarioService funcionarioServ;
	
	@Operation(
			summary = "Retorna todos os funcionarios.",
			description = "Endpoint para listar todos os funcionarios cadastrados no sistema."
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200"),
		@ApiResponse(
				responseCode = "500",
				description = "Erro interno do servidor."
				)
	})
	@GetMapping
	public List<Funcionario> getAll() {
		return funcionarioServ.getAll();
	}
	
	@Operation(summary = "Busca um funcionario por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionario encontrado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Nenhum funcionario encontrado."),
	})
	@GetMapping("/{id}")
	public Funcionario getById(@PathVariable Long id) {
		return funcionarioServ.getById(id);
	}
	
	@Operation(summary = "Cria um novo funcionário.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionário criado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Dados inválidos."),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Funcionario create(@RequestBody Funcionario funcionario) {
		funcionario.setId(null);
		return funcionarioServ.save(funcionario);
	}
	
	@Operation(summary = "Atualiza um funcionário existente.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Funcionario não atualizado."),
	})
	@PutMapping("/{id}")
	public Funcionario update(
		@PathVariable Long id,
		@RequestBody Funcionario funcionario
	) {
		funcionario.setId(id);
		return funcionarioServ.save(funcionario);
	}
	
	@Operation(summary = "Exclui um funcionario por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionário excluído com sucesso."),
		@ApiResponse(responseCode = "404", description = "Funcionário não encontrado."),
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) 
	public void delete(@PathVariable Long id) {
		funcionarioServ.delete(id);
	}
}
