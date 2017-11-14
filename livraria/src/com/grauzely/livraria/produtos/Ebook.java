package com.grauzely.livraria.produtos;

import com.grauzely.livraria.Autor;

public class Ebook extends Livro implements Promocional {
	
	private String waterMark;
	
	public Ebook(Autor autor){
		super(autor);
	}
	@Override
	public String toString(){
	
		return "eu sou um ebook!";
	}
	
	@Override
	public boolean aplicarDescontoDe(double perc) {
		if(perc > 0.15){
			return false;
		}
		double desconto = this.getValor() * perc;
		this.setValor(this.getValor() - desconto);
		return true;
	}

	public String getWaterMark() {
		return waterMark;
	}

	public void setWaterMark(String waterMark) {
		this.waterMark = waterMark;
	}
	
}
