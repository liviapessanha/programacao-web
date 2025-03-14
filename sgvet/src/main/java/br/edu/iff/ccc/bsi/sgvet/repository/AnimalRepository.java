package br.edu.iff.ccc.bsi.sgvet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
	List<Animal> findAll();
	
	// optional evita nullPointerException
	Optional<Animal> findById(Long id);
	
	Animal save(Animal animal);
	
	void deleteById(Long id);
	
	List<Animal> findByEspecie(String especie);
	
	List<Animal> findByRaca(String raca);
	
	List<Animal> findByClienteId(Long clienteId);
}

