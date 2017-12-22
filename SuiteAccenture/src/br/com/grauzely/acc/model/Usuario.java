package br.com.grauzely.acc.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Usuario {

	private final IntegerProperty id;
	private final StringProperty apelito;
    private final StringProperty nome;
    private final StringProperty email;
    private final StringProperty senha;

    public Usuario() {
        this(null, null, null, null, null);
    }
    
    public Usuario(Integer id, String apelito,String nome,String email,String senha){
    	this.id = new SimpleIntegerProperty(id);
    	this.apelito = new SimpleStringProperty(apelito);
        this.nome = new SimpleStringProperty(nome);
        this.email = new SimpleStringProperty(email);
        this.senha = new SimpleStringProperty(senha);
    }

	public final StringProperty apelitoProperty() {
		return this.apelito;
	}

	public final java.lang.String getApelito() {
		return this.apelitoProperty().get();
	}

	public final void setApelito(final java.lang.String apelito) {
		this.apelitoProperty().set(apelito);
	}

	public final StringProperty nomeProperty() {
		return this.nome;
	}

	public final java.lang.String getNome() {
		return this.nomeProperty().get();
	}

	public final void setNome(final java.lang.String nome) {
		this.nomeProperty().set(nome);
	}

	public final StringProperty emailProperty() {
		return this.email;
	}

	public final java.lang.String getEmail() {
		return this.emailProperty().get();
	}

	public final void setEmail(final java.lang.String email) {
		this.emailProperty().set(email);
	}

	public final StringProperty senhaProperty() {
		return this.senha;
	}

	public final java.lang.String getSenha() {
		return this.senhaProperty().get();
	}

	public final void setSenha(final java.lang.String senha) {
		this.senhaProperty().set(senha);
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}

	public final int getId() {
		return this.idProperty().get();
	}

	public final void setId(final int id) {
		this.idProperty().set(id);
	}
	
}
