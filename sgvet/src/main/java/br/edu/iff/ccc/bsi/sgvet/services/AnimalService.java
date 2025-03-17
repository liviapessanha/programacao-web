package br.edu.iff.ccc.bsi.sgvet.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;
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
		return animal.orElse(null);
	}
	
	//salva ou atualiza
	@Transactional
	public Animal save(Animal animal) {
		return animalRep.save(animal);
	}
	
	@Transactional
	public void delete(Long id) {
		animalRep.deleteById(id);
	}
	
	public List<Animal> getByEspecie(String especie) {
		return animalRep.findByEspecie(especie);
	}
	
	public List<Animal> getByRaca(String raca) {
		return animalRep.findByRaca(raca);
	}
	
	public List<Animal> getAnimalByClienteId(Long id) {
		return animalRep.findByClienteId(id);
	}
}
