package br.com.grauzely.endereco.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import br.com.grauzely.endereco.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pessoa {
	
	 	private final StringProperty nome;
	    private final StringProperty sobrenome;
	    private final StringProperty logradouro;
	    private final IntegerProperty cep;
	    private final StringProperty cidade;
	    private final ObjectProperty<LocalDate> nascimento;

	    //Construtor padr�o
	    public Pessoa() {
	        this(null, null);
	    }
	    
	    //Construtor com alguns dados iniciais
	    public Pessoa(String nome, String sobrenome) {
	        this.nome = new SimpleStringProperty(nome);
	        this.sobrenome = new SimpleStringProperty(sobrenome);

	        // Alguns dados de exemplo, apenas para testes.
	        this.logradouro = new SimpleStringProperty("Av cnd. da boa vista");
	        this.cep = new SimpleIntegerProperty(50040090);
	        this.cidade = new SimpleStringProperty("Recife");
	        this.nascimento = new SimpleObjectProperty<LocalDate>(LocalDate.of(1999, 2, 21));
	    }

		public StringProperty nomeProperty() {
			return this.nome;
		}

		public java.lang.String getNome() {
			return this.nomeProperty().get();
		}

		public void setNome(final java.lang.String nome) {
			this.nomeProperty().set(nome);
		}

		public StringProperty sobrenomeProperty() {
			return this.sobrenome;
		}

		public java.lang.String getSobrenome() {
			return this.sobrenomeProperty().get();
		}

		public void setSobrenome(final java.lang.String sobrenome) {
			this.sobrenomeProperty().set(sobrenome);
		}

		public StringProperty logradouroProperty() {
			return this.logradouro;
		}

		public java.lang.String getLogradouro() {
			return this.logradouroProperty().get();
		}

		public void setLogradouro(final java.lang.String logradouro) {
			this.logradouroProperty().set(logradouro);
		}

		public IntegerProperty cepProperty() {
			return this.cep;
		}

		public int getCep() {
			return this.cepProperty().get();
		}

		public void setCep(final int cep) {
			this.cepProperty().set(cep);
		}

		public StringProperty cidadeProperty() {
			return this.cidade;
		}

		public java.lang.String getCidade() {
			return this.cidadeProperty().get();
		}

		public void setCidade(final java.lang.String cidade) {
			this.cidadeProperty().set(cidade);
		}

		public ObjectProperty<LocalDate> nascimentoProperty() {
			return this.nascimento;
		}

		@XmlJavaTypeAdapter(LocalDateAdapter.class)
		public java.time.LocalDate getNascimento() {
			return this.nascimentoProperty().get();
		}

		public void setNascimento(final java.time.LocalDate nascimento) {
			this.nascimentoProperty().set(nascimento);
		}
	    
	   
	    
}
