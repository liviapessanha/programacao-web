package br.edu.iff.ccc.bsi.sgvet.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping(path = "/")
public class MainViewController {
	@Autowired
	private ClienteRepository clienteRep;
	
	@GetMapping
	@ResponseBody
	public String getHome() {
		return "Tela principal";
	}
	
	@GetMapping("clientes")
	public String getClientes(Model model) {
		Cliente  cliente = new Cliente();
		model.addAttribute("cliente", cliente);
		return "/clientes/clientes";
	}
	
	@PostMapping("/clientes")
	 public String saveTask(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) { 
		if (result.hasErrors()) { 
			model.addAttribute("cliente", cliente);
			return "/clientes/clientes"; // Retorna ao formulário se houver erros } 
		}
			clienteRep.save(cliente); 
			return "redirect:/clientes"; 
		}
	
	@GetMapping("funcionarios")
	@ResponseBody
	public String getFuncionarios() {
		return "Tela de funcionarios aqui";
	}
}

	
	
	
	

