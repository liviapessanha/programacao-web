package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.repository.ConsultaRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class ConsultaService {
	
	@Autowired
	private ConsultaRepository consultaRep;
	
	public List<Consulta> getAll() {
		return consultaRep.findAll();
	}
	
	public Consulta getById(Long id) {
		return consultaRep.findById(id)
			.orElseThrow(() -> new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Consulta com ID " + id + " nao encontrado."
			));
	}
	
	@Transactional
	public Consulta save(Consulta consulta) {
		return consultaRep.save(consulta);
	}
	
	public Consulta salvarOuAtualizar(Consulta consulta) {
		if (consulta.getId() != null) {
			Consulta existente = consultaRep.findById(consulta.getId())
				.orElseThrow(() -> new EntityNotFoundException("Consulta não encontrada"));
			
			existente.setFuncionario(consulta.getFuncionario());
			existente.setCliente(consulta.getCliente());

			return consultaRep.save(existente);
		} else {
			return consultaRep.save(consulta);
		}
	}
	
	@Transactional
	public void delete(Long id) {
		if(!consultaRep.existsById(id)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Consulta com ID " + id + " nao encontrado."
			);
		}
		consultaRep.deleteById(id);
	}
	
	public List<Consulta> getConsultasByClienteId(Long id) {
		List<Consulta> consultas = consultaRep.findByCliente_Id(id);
		if(consultas.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Nenhum cliente vinculado a essa consulta"
			);
		}
		return consultas;
	}
	
	public List<Consulta> getConsultasByAnimalId(Long id) {
		List<Consulta> consultas = consultaRep.findByAnimal_Id(id);
		if(consultas.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Nenhum animal vinculado a essa consulta."
			);
		}
		return consultas;
	}
}
