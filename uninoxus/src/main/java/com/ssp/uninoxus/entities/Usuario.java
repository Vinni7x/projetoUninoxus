package com.ssp.uninoxus.entities;
import java.time.LocalDate;

import com.ssp.uninoxus.enums.Perfil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario extends Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idUsuario;
	
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
    private String Senha;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private Perfil perfil; 
    
    
	public Usuario() {
		
	}
	
	public Usuario(String nomePessoa, String cpf, LocalDate dataNascimento, 
			Long idUsuario, String email, String senha, Perfil perfil) {
		
		super(nomePessoa, cpf, dataNascimento);

		this.idUsuario = idUsuario;
		this.email = email;
		Senha = senha;
		this.perfil = perfil; 
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return Senha;
	}

	public void setSenha(String senha) {
		Senha = senha;
	} 

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
    
    

}
