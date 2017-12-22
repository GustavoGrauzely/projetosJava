package br.com.grauzely.acc.service;

import java.util.List;

import br.com.grauzely.acc.dao.ServiceRequestsDao;
import br.com.grauzely.acc.dao.UsuarioDao;
import br.com.grauzely.acc.model.ServiceRequests;

public interface ServiceRequestsService {

	//Criar
	public boolean salvar(ServiceRequests sr);
	
	//Listagem
	public List<ServiceRequests> buscarTodas();
	public ServiceRequests buscarPorId(int id);	
	
	//Apagar
	public void apagar(int id);
	
	//Atualização
	public void atualizar(ServiceRequests sr);
	
	//retorna a implementação que escolhemos
		public static ServiceRequestsService getNewInstance(){
			return new ServiceRequestsDao();
		}	
	
}
