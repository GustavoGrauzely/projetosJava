package br.com.patchexpress.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.patchexpress.domain.Cliente;
import br.com.patchexpress.factory.ConexaoFactory;

public class ClienteDAO {
	
	public void salvar(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO CLIENTE (ID,NOME)VALUES((?),(?)) ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getId());
		comando.setString(2,c.getNome());
		
		comando.executeUpdate();	
	}
	
	public Cliente consultarCliente(Cliente c) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT * FROM CLIENTE WHERE ID = ? ");
	
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getId());
		
		ResultSet result =  comando.executeQuery();
		
		Cliente retornoCliente = null;
		if(result.next()){
			retornoCliente = new Cliente();
			
			retornoCliente.setId(result.getLong("id"));
			retornoCliente.setNome(result.getString("nome"));
		}
		return retornoCliente;
	}
	
	
	public ArrayList<Cliente> listarTodosCliente() throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM CLIENTE  ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		ResultSet result =  comando.executeQuery();
		
		
		ArrayList<Cliente> listarTodos = new ArrayList<Cliente>();
		
		while(result.next()){
			Cliente clienteList = new Cliente();
			clienteList.setId(result.getLong("codigo"));
			clienteList.setNome(result.getString("nome"));
			
			listarTodos.add(clienteList);
		}
		
		return listarTodos;
		
	}
	
	public void edit(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE CLIENTE SET NOME = ? WHERE ID = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1,c.getNome());
		comando.setLong(2, c.getId());
		
		comando.executeUpdate();
	}
	
	public void excluir(Cliente c) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("DELETE FROM CLIENTE WHERE ID ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, c.getId());
		comando.executeUpdate();
		
	}
	
//	public static void main(String[] args) {
//		
//		ClienteDAO clienteDAO = new ClienteDAO();
//		try {
//			ArrayList<Cliente> lista = clienteDAO.listarTodosCliente();
//			for (Cliente c :lista) {
//				System.out.println("Resulto"+c);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

}
