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

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.services.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/consultas")
@Tag(name = "ConsultaRest", description = "API para gerenciamento de consultas, Inclui operações CRUD e filtros por cliente e animal.")
public class ConsultaController {

	@Autowired
	public ConsultaService consultaServ;
	
	@Operation(
			summary = "Retorna todas as consultas.",
			description = "Endpoint para listar todas as consultas cadastradas no sistema."
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200"),
		@ApiResponse(
				responseCode = "500",
				description = "Erro interno do servidor."
				)
	})
	@GetMapping
	public List<Consulta> getAll() {
		return consultaServ.getAll();
	}
	
	@Operation(summary = "Busca uma consulta por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Consulta encontrada com sucesso."),
		@ApiResponse(responseCode = "404", description = "Nenhuma consulta encontrada."),
	})
	@GetMapping("/{id}") 
	public Consulta getById(@PathVariable Long id) {
		return consultaServ.getById(id);
	}
	
	@Operation(summary = "Cria uma nova consulta.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Consulta criada com sucesso."),
		@ApiResponse(responseCode = "400", description = "Dados inválidos."),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Consulta create(@RequestBody Consulta consulta) {
		consulta.setId(null);
		return consultaServ.save(consulta);
	}
	
	@Operation(summary = "Atualiza uma nova consulta.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Consulta atualizada com sucesso."),
		@ApiResponse(responseCode = "404", description = "Dados inválidos."),
	})
	@PutMapping("/{id}")
	public Consulta update(@PathVariable Long id, @RequestBody Consulta consulta) {
		 consulta.setId(id); 
		return consultaServ.save(consulta);
	}
	
	@Operation(summary = "Exclui uma consulta por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Consulta excluída com sucesso."),
		@ApiResponse(responseCode = "404", description = "Consulta não encontrada."),
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		consultaServ.delete(id);
	}
	
	@Operation(summary = "Busca consultas por clienteId.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Consultas do cliente encontradas."),
		@ApiResponse(responseCode = "404", description = "Nenhum consulta do cliente encontrada."),
	})
	@GetMapping("/cliente/{clienteId}")
	public List<Consulta> getConsultasByClienteId(@PathVariable Long clienteId) {
		return consultaServ.getConsultasByClienteId(clienteId);
	}
	
	@Operation(summary = "Busca consultas por animalId.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Consultas do animal encontradas."),
		@ApiResponse(responseCode = "404", description = "Nenhum consulta do animal encontrada."),
	})
	@GetMapping("/animal/{animalId}")
	public List<Consulta> getConsultasByAnimalId(@PathVariable Long animalId) {
		return consultaServ.getConsultasByAnimalId(animalId);
	}
}
