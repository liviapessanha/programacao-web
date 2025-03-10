package br.edu.iff.ccc.bsi.sgvet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
}
