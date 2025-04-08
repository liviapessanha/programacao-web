package br.edu.iff.ccc.bsi.sgvet.entities;

import java.io.Serializable;
import java.util.Objects;

import br.edu.iff.ccc.bsi.sgvet.enums.Papel;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@MappedSuperclass
public abstract class Usuario implements Serializable {

	protected static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Campo obrigatório.")
	@Size(min = 1, max = 80, message = "O nome deve ter entre 1 e 80 caracteres.")
	@Column (length = 80)
	private String nome;
	
	@NotBlank(message = "Campo obrigatório.")
	@Email(message = "O e-mail deve ser válido.")
	private String email;
	
	@NotBlank(message = "Campo obrigatório.")
	private String senha;
	
	@NotBlank(message = "Campo obrigatório.")
	@Size(min = 11, max = 11, message = "O telefone deve ter 11 caracteres.")
	private String telefone;
	
	@NotNull(message = "Campo obrigatório.")
	@Enumerated(EnumType.STRING)
	private Papel papel;
	
	public Usuario() {
		
	}

	public Usuario(Long id, String nome, String email, String senha, String telefone, Papel papel) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.telefone = telefone;
		this.papel = papel;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Papel getPapel() {
		return papel;
	}

	public void setPapel(Papel papel) {
		this.papel = papel;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
