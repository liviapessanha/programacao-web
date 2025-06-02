package br.edu.iff.ccc.bsi.sgvet.controller.view;



import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.sgvet.repository.ConsultaRepository;

@Controller
@RequestMapping(path = "/")
public class MainViewController {
	@Autowired
	private ConsultaRepository consultaRep;
	
	@Autowired
	private ClienteRepository clienteRep;
	
	@GetMapping
	public String getHome(Model model) {
		model.addAttribute("consulta", new Consulta());
		
		LocalDate today = LocalDate.now();
		LocalDate inicioSemana = today.with(DayOfWeek.MONDAY);
		LocalDate fimSemana = today.with(DayOfWeek.SUNDAY);
		
		model.addAttribute("consultas", consultaRep.findAll());
		
		List<Consulta> consultasSemana = consultaRep.findByDiaBetween(inicioSemana, fimSemana);
		model.addAttribute("consultasDaSemana", consultasSemana);
		
		List<Cliente> clientes = clienteRep.findAll();
		
		model.addAttribute("totalClientes", clientes);
		
		model.addAttribute("totalCancelados", consultaRep.findByStatusContainingIgnoreCase("Cancelado"));
		return "index";
	}
}

	
	
	
	

