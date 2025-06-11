package br.edu.iff.ccc.bsi.sgvet.controller.view;

import java.util.Collections;
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
import br.edu.iff.ccc.bsi.sgvet.services.AnimalService;
import br.edu.iff.ccc.bsi.sgvet.services.ClienteService;
import br.edu.iff.ccc.bsi.sgvet.services.ConsultaService;
import br.edu.iff.ccc.bsi.sgvet.services.FuncionarioService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("consultas")
public class ConsultaViewController {

	@Autowired
	private ConsultaService consultaServ;
	
	@Autowired
	private FuncionarioService funcionarioServ;
	
	@Autowired
	private ClienteService clienteServ;
	
	@Autowired
	private AnimalService animalServ;
	
	@GetMapping
	public String getConsultas(Model model) {
		model.addAttribute("consulta", new Consulta());
		
		List<Funcionario> funcionarios = funcionarioServ.getAll();
		model.addAttribute("funcionarios", funcionarios);
		
		List<Cliente> clientes = clienteServ.getAll();
		model.addAttribute("clientes", clientes);
		
		List<Animal> animais = animalServ.getAll();
		model.addAttribute("animais", animais);
		
		model.addAttribute("consultas", consultaServ.getAll());
		return "consulta/consulta";
	}
	
	@GetMapping("lista")
	public String getConsultaLista(Model model, @RequestParam(required = false) String status) {
		model.addAttribute("consulta", new Consulta());
		
		if (status != null && !status.isEmpty()) {
			List<Consulta> consultasFiltradas = consultaServ.getConsultasByStatus(status);
			Collections.reverse(consultasFiltradas);
			model.addAttribute("consultas", consultasFiltradas);
			return "consulta/consultaLista";
		}

		List<Consulta> consultas = consultaServ.getAll();
		Collections.reverse(consultas);
		model.addAttribute("consultas", consultas);
		return "consulta/consultaLista";
	}
	
	@GetMapping("/editar")
	public String editarConsulta(@RequestParam Long id, Model model) {
	    Consulta consulta = consultaServ.getById(id);

	    if (consulta == null) {
	        return "redirect:/consultasLista";
	    }

	    model.addAttribute("consulta", consulta); 
	    model.addAttribute("clientes", clienteServ.getAll());
	    model.addAttribute("funcionarios", funcionarioServ.getAll());
	    model.addAttribute("animais", animalServ.getAll());
		 
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
			model.addAttribute("consultas", consultaServ.getAll());
			return "consulta/consulta";
		}
		
		consultaServ.save(consulta);
		return "redirect:/consultas";
		
	}
}
