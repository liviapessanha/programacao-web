package br.edu.iff.ccc.bsi.sgvet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.iff.ccc.bsi.sgvet.entities.Funcionario;
import br.edu.iff.ccc.bsi.sgvet.enums.Papel;
import br.edu.iff.ccc.bsi.sgvet.repository.FuncionarioRepository;
import br.edu.iff.ccc.bsi.sgvet.services.FuncionarioService;

public class TestFuncionario {
    
    @Mock
    private FuncionarioRepository funcionarioRepository;
    
    @InjectMocks
    private FuncionarioService funcionarioService;
    
    private Funcionario funcionario;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("João Silva");
        funcionario.setEmail("joao.silva@exemplo.com");
        funcionario.setSenha("senha123");
        funcionario.setTelefone("12345678901");
        funcionario.setPapel(Papel.FUNCIONARIO);
        funcionario.setCargo("Veterinário");
        funcionario.setHorario_trabalho("08:00 - 17:00");
    }
    
    @Test
    public void testSaveFuncionario() {
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);
        
        Funcionario savedFuncionario = funcionarioService.save(funcionario);
        
        assertNotNull(savedFuncionario);
        assertEquals(1L, savedFuncionario.getId());
        assertEquals("João Silva", savedFuncionario.getNome());
        assertEquals("joao.silva@exemplo.com", savedFuncionario.getEmail());
        assertEquals("senha123", savedFuncionario.getSenha());
        assertEquals("12345678901", savedFuncionario.getTelefone());
        assertEquals(Papel.FUNCIONARIO, savedFuncionario.getPapel());
        assertEquals("Veterinário", savedFuncionario.getCargo());
        assertEquals("08:00 - 17:00", savedFuncionario.getHorario_trabalho());
        
        verify(funcionarioRepository, times(1)).save(funcionario);
    }
    
//    @Test
//    public void testGetFuncionarioById() {
//        when(funcionarioRepository.findById(1L)).thenReturn(Optional.of(funcionario));
//        when(funcionarioRepository.findById(2L)).thenReturn(Optional.empty());
//
//        Funcionario foundFuncionario = funcionarioService.getById(1L);
//
//        assertNotNull(foundFuncionario);
//        assertEquals(1L, foundFuncionario.getId());
//        assertEquals("João Silva", foundFuncionario.getNome());
//        assertEquals("Veterinário", foundFuncionario.getCargo());
//        assertEquals("08:00 - 17:00", foundFuncionario.getHorario_trabalho());
//
//        Funcionario notFoundFuncionario = funcionarioService.getById(2L);
//        assertNull(notFoundFuncionario);
//
//        verify(funcionarioRepository, times(1)).findById(1L);
//        verify(funcionarioRepository, times(1)).findById(2L);
//    }
}