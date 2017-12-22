package br.com.grauzely.acc.model;

import java.util.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ServiceRequests {

	private final IntegerProperty id_sr;
	private final IntegerProperty id_geracao;
	private final ObjectProperty<Date> data_geracao;
	private final StringProperty demanda;
	private final StringProperty numeroSR;
	private final StringProperty status;
	private final StringProperty usuario;
	private final StringProperty descricao;
	private final StringProperty data;

	public ServiceRequests() {
		this(0, 0, null, null, null, null, null, null, null);
	}

	public ServiceRequests(Integer id, Integer id_geracao,
			Date data_geracao, String demanda, String numeroSR, String status,
			String usuario, String descricao, String data) {
		this.id_sr = new SimpleIntegerProperty(id);
		this.id_geracao = new SimpleIntegerProperty(id_geracao);
		this.data_geracao = new SimpleObjectProperty<Date>(data_geracao);
		this.demanda = new SimpleStringProperty(demanda);
		this.numeroSR = new SimpleStringProperty(numeroSR);
		this.status = new SimpleStringProperty(status);
		this.usuario = new SimpleStringProperty(usuario);
		this.descricao = new SimpleStringProperty(descricao);
		this.data = new SimpleStringProperty(data);
	}

	public final StringProperty demandaProperty() {
		return this.demanda;
	}

	public final java.lang.String getDemanda() {
		return this.demandaProperty().get();
	}

	public final void setDemanda(final java.lang.String demanda) {
		this.demandaProperty().set(demanda);
	}

	public final StringProperty numeroSRProperty() {
		return this.numeroSR;
	}

	public final java.lang.String getNumeroSR() {
		return this.numeroSRProperty().get();
	}

	public final void setNumeroSR(final java.lang.String numeroSR) {
		this.numeroSRProperty().set(numeroSR);
	}

	public final StringProperty statusProperty() {
		return this.status;
	}

	public final java.lang.String getStatus() {
		return this.statusProperty().get();
	}

	public final void setStatus(final java.lang.String status) {
		this.statusProperty().set(status);
	}

	public final StringProperty usuarioProperty() {
		return this.usuario;
	}

	public final java.lang.String getUsuario() {
		return this.usuarioProperty().get();
	}

	public final void setUsuario(final java.lang.String usuario) {
		this.usuarioProperty().set(usuario);
	}

	public final StringProperty descricaoProperty() {
		return this.descricao;
	}

	public final String getDescricao() {
		return this.descricaoProperty().get();
	}

	public final void setDescricao(final String descricao) {
		this.descricaoProperty().set(descricao);
	}

	public final IntegerProperty id_srProperty() {
		return this.id_sr;
	}

	public final int getId_sr() {
		return this.id_srProperty().get();
	}

	public final void setId_sr(final int id_sr) {
		this.id_srProperty().set(id_sr);
	}

	public final IntegerProperty id_geracaoProperty() {
		return this.id_geracao;
	}

	public final int getId_geracao() {
		return this.id_geracaoProperty().get();
	}

	public final void setId_geracao(final int id_geracao) {
		this.id_geracaoProperty().set(id_geracao);
	}

	public final ObjectProperty<Date> data_geracaoProperty() {
		return this.data_geracao;
	}

	public final java.util.Date getData_geracao() {
		return this.data_geracaoProperty().get();
	}

	public final void setData_geracao(final java.util.Date data_geracao) {
		this.data_geracaoProperty().set(data_geracao);
	}

	public final StringProperty dataProperty() {
		return this.data;
	}

	public final java.lang.String getData() {
		return this.dataProperty().get();
	}

	public final void setData(final java.lang.String data) {
		this.dataProperty().set(data);
	}

	
}
