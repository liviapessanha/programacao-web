package br.edu.iff.ccc.bsi.sgvet.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Consulta> consultas = new ArrayList<>();
	
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Animal> animais = new ArrayList<>();

	@NotBlank(message = "Campo obrigatório.")
	@Column(name = "CPF", unique = true, length = 11)
	private String cpf;
	
	@NotBlank(message = "Campo obrigatório.")
	private String endereco;
	
	public Cliente() {
		
	}

	public Cliente(String cpf, String endereco) {
		super();
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
