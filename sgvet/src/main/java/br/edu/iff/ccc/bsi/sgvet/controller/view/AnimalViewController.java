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
import br.edu.iff.ccc.bsi.sgvet.services.AnimalService;
import br.edu.iff.ccc.bsi.sgvet.services.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("animais")
public class AnimalViewController {

	@Autowired
	private AnimalService animalServ;
	
	@Autowired
	private ClienteService clienteServ;
	
	@GetMapping
	public String getAnimais(@RequestParam(required = false) String nome, Model model) {
		model.addAttribute("animal", new Animal());
		
		List<Cliente> clientes = clienteServ.getAll();
	    model.addAttribute("clientes", clientes);
		
		if (nome != null && !nome.isEmpty()) {
			model.addAttribute("animais", animalServ.getAnimaisByNome(nome));
			return "animal/animal";
		}
		
		model.addAttribute("animais", animalServ.getAll());
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
			model.addAttribute("animais", animalServ.getAll());
			return "animal/animal";
		}
		
		animalServ.save(animal);
		System.out.println("Animal salvo com sucesso.");
		return "redirect:/animais";
	}
}














