package br.edu.iff.ccc.bsi.sgvet.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Funcionario extends Usuario {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL)
	private List<Consulta> consultas = new ArrayList<>();
	
	@NotBlank(message = "Campo obrigatório.")
	private String cargo;
	
	@NotBlank(message = "Campo obrigatório.")
	private String horario_trabalho;
	
	public Funcionario() {
		
	}

	public Funcionario(String cargo, String horario_trabalho) {
		super();
		this.cargo = cargo;
		this.horario_trabalho = horario_trabalho;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getHorario_trabalho() {
		return horario_trabalho;
	}

	public void setHorario_trabalho(String horario_trabalho) {
		this.horario_trabalho = horario_trabalho;
	}
}
