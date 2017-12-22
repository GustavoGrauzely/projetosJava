package br.com.grauzely.acc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import br.com.grauzely.acc.model.Usuario;
import br.com.grauzely.acc.service.UsuarioService;
import br.com.grauzely.acc.util.ConnectionUtil;

public class UsuarioDao implements UsuarioService {
	
	Connection connection = null;
		
	public UsuarioDao(){
		connection = ConnectionUtil.connectdb();
	}
	
		// comandos
		final String INSERIR = "INSERT INTO usuarios (apelito, nome, email, senha) VALUES (?, ?, ?, ?)";
		final String ATUALIZAR = "UPDATE usuarios SET senha=? WHERE ID = ?";
		final String BUSCAR = "SELECT apelito, nome, email, senha FROM usuarios WHERE ID = ?";
	
		@Override
		public boolean salvar(Usuario usuario) {
			try {
				PreparedStatement salvar = connection.prepareStatement(INSERIR);
				salvar.setString(1, usuario.getApelito());
				salvar.setString(2, usuario.getNome());
				salvar.setString(3, usuario.getEmail());
				salvar.setString(4, usuario.getSenha());
				salvar.execute();
				salvar.close();
				connection.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR AO SALVAR USUÁRIO");
				System.exit(0);
				return false;
			} 
		}


		@Override
		public Usuario buscarPorUser(String email) {
			Usuario user = new Usuario();
			try {
				PreparedStatement buscar = connection.prepareStatement(BUSCAR);
				buscar.setString(1, email);
				ResultSet resultadoBusca = buscar.executeQuery();
				resultadoBusca.next();
			    if (!resultadoBusca.next()) {
					infoBox("Email não cadastrado", "Atenção!", null);
				} else {
					while(resultadoBusca.next()){
						user.setId(Integer.valueOf(resultadoBusca.getString("id")));
						user.setApelito(resultadoBusca.getString("apelito"));
						user.setNome(resultadoBusca.getString("nome"));
						user.setEmail(resultadoBusca.getString("email"));
						user.setSenha(resultadoBusca.getString("senha"));
					}
					resultadoBusca.close();
				}
				buscar.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR BUSCANDO EMAIL " + email);
				System.exit(0);
			} 
			return user;
		}

		@Override
		public void atualizar(Usuario usuario) {
			try {
				PreparedStatement atualizar = connection.prepareStatement(ATUALIZAR);
				atualizar.setString(1, usuario.getSenha());
				atualizar.setInt(2, usuario.getId());
				atualizar.executeUpdate();
				atualizar.close();
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("ERROR ATUALIZANDO USUARIO COM ID " + usuario.getId());
				System.exit(0);
			} 

		}
		
		public static void infoBox(String infoMessage, String titleBar,
				String headerMessage) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle(titleBar);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image("file:resources/imagens/setup.png"));
			alert.setHeaderText(headerMessage);
			alert.setContentText(infoMessage);
			alert.showAndWait();
		}


}
