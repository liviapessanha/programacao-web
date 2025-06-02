package br.edu.iff.ccc.bsi.sgvet.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.services.ClienteService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("clientes")
public class ClienteViewController {

	@Autowired
	private ClienteService clienteServ;
	
	@GetMapping
	public String getClientes(@RequestParam(required = false) String nome, Model model) {
		
		model.addAttribute("cliente", new Cliente());
		
		  if (nome != null && !nome.isEmpty()) { 
			  model.addAttribute("clientes", clienteServ.getClientesByNome(nome)); 
			  return "clientes/clientes";
		  }
		 
		model.addAttribute("clientes", clienteServ.getAll()); 
		return "clientes/clientes";
	}
	
	@PostMapping
	 public String saveCliente(
			 @Valid @ModelAttribute("cliente") Cliente cliente, 
			 BindingResult result, 
			 Model model
	) { 
		
		if (result.hasErrors()) { 
			System.out.println("Tem erro!");
			result.getAllErrors().forEach(System.out::println);
			model.addAttribute("clientes", clienteServ.getAll());
			return "/clientes/clientes";
		}
	
		clienteServ.save(cliente); 
		System.out.println("Salvo com sucesso!");
		
		return "redirect:/clientes"; 
	}
}
