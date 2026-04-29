package com.ssp.uninoxus.entities;
import java.time.LocalDate;

import jakarta.persistence.MappedSuperclass;



@MappedSuperclass 
public abstract class  Pessoa {
	
	private String nomePessoa;
	
	private String cpf;
	private LocalDate dataNascimento;  
	
	public Pessoa () {
		
	}
	
	public Pessoa(String nomePessoa, String cpf, LocalDate dataNascimento ) {
		super();
		this.nomePessoa = nomePessoa;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;		
	}

	public String getNomePessoa() {
		return nomePessoa; 
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


}
