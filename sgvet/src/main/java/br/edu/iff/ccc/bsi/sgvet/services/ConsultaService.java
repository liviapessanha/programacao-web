package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.repository.ConsultaRepository;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRep;
	
	public List<Consulta> getAll() {
		return consultaRep.findAll();
	}
	
	public Consulta getById(Long id) {
		Optional<Consulta> consulta = consultaRep.findById(id);
		return consulta.orElse(null);
	}
	
	@Transactional
	public Consulta save(Consulta consulta) {
		return consultaRep.save(consulta);
	}
	
	@Transactional
	public void delete(Long id) {
		consultaRep.deleteById(id);
	}
	
	public List<Consulta> getConsultasByClienteId(Long id) {
		return consultaRep.findClienteById(id);
	}
	
	public List<Consulta> getConsultasByAnimalId(Long id) {
		return consultaRep.findAnimalById(id);
	}
}
