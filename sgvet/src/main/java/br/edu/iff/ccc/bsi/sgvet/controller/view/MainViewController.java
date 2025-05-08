package br.edu.iff.ccc.bsi.sgvet.controller.view;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;

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
	
}

	
	
	
	

