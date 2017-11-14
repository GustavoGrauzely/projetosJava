package com.grauzely.livraria.produtos;

import com.grauzely.livraria.Autor;

public class LivroFisico extends Livro implements Promocional{

	public LivroFisico(Autor autor){
		super(autor);
	}
	
	public double getTaxaImpressao(){
		return this.getValor() * 0.05;
	}
	
	@Override
	public boolean aplicarDescontoDe(double perc) {
		if(perc > 0.3){
			return false;
		}
		double desconto = this.getValor() * perc;
		this.setValor(this.getValor() - desconto);
		return true;
	}
	
}
