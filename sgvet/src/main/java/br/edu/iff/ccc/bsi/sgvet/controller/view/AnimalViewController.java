package br.edu.iff.ccc.bsi.sgvet.controller.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;
import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.repository.AnimalRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("animais")
public class AnimalViewController {

	@Autowired
	private AnimalRepository animalRep;
	
	@Autowired
	private ClienteRepository clienteRep;
	
	@GetMapping
	public String getAnimais(@RequestParam(required = false) String nome, Model model) {
		model.addAttribute("animal", new Animal());
		
		List<Cliente> clientes = clienteRep.findAll();
	    model.addAttribute("clientes", clientes);
		
		if (nome != null && !nome.isEmpty()) {
			model.addAttribute("animais", animalRep.findByNomeContainingIgnoreCase(nome));
			return "animal/animal";
		}
		
		model.addAttribute("animais", animalRep.findAll());
		return "animal/animal";
	}
	
	@PostMapping
	public String saveAnimal(
			@Valid @ModelAttribute("animal") Animal animal,
			BindingResult result,
			Model model
		) {
			
		if (result.hasErrors()) {
			System.out.println("Tem erro!");
			result.getAllErrors().forEach(System.out::println);
			model.addAttribute("animais", animalRep.findAll());
			return "animal/animal";
		}
		
		if (animal.getId() != null) {
			Animal animalExistente = animalRep.findById(animal.getId()).orElse(null);
			
			if (animalExistente != null) {
				animalExistente.setNome(animal.getNome());
				animalExistente.setEspecie(animal.getEspecie());
				animalExistente.setRaca(animal.getRaca());
				animalExistente.setIdade(animal.getIdade());
				animalExistente.setSexo(animal.getSexo());
				animalExistente.setPeso(animal.getPeso());
				animalExistente.setCor(animal.getCor());
				animalExistente.setObservacao(animal.getObservacao());
				animalExistente.setCliente(animal.getCliente());
				
				animalRep.save(animalExistente);
				System.out.println("Animal editado com sucesso!");
				return "redirect:/animais";
			}
			System.out.println("Animal com ID não encontrado.");
		}
		
		animalRep.save(animal);
		System.out.println("Animal salvo com sucesso.");
		return "redirect:/animais";
	}
}














