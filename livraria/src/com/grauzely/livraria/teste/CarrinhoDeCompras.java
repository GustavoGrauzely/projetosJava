package com.grauzely.livraria.teste;

import com.grauzely.livraria.produtos.Produto;

public class CarrinhoDeCompras {

	
	private double total;
	private Produto[] produtos = new Produto[10];
	private int contador = 0;
		
	public void adiciona(Produto produto){
		System.out.println("Adicionando: "+ produto);
		this.produtos[contador] = produto;
		contador++;
		this.total +=produto.getValor();
	}
	
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}

	public Produto[] getProdutos() {
		return produtos;
	}

	public void setProdutos(Produto[] produtos) {
		this.produtos = produtos;
	}

	
	
	
	
}
