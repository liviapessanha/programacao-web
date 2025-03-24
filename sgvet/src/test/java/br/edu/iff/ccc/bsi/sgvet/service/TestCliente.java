package br.edu.iff.ccc.bsi.sgvet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.iff.ccc.bsi.sgvet.entities.Cliente;
import br.edu.iff.ccc.bsi.sgvet.enums.Papel;
import br.edu.iff.ccc.bsi.sgvet.repository.ClienteRepository;
import br.edu.iff.ccc.bsi.sgvet.services.ClienteService;

public class TestCliente {
    
    @Mock
    private ClienteRepository clienteRepository;
    
    @InjectMocks
    private ClienteService clienteService;
    
    private Cliente cliente;
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Maria Santos");
        cliente.setEmail("maria.santos@exemplo.com");
        cliente.setSenha("senha456");
        cliente.setTelefone("98765432101");
        cliente.setPapel(Papel.CLIENTE);
        cliente.setCpf("12345678901");
        cliente.setEndereco("Rua das Flores, 123 - Centro");
    }
    
    @Test
    public void testSaveCliente() {
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        
        Cliente savedCliente = clienteService.save(cliente);
        
        assertNotNull(savedCliente);
        assertEquals(1L, savedCliente.getId());
        assertEquals("Maria Santos", savedCliente.getNome());
        assertEquals("maria.santos@exemplo.com", savedCliente.getEmail());
        assertEquals("senha456", savedCliente.getSenha());
        assertEquals("98765432101", savedCliente.getTelefone());
        assertEquals(Papel.CLIENTE, savedCliente.getPapel());
        assertEquals("12345678901", savedCliente.getCpf());
        assertEquals("Rua das Flores, 123 - Centro", savedCliente.getEndereco());
        
        verify(clienteRepository, times(1)).save(cliente);
    }
    
    @Test
    public void testGetAllClientes() {
        List<Cliente> listaClientes = new ArrayList<>();
        
        listaClientes.add(cliente);
        
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("João Oliveira");
        cliente2.setEmail("joao.oliveira@exemplo.com");
        cliente2.setSenha("senha789");
        cliente2.setTelefone("45678912301");
        cliente2.setPapel(Papel.CLIENTE);
        cliente2.setCpf("98765432109");
        cliente2.setEndereco("Avenida Principal, 456 - Bairro Novo");
        
        listaClientes.add(cliente2);
        
        when(clienteRepository.findAll()).thenReturn(listaClientes);
        
        List<Cliente> result = clienteService.getAll();
        
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals("Maria Santos", result.get(0).getNome());
        assertEquals(2L, result.get(1).getId());
        assertEquals("João Oliveira", result.get(1).getNome());
        
        verify(clienteRepository, times(1)).findAll();
    }
}