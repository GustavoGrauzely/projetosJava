package br.com.patchexpress.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	private static final String USUARIO = "postgres";
	private static final String SENHA = "sug";
	private static final String URL = "jdbc:postgresql://localhost:5432/SUG";
	private Connection con;
	
	//Conexão
		public static Connection conectar() throws SQLException{
		
			Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
			
			return conexao;
		}
	
	
	ConexaoFactory(){
		
		try{
			Class.forName("org.postgresql.Driver");
			setCon(DriverManager.getConnection(URL, USUARIO, SENHA));
		
			System.out.println("Conexão com Projeto SUG Ok !!");
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Conexão não "+e);
		}
		
		
	}


	public Connection getCon() {
		return con;
	}


	public void setCon(Connection con) {
		this.con = con;
	}
	
}
