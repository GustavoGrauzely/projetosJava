package com.grauzely.livraria.teste;

import com.grauzely.livraria.Autor;
import com.grauzely.livraria.produtos.Ebook;
import com.grauzely.livraria.produtos.Livro;
import com.grauzely.livraria.produtos.LivroFisico;

public class CadastroDeLivros {

	public static void main(String[] args) {
		
		Autor autor = new Autor();
		autor.setNome("Joao silva");
		autor.setCpf("2312321321");
		autor.setEmail("joao@gmail.com");
		
		Livro livro = new LivroFisico(autor);
		livro.setNome("Java 8 Pratico");
		livro.setDescricao("Livro sobre Java 8");
		livro.setValor(20.00);
		livro.setIsbn("21321");
		
				
		livro.mostrarDetalhes();
		
		Livro livro2 = new Ebook(autor);
		livro2.setNome("Java 8 Pratico");
		livro2.setDescricao("Livro sobre Java 8");
		livro2.setValor(20.00);
		livro2.setAutor(new Autor());
		livro2.getAutor().setNome("Joao silva");
		livro2.getAutor().setCpf("2312321321");
		livro2.getAutor().setEmail("joao@gmail.com");
				
		livro2.mostrarDetalhes();
		
		Livro livro3 = new Ebook(autor);
		livro3.setNome("Java 8 Pratico");
		livro3.setDescricao("Livro sobre Java 8");
		livro3.setValor(20.00);
		livro3.setAutor(new Autor());
		livro3.getAutor().setNome("Joao silva");
		livro3.getAutor().setCpf("2312321321");
		livro3.getAutor().setEmail("joao@gmail.com");
				
		livro3.mostrarDetalhes();
	}

}
