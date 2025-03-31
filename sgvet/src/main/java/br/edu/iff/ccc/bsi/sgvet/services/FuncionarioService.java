package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRep;
	
	public List<Funcionario> getAll() {
		return funcionarioRep.findAll();
	}
	
	public Funcionario getById(Long id) {
		return funcionarioRep.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Funcionario com ID " + id + " nao encontrado"
			));
	}
	
	@Transactional
	public Funcionario save(Funcionario funcionario) {
		return funcionarioRep.save(funcionario);
	}
	
	@Transactional
	public void delete(Long id) {
		if(!funcionarioRep.existsById(id)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Funcionario com ID " + id + " nao encontrado."
			);
		}
		funcionarioRep.deleteById(id);
	}
}
