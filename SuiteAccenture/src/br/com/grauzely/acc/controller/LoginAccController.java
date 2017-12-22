package br.com.grauzely.acc.controller;

import javafx.fxml.Initializable;
import br.com.grauzely.acc.MainApp;
import br.com.grauzely.acc.util.ConnectionUtil;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class LoginAccController implements Initializable {

	private MainApp mainApp;

	@FXML
	private TextField apelido;

	@FXML
	private PasswordField senha;
	

	Stage dialogStage = new Stage();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	public LoginAccController() {
		connection = ConnectionUtil.connectdb();
	}
	
	@FXML
	private void cadastroLogin() {
		mainApp.cadastrarLogin();
	}

	@FXML
	private void entrarLogin(ActionEvent event) {
		String username = apelido.getText().toString();
		String password = senha.getText().toString();

		String sql = "SELECT * FROM usuarios WHERE apelito = ? and senha = ?";

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				infoBox("Email e senha incorretas", "Atenção!", null);
			} else {
				Node source = (Node) event.getSource();
				dialogStage = (Stage) source.getScene().getWindow();
				dialogStage.close();
				mainApp.abrirMenu();
		}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void infoBox(String infoMessage, String titleBar,	String headerMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titleBar);
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("file:resources/imagens/setup.png"));
		alert.setHeaderText(headerMessage);
		alert.setContentText(infoMessage);
		alert.showAndWait();
	}

	/**
	 * É chamado pela aplicação principal para referenciar a si mesma.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Fecha a aplicação.
	 */
	@FXML
	private void sair() {
		System.exit(0);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}
