package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;
import br.edu.iff.ccc.bsi.sgvet.exception.AnimalNaoEncontradoException;
import br.edu.iff.ccc.bsi.sgvet.exception.EspecieNaoEncontradaException;
import br.edu.iff.ccc.bsi.sgvet.repository.AnimalRepository;

@Service
public class AnimalService {

	@Autowired
	private AnimalRepository animalRep;
	
	public List<Animal> getAll() {
		return animalRep.findAll();
	}
	
	public Animal getById(Long id) {
		Optional<Animal> animal = animalRep.findById(id);
		return animal.orElseThrow(() -> new AnimalNaoEncontradoException(id));
	}
	
	@Transactional
	public Animal save(Animal animal) {
		return animalRep.save(animal);
	}
	
	@Transactional
	public void delete(Long id) {
		if(!animalRep.existsById(id)) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"Animal com ID " + id + " nao encontrado."
			);
		}
		
		animalRep.deleteById(id);
	}
	
	public List<Animal> getByEspecie(String especie) {
		List<Animal> animais = animalRep.findByEspecie(especie);
		if(animais.isEmpty()) {
			throw new EspecieNaoEncontradaException(especie);
		}
		
		return animais;
		
	}
	
	public List<Animal> getByRaca(String raca) {
		List<Animal> animais = animalRep.findByRaca(raca);
		if(animais.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Animal com a " + raca + " nao encontrado."
		    );
		}
		return animais;
	}
	
	public List<Animal> getAnimalByClienteId(Long id) {
		List<Animal> animais = animalRep.findByClienteId(id);
		if(animais.isEmpty()) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Cliente com o " + id + " nao encontrado."
		    );
		}
		return animais;
	}
}
