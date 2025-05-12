package br.edu.iff.ccc.bsi.sgvet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findAll();
	
	Optional<Cliente> findById(Long id);
	
	Cliente save(Cliente cliente);
	
	void deleteById(Long id);
	
	List<Cliente> findByNomeContainingIgnoreCase(String nome);
}
