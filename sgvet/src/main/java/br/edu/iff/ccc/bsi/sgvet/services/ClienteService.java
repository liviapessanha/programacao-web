package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRep;
	
	public List<Cliente> getAll() {
		return clienteRep.findAll();
	}
	
	public Cliente getById(Long id) {
		Optional<Cliente> cliente = clienteRep.findById(id);
		return cliente.orElse(null);
	}
	
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRep.save(cliente);
	}
	
	@Transactional
	public void delete(Long id) {
		clienteRep.deleteById(id);
	}
}
