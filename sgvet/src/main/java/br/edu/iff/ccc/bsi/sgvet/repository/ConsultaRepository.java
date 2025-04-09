package br.edu.iff.ccc.bsi.sgvet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	List<Consulta> findAll();
	
	Optional<Consulta> findById(Long id);
	
	Consulta save(Consulta consulta);
	
	void deleteById(Long id);
	
	List<Consulta> findByCliente_Id(Long clienteId);
	
	List<Consulta> findByAnimal_Id(Long animalId);
}
