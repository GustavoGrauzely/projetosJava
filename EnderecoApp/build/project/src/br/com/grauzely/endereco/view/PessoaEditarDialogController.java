package br.com.grauzely.endereco.view;

import br.com.grauzely.endereco.model.Pessoa;
import br.com.grauzely.endereco.util.DataUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PessoaEditarDialogController {

	@FXML
    private TextField nomeCampo;
    @FXML
    private TextField sobrenomeCampo;
    @FXML
    private TextField logradouroCampo;
    @FXML
    private TextField cepCampo;
    @FXML
    private TextField cidadeCampo;
    @FXML
    private TextField nascimentoCampo;


    private Stage dialogStage;
    private Pessoa pessoa;
    private boolean okClicked = false;

    /**
     * Inicializa a classe controlle. Este m�todo � chamado automaticamente
     * ap�s o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Define o palco deste dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Define a pessoa a ser editada no dialog.
     * 
     * @param person
     */
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;

        nomeCampo.setText(pessoa.getNome());
        sobrenomeCampo.setText(pessoa.getSobrenome());
        logradouroCampo.setText(pessoa.getLogradouro());
        cepCampo.setText(Integer.toString(pessoa.getCep()));
        cidadeCampo.setText(pessoa.getCidade());
        nascimentoCampo.setText(DataUtil.format(pessoa.getNascimento()));
        nascimentoCampo.setPromptText("dd.mm.yyyy");
    }

    /**
     * Retorna true se o usu�rio clicar OK,caso contr�rio false.
     * 
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Chamado quando o usu�rio clica OK.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
        	pessoa.setNome(nomeCampo.getText());
        	pessoa.setSobrenome(sobrenomeCampo.getText());
        	pessoa.setLogradouro(logradouroCampo.getText());
        	pessoa.setCep(Integer.parseInt(cepCampo.getText()));
        	pessoa.setCidade(cidadeCampo.getText());
            pessoa.setNascimento(DataUtil.parse(nascimentoCampo.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Chamado quando o usu�rio clica Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Valida a entrada do usu�rio nos campos de texto.
     * 
     * @return true se a entrada � v�lida
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (nomeCampo.getText() == null || nomeCampo.getText().length() == 0) {
            errorMessage += "Nome inv�lido!\n"; 
        }
        if (sobrenomeCampo.getText() == null || sobrenomeCampo.getText().length() == 0) {
            errorMessage += "Sobrenome inv�lido!\n"; 
        }
        if (logradouroCampo.getText() == null || logradouroCampo.getText().length() == 0) {
            errorMessage += "Rua inv�lida!\n"; 
        }

        if (cepCampo.getText() == null || cepCampo.getText().length() == 0) {
            errorMessage += "C�digo Postal inv�lido!\n"; 
        } else {
            // tenta converter o c�digo postal em um int.
            try {
                Integer.parseInt(cepCampo.getText());
            } catch (NumberFormatException e) {
                errorMessage += "C�digo Postal inv�lido (deve ser um inteiro)!\n"; 
            }
        }

        if (cidadeCampo.getText() == null || cidadeCampo.getText().length() == 0) {
            errorMessage += "Cidade inv�lida!\n"; 
        }

        if (nascimentoCampo.getText() == null || nascimentoCampo.getText().length() == 0) {
            errorMessage += "Anivers�rio inv�lido!\n";
        } else {
            if (!DataUtil.validDate(nascimentoCampo.getText())) {
                errorMessage += "Anivers�rio inv�lido. Use o formato dd.mm.yyyy!\n";
            }
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Mostra a mensagem de erro.
            	Alert alert = new Alert(AlertType.ERROR);
                      alert.setTitle("Campos Inv�lidos");
                      alert.setHeaderText("Por favor, corrija os campos inv�lidos");
                      alert.setContentText(errorMessage);
                alert.showAndWait();

            return false;
        }
    }
	
}
