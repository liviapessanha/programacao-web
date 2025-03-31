package br.edu.iff.ccc.bsi.sgvet.controller.apirest;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// HATEOAS
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
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
	public CollectionModel<EntityModel<Funcionario>> getAll() {
		List<Funcionario> funcionarios = funcionarioServ.getAll();
		
		//HATEOAS
		//converte cada funcionario para EntityModel com links
		List<EntityModel<Funcionario>> funcionariosComLinks = funcionarios.stream()
				.map(funcionario -> {
				EntityModel<Funcionario> resource = EntityModel.of(funcionario);
				resource.add(linkTo(methodOn(FuncionarioController.class)
						.getById(funcionario.getId())).withSelfRel());
				return resource;
			})
			.collect(Collectors.toList());
		
		//cria colecao com links
		CollectionModel<EntityModel<Funcionario>> collection = CollectionModel.of(funcionariosComLinks);
		
		//add link para ele mesmo
		collection.add(linkTo(methodOn(FuncionarioController.class).getAll()).withSelfRel());
		
		return collection;
	}
	
	@Operation(summary = "Busca um funcionario por ID.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionario encontrado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Nenhum funcionario encontrado."),
	})
	@GetMapping("/{id}")
	public EntityModel<Funcionario> getById(@PathVariable Long id) {
		Funcionario funcionario = funcionarioServ.getById(id);
		
		//cria o EntityModel com  funcionario
		EntityModel<Funcionario> resource = EntityModel.of(funcionario);
		
		//add link para ele mesmo
		resource.add(linkTo(methodOn(FuncionarioController.class).getById(id)).withSelfRel());
		
		resource.add(linkTo(methodOn(FuncionarioController.class).getAll()).withRel("funcionarios"));
		
		return resource;
	}
	

	@Operation(summary = "Cria um novo funcionário.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionário criado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Dados inválidos."),
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Funcionario> create(@RequestBody Funcionario funcionario) {
		funcionario.setId(null);
		Funcionario funcionarioSalvo = funcionarioServ.save(funcionario);
		
		EntityModel<Funcionario> resource = EntityModel.of(funcionarioSalvo);
		
		// link para o novo recurso criado
		resource.add(linkTo(methodOn(FuncionarioController.class)
				.getById(funcionarioSalvo.getId())).withSelfRel());
		
		//link para a colecao
		resource.add(linkTo(methodOn(FuncionarioController.class).getAll()).withRel("funcionarios"));
		
		return resource;
	}
	
	@Operation(summary = "Atualiza um funcionário existente.")
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso."),
		@ApiResponse(responseCode = "404", description = "Funcionario não atualizado."),
	})
	@PutMapping("/{id}")
	public EntityModel<Funcionario> update(
		@PathVariable Long id,
		@RequestBody Funcionario funcionario
	) {
		funcionario.setId(id);
		Funcionario funcionarioAtualizado = funcionarioServ.save(funcionario);
		
		EntityModel<Funcionario> resource = EntityModel.of(funcionarioAtualizado);
		
		resource.add(linkTo(methodOn(FuncionarioController.class)
				.getById(id)).withSelfRel());
		
		//link para a colecao
		resource.add(linkTo(methodOn(FuncionarioController.class).getAll()).withRel("funcionarios"));
		
		return resource;
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
