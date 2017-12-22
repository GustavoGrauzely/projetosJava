package br.com.grauzely.acc.service;

import br.com.grauzely.acc.dao.UsuarioDao;
import br.com.grauzely.acc.model.Usuario;

public interface UsuarioService {

	//Criar
	public boolean salvar(Usuario usuario);
	
	//Buscar Usuario
	public Usuario buscarPorUser(String email);
	
	//Atualizar
	public void atualizar(Usuario usuario);
	
	//retorna a implementação que escolhemos
	public static UsuarioService getNewInstance(){
		return new UsuarioDao();
	}
	
}
