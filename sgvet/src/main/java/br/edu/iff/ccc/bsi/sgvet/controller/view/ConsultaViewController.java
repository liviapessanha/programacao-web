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

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;
import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.repository.AnimalRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.ConsultaRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.FuncionarioRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("consultas")
public class ConsultaViewController {

	@Autowired
	private ConsultaRepository consultaRep;
	
	@Autowired
	private FuncionarioRepository funcionarioRep;
	
	@Autowired
	private ClienteRepository clienteRep;
	
	@Autowired
	private AnimalRepository animalRep;
	
	@GetMapping
	public String getConsultas(Model model) {
		model.addAttribute("consulta", new Consulta());
		
		List<Funcionario> funcionarios = funcionarioRep.findAll();
		model.addAttribute("funcionarios", funcionarios);
		
		List<Cliente> clientes = clienteRep.findAll();
		model.addAttribute("clientes", clientes);
		
		List<Animal> animais = animalRep.findAll();
		model.addAttribute("animais", animais);
		
		model.addAttribute("consultas", consultaRep.findAll());
		return "consulta/consulta";
	}
	
	@PostMapping
	public String saveConsulta(
			@Valid @ModelAttribute("consulta") Consulta consulta,
			BindingResult result,
			Model model
	) {
		if (result.hasErrors()) {
			System.out.println("Tem erro!");
			result.getAllErrors().forEach(System.out::println);
			model.addAttribute("consultas", consultaRep.findAll());
			return "consulta/consulta";
		}
		
		if (consulta.getId() != null) {
			Consulta consultaExistente = consultaRep.findById(consulta.getId()).orElse(null);
			
			if (consultaExistente != null) {
				consultaExistente.setFuncionario(consulta.getFuncionario());
				consultaExistente.setCliente(consulta.getCliente());
				consultaExistente.setAnimal(consulta.getAnimal());
				consultaExistente.setHora(consulta.getHora());
				consultaExistente.setDia(consulta.getDia());
				consultaExistente.setStatus(consulta.getStatus());
				consultaExistente.setMotivo_consulta(consulta.getMotivo_consulta());
				consultaExistente.setObservacoes(consulta.getObservacoes());
				
				consultaRep.save(consultaExistente);
				System.out.println("Consulta editada com sucesso!");
				return "redirect:/consultas";
			}
			System.out.println("Consulta com ID não encontrado.");
		}
		
		consultaRep.save(consulta);
		System.out.println("Consulta salvo com sucesso.");
		return "redirect:/consultas";
		
	}
}
