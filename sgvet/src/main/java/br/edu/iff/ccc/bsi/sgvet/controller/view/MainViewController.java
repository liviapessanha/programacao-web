package br.edu.iff.ccc.bsi.sgvet.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainViewController {
	@GetMapping("/home")
	public String getHome() {
		return "home";
	}
}
