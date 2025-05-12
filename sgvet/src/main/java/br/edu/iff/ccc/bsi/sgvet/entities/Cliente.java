package br.edu.iff.ccc.bsi.sgvet.entities;

import java.util.ArrayList;
import java.util.List;

import br.edu.iff.ccc.bsi.sgvet.enums.Papel;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente")
	private List<Consulta> consultas = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente")
	private List<Animal> animais = new ArrayList<>();

	@NotBlank(message = "Campo obrigatório.")
	@Column(name = "CPF", unique = true, length = 11)
	private String cpf;
	
	@NotBlank(message = "Campo obrigatório.")
	private String endereco;
	
	public Cliente() {
		super();
		this.setPapel(Papel.CLIENTE);
	}

	public Cliente(Long id, String nome, String email, String senha, String telefone, Papel papel, String cpf, String endereco) {
		super(id, nome, email, senha, telefone, Papel.CLIENTE);
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	
}
