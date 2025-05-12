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
import br.edu.iff.ccc.bsi.sgvet.enums.Papel;
import br.edu.iff.ccc.bsi.sgvet.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("funcionarios")
public class FuncionarioViewController {

	@Autowired
	private FuncionarioRepository funcionarioRep;
	
	@GetMapping
	public String getFuncionarios(@RequestParam(required = false) String nome, Model model) {
		model.addAttribute("funcionario", new Funcionario());
		
		if (nome != null && !nome.isEmpty()) {
			model.addAttribute("funcionarios", funcionarioRep.findByNomeContainingIgnoreCase(nome));
			return "funcionarios/funcionarios";
		}
		
		model.addAttribute("funcionarios", funcionarioRep.findAll());
		return "funcionarios/funcionarios";
	}
	
	@PostMapping
	public String saveFuncionario(
			@Valid @ModelAttribute("funcionario") Funcionario funcionario,
			BindingResult result,
			Model model
			) {
		
		if (funcionario.getPapel() != null) {
			funcionario.setPapel(Papel.FUNCIONARIO);
		}
		
		if (result.hasErrors()) {
			System.out.println("Tem erro!");
			result.getAllErrors().forEach(System.out::println);
			model.addAttribute("funcionarios", funcionarioRep.findAll());
			return "funcionarios/funcionarios";
		}
		
		if (funcionario.getId() != null) {
			Funcionario funcionarioExistente = funcionarioRep.findById(funcionario.getId()).orElse(null);
			
			if (funcionarioExistente != null) {
				funcionarioExistente.setNome(funcionario.getNome());
				funcionarioExistente.setEmail(funcionario.getEmail());
				funcionarioExistente.setTelefone(funcionario.getTelefone());
				funcionarioExistente.setSenha(funcionario.getSenha());
				funcionarioExistente.setCargo(funcionario.getCargo());
				funcionarioExistente.setHorario_trabalho(funcionario.getHorario_trabalho());
				
				funcionarioRep.save(funcionarioExistente);
				System.out.println("Funcionario editado com sucesso!");
				return "redirect:/funcionarios";
			}
			System.out.println("Funcionario com ID não encontrado.");
		}
		
		funcionarioRep.save(funcionario);
		System.out.println("Funcionario salvo com sucesso.");
		return "redirect:/funcionarios";
	}
}






















