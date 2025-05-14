package br.edu.iff.ccc.bsi.sgvet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	List<Animal> findByEspecie(String especie);
	
	List<Animal> findByRaca(String raca);
	
	List<Animal> findByClienteId(Long clienteId);
	
	List<Animal> findByNomeContainingIgnoreCase(String nome);
}

