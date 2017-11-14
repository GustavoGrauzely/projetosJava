package com.grauzely.livraria.produtos;

import com.grauzely.livraria.Editora;

public class Revista implements Produto, Promocional{
	
	private String nome;
	private String descricao;
	private double valor;
	private Editora editora;
	
	@Override
	public boolean aplicarDescontoDe(double perc) {
		if(perc > 0.3){
			return false;
		}
		double desconto = this.getValor() * perc;
		this.setValor(this.getValor() - desconto);
		return true;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}

}
