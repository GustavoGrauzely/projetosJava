package com.grauzely.livraria;

public class Autor {
	
	private String nome;
	private String email;
	private String cpf;
	
	public void mostrarDetalhes(){
		String msg = "Mostrando detalhes do autor: ";
		System.out.println(msg);
		System.out.println(nome);
		System.out.println(email);
		System.out.println(cpf);
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
