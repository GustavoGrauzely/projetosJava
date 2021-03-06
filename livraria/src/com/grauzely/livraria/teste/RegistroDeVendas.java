package com.grauzely.livraria.teste;

import com.grauzely.livraria.Autor;
import com.grauzely.livraria.produtos.Ebook;
import com.grauzely.livraria.produtos.LivroFisico;
import com.grauzely.livraria.produtos.Produto;

public class RegistroDeVendas {

	public static void main(String[] args) {
		Autor autor = new Autor();
		autor.setNome("Mauricio Aniche");

		LivroFisico fisico = new LivroFisico(autor);
		fisico.setNome("Test-Driven DEvelopment");
		fisico.setValor(59.90);

		Ebook ebook = new Ebook(autor);
		ebook.setNome("Test-Driven DEvelopment");
		ebook.setValor(29.90);

		CarrinhoDeCompras carrinho = new CarrinhoDeCompras();
		carrinho.adiciona(fisico);
		carrinho.adiciona(ebook);

		Produto[] produtos = carrinho.getProdutos();

		/*
		 * for(Produto produto: produtos){ if(produto != null){
		 * System.out.println(produto.getValor()); } }
		 */

	/*	for (Produto produto : produtos) {
			try {
				System.out.println(produto.getValor());
			} catch (Exception e) {
				e.printStackTrace();
			}	
		} */
		System.out.println("Fui executado!");

		System.out.println("Total " + carrinho.getTotal());

	}
}
