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
import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.repository.AnimalRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.ConsultaRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.FuncionarioRepository;
import br.edu.iff.ccc.bsi.sgvet.services.ConsultaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("consultas")
public class ConsultaViewController {

	@Autowired
	private ConsultaRepository consultaRep;
	
	@Autowired
	private ConsultaService consultaServ;
	
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
	
	@GetMapping("lista")
	public String getConsultaLista(Model model, @RequestParam(required = false) String status) {
		model.addAttribute("consulta", new Consulta());
		
		if (status != null && !status.isEmpty()) {
			model.addAttribute("consultas", consultaRep.findByStatusContainingIgnoreCase(status));
			return "consulta/consultaLista";
		}
		
		model.addAttribute("consultas", consultaRep.findAll());
		return "consulta/consultaLista";
	}
	
	@GetMapping("/editar")
	public String editarConsulta(@RequestParam Long id, Model model) {
	    Consulta consulta = consultaServ.getById(id);

	    if (consulta == null) {
	        return "redirect:/consultasLista";
	    }

	    model.addAttribute("consulta", consulta); 
	    model.addAttribute("clientes", clienteRep.findAll());
	    model.addAttribute("funcionarios", funcionarioRep.findAll());
	    model.addAttribute("animais", animalRep.findAll());
		 
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
		
		consultaServ.save(consulta);
		return "redirect:/consultas";
		
	}
}
