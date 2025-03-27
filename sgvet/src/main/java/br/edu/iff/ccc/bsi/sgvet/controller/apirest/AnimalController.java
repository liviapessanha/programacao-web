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

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;
import br.edu.iff.ccc.bsi.sgvet.services.AnimalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/animais")
@Tag(name = "AnimalRest", description = "API para gerenciamento de animais, Inclui operações CRUD e filtros por espécie, raça e cliente.")
public class AnimalController {
	@Autowired
	public AnimalService animalServ;
	
	@Operation(
			summary = "Retorna todos os animais.",
			description = "Endpoint para listar todos os animais cadastrados no sistema."
			)
	@ApiResponses({
		@ApiResponse(responseCode = "200"),
		@ApiResponse(
				responseCode = "500",
				description = "Erro interno do servidor."
				)
	})
	@GetMapping
	public List<Animal> getAll() {
		return animalServ.getAll();
	}
	
	@Operation(summary = "Busca um animal por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animal encontrado"),
		@ApiResponse(responseCode = "404", description = "Animal não encontrado"),
	})
	@GetMapping("/{id}")
	public Animal getById(@PathVariable Long id) {
		return animalServ.getById(id);
	}
	
	@Operation(summary = "Cria um novo animal.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animal criado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Dados inválidos."),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Animal create(@RequestBody Animal animal) {
		animal.setId(null);
		return animalServ.save(animal);
	}
	
	@Operation(summary = "Atualiza um animal existente.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animal atualizado."),
		@ApiResponse(responseCode = "404", description = "Animal não atualizado."),
	})
	@PutMapping("/{id}")
	public Animal update(
		@PathVariable Long id,
		@RequestBody Animal animal
	) {
		animal.setId(id);
		return animalServ.save(animal);
	}
	
	@Operation(summary = "Exclui um animal por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animal excluído com sucesso."),
		@ApiResponse(responseCode = "404", description = "Animal não encontrado."),
	})
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT) // Retorna 204, com sucesso mas sem corpo/retorno
	public void delete(@PathVariable Long id) {
		animalServ.delete(id);
	}
	
	@Operation(summary = "Busca animais por espécie.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animais da espécie encontrados."),
		@ApiResponse(responseCode = "404", description = "Nenhum animal dessa espécie encontrado."),
	})
	@GetMapping("/especie/{especie}")
	public List<Animal> getByEspecie(@PathVariable String especie) {
		return animalServ.getByEspecie(especie);
	}
	
	@Operation(summary = "Busca animais por raça.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animais da raça encontrados."),
		@ApiResponse(responseCode = "404", description = "Nenhum animal dessa raça encontrado."),
	})
	@GetMapping("/raca/{raca}")
	public List<Animal> getByRaca(@PathVariable String raca) {
		return animalServ.getByRaca(raca);
	}
	
	@Operation(summary = "Busca animais por ID do cliente.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Animais do cliente encontrados."),
		@ApiResponse(responseCode = "404", description = "Nenhum animal vinculado a esse cliente."),
	})
	@GetMapping("/cliente/{clienteId}")
	public List<Animal> getByEspecie(@PathVariable Long clienteId) {
		return animalServ.getAnimalByClienteId(clienteId);
	}
	
	
}
