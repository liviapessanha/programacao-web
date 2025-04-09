package br.edu.iff.ccc.bsi.sgvet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
import br.edu.iff.ccc.bsi.sgvet.entities.Consulta;
import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.repository.ConsultaRepository;
import br.edu.iff.ccc.bsi.sgvet.services.ConsultaService;

@ExtendWith(MockitoExtension.class)
public class TestConsulta {
    
    @InjectMocks
    private ConsultaService consultaService;
    
    @Mock
    private ConsultaRepository consultaRepository;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    @DisplayName("should find all consultas with success")
    void testGetAll() {
       
        List<Consulta> listaConsultas = new ArrayList<>();
        
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        
        Animal animal = new Animal();
        animal.setId(1L);
        
        Consulta consulta1 = new Consulta(1L, funcionario, cliente, animal, "14:00", "Agendada", 
                "Checkup anual", "Paciente sem sintomas aparentes", 150.0);
        consulta1.setAnimal(animal);
        
        Consulta consulta2 = new Consulta(2L, funcionario, cliente, animal, "15:30", "Confirmada", 
                "Vacinação", "Trazer carteira de vacinação", 80.0);
        consulta2.setAnimal(animal);
        
        listaConsultas.add(consulta1);
        listaConsultas.add(consulta2);
        
        when(consultaRepository.findAll()).thenReturn(listaConsultas);
        
        List<Consulta> resultado = consultaService.getAll();
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("14:00", resultado.get(0).getHora());
        assertEquals("15:30", resultado.get(1).getHora());
        assertEquals("Agendada", resultado.get(0).getStatus());
        assertEquals("Confirmada", resultado.get(1).getStatus());
        verify(consultaRepository, times(1)).findAll();
    }
    
    @Test
    @DisplayName("should find consulta by id with success")
    void testGetById_Success() {
      
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        
        Animal animal = new Animal();
        animal.setId(1L);
        
        Consulta consultaMock = new Consulta(1L, funcionario, cliente, animal, "14:00", "Agendada", 
                "Checkup anual", "Paciente sem sintomas aparentes", 150.0);
        consultaMock.setAnimal(animal);
        
        when(consultaRepository.findById(1L)).thenReturn(Optional.of(consultaMock));
      
        Consulta resultado = consultaService.getById(1L);
        
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("14:00", resultado.getHora());
        assertEquals("Agendada", resultado.getStatus());
        assertEquals("Checkup anual", resultado.getMotivo_consulta());
        assertEquals("Paciente sem sintomas aparentes", resultado.getObservacoes());
        assertEquals(150.0, resultado.getValor());
        assertEquals(1L, resultado.getCliente().getId());
        assertEquals(1L, resultado.getFuncionario().getId());
        assertEquals(1L, resultado.getAnimal().getId());
        verify(consultaRepository, times(1)).findById(1L);
    }
    
    @Test
    @DisplayName("should search query for non-existent ID returns null")
    void testGetById_NotFound() {
        when(consultaRepository.findById(99L)).thenReturn(Optional.empty());
        
        Consulta resultado = consultaService.getById(99L);
        
        assertNull(resultado);
        verify(consultaRepository, times(1)).findById(99L);
    }
    
    @Test
    @DisplayName("should save with success")
    void testSave() {
    
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        
        Animal animal = new Animal();
        animal.setId(1L);
        
        Consulta consultaParaSalvar = new Consulta(null, funcionario, cliente, animal, "14:00", "Agendada", 
                "Checkup anual", "Paciente sem sintomas aparentes", 150.0);
        consultaParaSalvar.setAnimal(animal);
        
        Consulta consultaSalva = new Consulta(1L, funcionario, cliente, animal, "14:00", "Agendada", 
                "Checkup anual", "Paciente sem sintomas aparentes", 150.0);
        consultaSalva.setAnimal(animal);
        
        when(consultaRepository.save(consultaParaSalvar)).thenReturn(consultaSalva);
        
        Consulta resultado = consultaService.save(consultaParaSalvar);
        
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("14:00", resultado.getHora());
        assertEquals("Agendada", resultado.getStatus());
        assertEquals("Checkup anual", resultado.getMotivo_consulta());
        assertEquals("Paciente sem sintomas aparentes", resultado.getObservacoes());
        assertEquals(150.0, resultado.getValor());
        assertEquals(1L, resultado.getCliente().getId());
        assertEquals(1L, resultado.getFuncionario().getId());
        assertEquals(1L, resultado.getAnimal().getId());
        verify(consultaRepository, times(1)).save(consultaParaSalvar);
    }
    
    @Test
    @DisplayName("should find by client id with success")
    void testGetConsultasByClienteId() {
        
        List<Consulta> listaConsultas = new ArrayList<>();
        
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        
        Funcionario funcionario = new Funcionario();
        funcionario.setId(1L);
        
        Animal animal = new Animal();
        animal.setId(1L);
        
        Consulta consulta1 = new Consulta(1L, funcionario, cliente, animal, "14:00", "Agendada", 
                "Checkup anual", "Paciente sem sintomas aparentes", 150.0);
        consulta1.setAnimal(animal);
        
        Consulta consulta2 = new Consulta(2L, funcionario, cliente, animal, "15:30", "Confirmada", 
                "Vacinação", "Trazer carteira de vacinação", 80.0);
        consulta2.setAnimal(animal);
        
        listaConsultas.add(consulta1);
        listaConsultas.add(consulta2);
        
        when(consultaRepository.findByCliente_Id(1L)).thenReturn(listaConsultas);
        
        List<Consulta> resultado = consultaService.getConsultasByClienteId(1L);
        
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals(1L, resultado.get(0).getCliente().getId());
        assertEquals(1L, resultado.get(1).getCliente().getId());
        verify(consultaRepository, times(1)).findByCliente_Id(1L);
    }
 }