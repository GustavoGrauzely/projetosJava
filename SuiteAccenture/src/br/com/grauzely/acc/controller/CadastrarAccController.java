package br.com.grauzely.acc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import br.com.grauzely.acc.MainApp;
import br.com.grauzely.acc.dao.UsuarioDao;
import br.com.grauzely.acc.model.Usuario;
import br.com.grauzely.acc.util.ConnectionUtil;

public class CadastrarAccController {

	@FXML
	private TextField usuario;
	@FXML
	private TextField nome;
	@FXML
	private TextField email;
	@FXML
	private PasswordField senha;

	private MainApp mainApp;
	private Stage dialogStage;
	private Usuario usuarios;
	private UsuarioDao usuarioDao;

	Stage dialogInfo = new Stage();
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	public CadastrarAccController() {
		connection = ConnectionUtil.connectdb();
	}

	@FXML
	private void cadastrarLogin(ActionEvent event) {
		usuarios.setApelito(usuario.getText());
		usuarios.setNome(nome.getText());
		usuarios.setEmail(email.getText());
		usuarios.setSenha(senha.getText());
		if (usuarioDao.salvar(usuarios)) {
			infoBox("Usuário Cadastrado!", "Sucesso!", null);
			Node source = (Node) event.getSource();
			dialogInfo = (Stage) source.getScene().getWindow();
			dialogInfo.close();
			dialogStage.close();
		}else{
			infoBox("Erro ao cadastrar usuário, por favor tente novamente!", "Atenção!", null);
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

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Define o palco deste dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	@FXML
	private void voltar() {
		dialogStage.close();
	}

	/**
	 * Inicializa a classe controlle. Este método é chamado automaticamente após
	 * o arquivo fxml ter sido carregado.
	 */
	@FXML
	private void initialize() {
	}

}
