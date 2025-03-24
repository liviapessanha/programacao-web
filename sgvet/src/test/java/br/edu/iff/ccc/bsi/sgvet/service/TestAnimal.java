package br.edu.iff.ccc.bsi.sgvet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import br.edu.iff.ccc.bsi.sgvet.entities.Animal;
import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.enums.Sexo;
import br.edu.iff.ccc.bsi.sgvet.repository.AnimalRepository;
import br.edu.iff.ccc.bsi.sgvet.services.AnimalService;

@ExtendWith(MockitoExtension.class)
public class TestAnimal {

	@InjectMocks
	private AnimalService animalService;
	
	@Mock
	private AnimalRepository animalRep;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	@DisplayName("should find all animals with success")
	void testGetAll() {
		List<Animal> listaAnimais = new ArrayList<>();
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		Animal animal1 = new Animal(1L, "Rex", "Cachorro", "Labrador", 5, Sexo.MACHO, 25.5, 
                "Caramelo", "Animal saudável", cliente, null);

		Animal animal2 = new Animal(2L, "Felix", "Gato", "Siamês", 3, Sexo.MACHO, 4.2, 
                "Branco", "Alérgico a certos alimentos", cliente, null);

		listaAnimais.add(animal1);
		listaAnimais.add(animal2);
		
		when(animalRep.findAll()).thenReturn(listaAnimais);
		
		List<Animal> resultado = animalService.getAll();
		
		assertNotNull(resultado);
		assertEquals(2, resultado.size());
		assertEquals("Rex", resultado.get(0).getNome());
		assertEquals("Felix", resultado.get(1).getNome());
		verify(animalRep, times(1)).findAll();
	}
	
	@Test
	@DisplayName("should find an animal by id with success")
	void testGetById() {
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		
		Animal animalMock = new Animal(1L, "Rex", "Cachorro", "Labrador", 5, Sexo.MACHO, 25.5, 
                "Caramelo", "Animal saudável", cliente, null);

		when(animalRep.findById(1L)).thenReturn(Optional.of(animalMock));
		
		Animal resultado = animalService.getById(1L);
		
		assertNotNull(resultado);
		assertEquals(1L, resultado.getId());
		assertEquals("Rex", resultado.getNome());
		assertEquals("Cachorro", resultado.getEspecie());
		assertEquals("Labrador", resultado.getRaca());
		assertEquals(5, resultado.getIdade());
		assertEquals(Sexo.MACHO, resultado.getSexo());
		assertEquals(25.5, resultado.getPeso());
		assertEquals("Caramelo", resultado.getCor());
		assertEquals("Animal saudável", resultado.getObservacao());
		assertEquals(1L, resultado.getCliente().getId());
		verify(animalRep, times(1)).findById(1L);
	}
}
