package br.com.patchexpress.bean;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.parsers.ParserConfigurationException;

import org.primefaces.context.RequestContext;
import org.xml.sax.SAXException;
import br.com.patchexpress.domain.log;
import br.com.patchexpress.util.Util;

@ManagedBean
public class IndexManagerBean {

	private String origem;
	private String destino;
	private boolean origemOk;
	private boolean destinoOk;
	private Boolean gerarPatchOk;
	private Util util;
	private List<log> listaLog;
	private Integer progress;

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public void barra() {
		  for(progress=0; progress < 100; progress++){
			  if((progress + (100/this.getListaLog().size())) < 100){
				  progress = progress + (100/this.getListaLog().size());
				  RequestContext.getCurrentInstance().update("mainForm:barra");
			  }else{
				  progress = 100;
			  }
          }
		  progress = 100;
    }

	public void initialaze() {
		this.setDestinoOk(false);
		this.setOrigemOk(false);
		this.setUtil(new Util());
		this.setListaLog(new ArrayList<log>());
		this.setGerarPatchOk(false);
	}

	public void gerarPatch() throws IOException, ParserConfigurationException,
			SAXException {
		this.initialaze();
		File dirOrigem = new File(this.getOrigem());
		File dirDestino = new File(this.getDestino());
		if (dirOrigem.exists()) {
			if (!dirDestino.exists()) {
				dirDestino.mkdir();
			}
			if (!dirOrigem.toString().equalsIgnoreCase(dirDestino.toString())) {
				this.setOrigemOk(true);
				this.setDestinoOk(true);
				this.setGerarPatchOk(true);
				RequestContext.getCurrentInstance().update("mainForm");
				ArquivoManagerBean arquivo = new ArquivoManagerBean();
				this.setListaLog(arquivo.GerarAtualizacao(this.getOrigem(),	this.getDestino()));
				this.barra();
				RequestContext.getCurrentInstance().update("mainForm:log_ID");
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_INFO,
								"Patch gerado com sucesso!", ""));
			} else {
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"Aten��o! Por favor digite diret�rios diferentes!",
										""));
			}
		} else {
			if (!dirOrigem.exists()) {
				FacesContext.getCurrentInstance().addMessage(
						null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR,
								"Aten��o! Diret�rio origem n�o existe", ""));
			}
		}
	}

	public void resetar() {
		this.setOrigem(null);
		this.setDestino(null);
		this.setOrigemOk(false);
		this.setDestinoOk(false);
		this.setListaLog(new ArrayList<log>());
		this.setProgress(0);
		this.setGerarPatchOk(false);
	}

	public String getOrigem() {
		return origem;
	}

	public void setOrigem(String origem) {
		this.origem = origem;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public boolean isOrigemOk() {
		return origemOk;
	}

	public void setOrigemOk(boolean origemOk) {
		this.origemOk = origemOk;
	}

	public boolean isDestinoOk() {
		return destinoOk;
	}

	public void setDestinoOk(boolean destinoOk) {
		this.destinoOk = destinoOk;
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

	public List<log> getListaLog() {
		return listaLog;
	}

	public void setListaLog(List<log> listaLog) {
		this.listaLog = listaLog;
	}


	public Boolean getGerarPatchOk() {
		return gerarPatchOk;
	}

	public void setGerarPatchOk(Boolean gerarPatchOk) {
		this.gerarPatchOk = gerarPatchOk;
	}

}