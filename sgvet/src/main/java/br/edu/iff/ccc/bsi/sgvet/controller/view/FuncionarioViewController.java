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

import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.services.FuncionarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioViewController {

	@Autowired
	private FuncionarioService funcionarioServ;
	
	@GetMapping
	public String getFuncionarios(@RequestParam(required = false) String nome, Model model) {
		model.addAttribute("funcionario", new Funcionario());
		
		if (nome != null && !nome.isEmpty()) {
			model.addAttribute("funcionarios", funcionarioServ.getFuncionariosByNome(nome));
			return "funcionarios/funcionarios";
		}
		
		model.addAttribute("funcionarios", funcionarioServ.getAll());
		return "funcionarios/funcionarios";
	}
	
	@PostMapping
	public String saveFuncionario(
			@Valid @ModelAttribute("funcionario") Funcionario funcionario,
			BindingResult result,
			Model model
	) {
		
		if (result.hasErrors()) {
			System.out.println("Tem erro!");
			result.getAllErrors().forEach(System.out::println);
			model.addAttribute("funcionarios", funcionarioServ.getAll());
			return "funcionarios/funcionarios";
		}
		
		funcionarioServ.save(funcionario);
		System.out.println("Funcionario salvo com sucesso.");
		return "redirect:/funcionarios";
	}
}






















