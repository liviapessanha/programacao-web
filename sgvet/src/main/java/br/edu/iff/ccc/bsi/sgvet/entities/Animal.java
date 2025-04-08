package br.edu.iff.ccc.bsi.sgvet.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import br.edu.iff.ccc.bsi.sgvet.enums.Sexo;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Animal implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório.")
	@Size(min = 1, max = 80, message = "O nome deve ter entre 1 e 80 caracteres.")
	@Column (length = 80)
	private String nome;
	
	@NotBlank(message = "Campo obrigatório.")
	@Size(min = 1, max = 80, message = "A espécie deve ter entre 1 e 80 caracteres.")
	@Column (length = 80)
	private String especie;
	
	@NotBlank(message = "Campo obrigatório.")
	@Size(min = 1, max = 80, message = "A raça deve ter entre 1 e 80 caracteres.")
	@Column (length = 80)
	private String raca;
	
	@NotNull(message = "Campo obrigatório.")
	private Integer idade;
	
	@NotNull(message = "Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@NotNull(message = "Campo obrigatório.")
	private Double peso;
	
	@NotBlank(message = "Campo obrigatório.")
	private String cor;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "animal", cascade = CascadeType.ALL)
	private List<Consulta> consultas = new ArrayList<>();
	
	public Animal() {
		
	}

	public Animal(Long id, String nome, String especie, String raca, Integer idade, Sexo sexo, Double peso,
			String cor, String observacao, Cliente cliente, List<Consulta> consultas) {
		super();
		this.id = id;
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.idade = idade;
		this.sexo = sexo;
		this.peso = peso;
		this.cor = cor;
		this.observacao = observacao;
		this.cliente = cliente;
		this.consultas = consultas;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animal other = (Animal) obj;
		return Objects.equals(id, other.id);
	}
}
