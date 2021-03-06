package br.com.grauzely.endereco.view;

import br.com.grauzely.endereco.MainApp;
import br.com.grauzely.endereco.model.Pessoa;
import br.com.grauzely.endereco.util.DataUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PessoaOverviewController {
	@FXML
	private TableView<Pessoa> tabelaPessoa;
	@FXML
	private TableColumn<Pessoa, String> colunaNome;
	@FXML
	private TableColumn<Pessoa, String> colunaSobrenome;

	@FXML
	private Label nomeLabel;
	@FXML
	private Label sobrenomeLabel;
	@FXML
	private Label logradouroLabel;
	@FXML
	private Label cepLabel;
	@FXML
	private Label cidadeLabel;
	@FXML
	private Label nascimentoLabel;

	// Reference to the main application.
	private MainApp mainApp;

	/**
	 * O construtor. O construtor � chamado antes do m�todo inicialize().
	 */
	public PessoaOverviewController() {
	}

	/**
	 * Inicializa a classe controller. Este m�todo � chamado automaticamente
	 * ap�s o arquivo fxml ter sido carregado.
	 */
	@FXML
	private void initialize() {
		// Inicializa a tabela de pessoa com duas colunas.
		colunaNome.setCellValueFactory(cellData -> cellData.getValue()
				.nomeProperty());
		colunaSobrenome.setCellValueFactory(cellData -> cellData.getValue()
				.sobrenomeProperty());
		
		// Limpa os detalhes da pessoa.
	    showPersonDetails(null);

	    // Detecta mudan�as de sele��o e mostra os detalhes da pessoa quando houver mudan�a.
	    tabelaPessoa.getSelectionModel().selectedItemProperty().addListener(
	            (observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	/**
	 * � chamado pela aplica��o principal para dar uma refer�ncia de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Adiciona os dados da observable list na tabela
		tabelaPessoa.setItems(mainApp.getListaPessoas());
	}
	
	private void showPersonDetails(Pessoa pessoa) {
	    if (pessoa != null) {
	        // Preenche as labels com informa��es do objeto person.
	        nomeLabel.setText(pessoa.getNome());
	        sobrenomeLabel.setText(pessoa.getSobrenome());
	        logradouroLabel.setText(pessoa.getLogradouro());
	        cepLabel.setText(Integer.toString(pessoa.getCep()));
	        cidadeLabel.setText(pessoa.getCidade());
	        nascimentoLabel.setText(DataUtil.format(pessoa.getNascimento()));
	    } else {
	        // Person � null, remove todo o texto.
	    	nomeLabel.setText("");
	    	sobrenomeLabel.setText("");
	    	logradouroLabel.setText("");
	    	cepLabel.setText("");
	    	cidadeLabel.setText("");
	        nascimentoLabel.setText("");
	    }
	}
	
	/**
	 * Chamado quando o usu�rio clica no bot�o delete.
	 */
	@FXML
	private void handleDeletePessoa() {
	    int selectedIndex = tabelaPessoa.getSelectionModel().getSelectedIndex();
	    if (selectedIndex >= 0) {
	    	tabelaPessoa.getItems().remove(selectedIndex);
	    } else {
	        // Nada selecionado.
	      Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Nenhuma sele��o");
	            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	            stage.getIcons().add(new Image("file:resources/imagens/book.png"));
	            alert.setHeaderText("Nenhuma Pessoa Selecionada");
	            alert.setContentText("Por favor, selecione uma pessoa na tabela.");
	            alert.showAndWait();
	    }
	}
	
	/**
	 * Chamado quando o usu�rio clica no bot�o novo. Abre uma janela para editar
	 * detalhes da nova pessoa.
	 */
	@FXML
	private void handleNewPerson() {
	    Pessoa tempPessoa = new Pessoa();
	    boolean okClicked = mainApp.showPersonEditDialog(tempPessoa);
	    if (okClicked) {
	        mainApp.getListaPessoas().add(tempPessoa);
	    }
	}

	/**
	 * Chamado quando o usu�rio clica no bot�o edit. Abre a janela para editar
	 * detalhes da pessoa selecionada.
	 */
	@FXML
	private void handleEditPerson() {
	    Pessoa selectedPerson = tabelaPessoa.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	        boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
	        if (okClicked) {
	            showPersonDetails(selectedPerson);
	        }

	    } else {
	        // Nada seleciondo.
	        Alert alert = new Alert(AlertType.WARNING);
	            alert.setTitle("Nenhuma sele��o");
	            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
	            stage.getIcons().add(new Image("file:resources/imagens/book.png"));
	            alert.setHeaderText("Nenhuma Pessoa Selecionada");
	            alert.setContentText("Por favor, selecione uma pessoa na tabela.");
	            alert.showAndWait();
	    }
	}
	
}
