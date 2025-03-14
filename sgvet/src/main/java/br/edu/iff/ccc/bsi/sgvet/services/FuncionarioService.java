package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		Optional<Funcionario> funcionario = funcionarioRep.findById(id);
		return funcionario.orElse(null);
	}
	
	@Transactional
	public Funcionario save(Funcionario funcionario) {
		return funcionarioRep.save(funcionario);
	}
	
	@Transactional
	public void delete(Long id) {
		funcionarioRep.deleteById(id);
	}
}
