package br.edu.iff.ccc.bsi.sgvet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	List<Funcionario> findAll();
	
	Optional<Funcionario> findById(Long id);
	
	Funcionario save(Funcionario funcionario);
	
	void deleteById(Long id);
	
	@Query("SELECT f FROM Funcionario f WHERE f.horario_trabalho = :horario_trabalho")
	List<Funcionario> findByHorarioTrabalhado(String horario_trabalho);
	
	List<Funcionario> findByCargo(String cargo);
	
}
