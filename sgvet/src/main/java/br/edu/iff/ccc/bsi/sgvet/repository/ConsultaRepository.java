package br.edu.iff.ccc.bsi.sgvet.repository;

import java.time.LocalDate;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
	
	List<Consulta> findByCliente_Id(Long clienteId);
	
	List<Consulta> findByAnimal_Id(Long animalId);
	
	List<Consulta> findByStatusContainingIgnoreCase(String status);
	
	List<Consulta> findByDiaBetween(LocalDate inicio, LocalDate fim);

	@Transactional
	@Modifying
	@Query("UPDATE Consulta c SET c.status = :status WHERE c.id = :id")
	void updateStatusById(Long id, String status);
	
}
