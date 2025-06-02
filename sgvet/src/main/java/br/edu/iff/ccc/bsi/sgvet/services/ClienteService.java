package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.enums.Papel;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRep;
	
	public List<Cliente> getAll() {
		return clienteRep.findAll();
	}
	
	public Cliente getById(Long id) {
		return clienteRep.findById(id)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.NOT_FOUND,
						"Cliente com ID " + id + " nao encontrado"
				));
	}
	
	@Transactional
	public Cliente save(Cliente cliente) {
		if(cliente.getPapel() == null) {
			cliente.setPapel(Papel.CLIENTE);
		}
		
		return clienteRep.save(cliente);
	}
	
	@Transactional
	public void delete(Long id) {
		if(!clienteRep.existsById(id)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Cliente com ID " + id + " nao encontrado."
			);
		}
		clienteRep.deleteById(id);
	}
	
	public List<Cliente> getClientesByNome(String nome) {
		List<Cliente> clientes = clienteRep.findByNomeContainingIgnoreCase(nome);
		if(clientes.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Nenhum cliente encontrado."
			);
		}
		return clientes;
	} 
}
