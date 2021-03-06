package br.com.grauzely.endereco;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import br.com.grauzely.endereco.model.Pessoa;
import br.com.grauzely.endereco.model.PessoaListWrapper;
import br.com.grauzely.endereco.view.PessoaEditarDialogController;
import br.com.grauzely.endereco.view.PessoaOverviewController;
import br.com.grauzely.endereco.view.RootLayoutController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Pessoa> listaPessoas = FXCollections.observableArrayList();

    
    public MainApp() {
    	listaPessoas.add(new Pessoa("Hans", "Muster"));
    	listaPessoas.add(new Pessoa("Ruth", "Mueller"));
    	listaPessoas.add(new Pessoa("Heinz", "Kurz"));
    	listaPessoas.add(new Pessoa("Cornelia", "Meier"));
    	listaPessoas.add(new Pessoa("Werner", "Meyer"));
    	listaPessoas.add(new Pessoa("Lydia", "Kunz"));
    	listaPessoas.add(new Pessoa("Anna", "Best"));
    	listaPessoas.add(new Pessoa("Stefan", "Meier"));
    	listaPessoas.add(new Pessoa("Martin", "Mueller"));
     }
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("EnderecoApp");
        this.primaryStage.getIcons().add(new Image("file:resources/imagens/book.png"));

        initRootLayout();

        showPersonOverview();
    }

    /**
     * Inicializa o root layout (layout base).
     */
    public void initRootLayout() {
    	 try {
    	        // Carrega o root layout do arquivo fxml.
    	        FXMLLoader loader = new FXMLLoader();
    	        loader.setLocation(MainApp.class
    	                .getResource("view/RootLayout.fxml"));
    	        rootLayout = (BorderPane) loader.load();

    	        // Mostra a scene (cena) contendo o root layout.
    	        Scene scene = new Scene(rootLayout);
    	        primaryStage.setScene(scene);

    	        // D� ao controller o acesso ao main app.
    	        RootLayoutController controller = loader.getController();
    	        controller.setMainApp(this);

    	        primaryStage.show();
    	    } catch (IOException e) {
    	        e.printStackTrace();
    	    }

    	    // Tenta carregar o �ltimo arquivo de pessoa aberto.
    	    File file = getPessoaFilePath();
    	    if (file != null) {
    	        loadPersonDataFromFile(file);
    	    }
    }

    /**
     * Mostra o person overview dentro do root layout.
     */
    public void showPersonOverview() {
    	try {
            // Carrega a person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PessoaOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            // Define a person overview no centro do root layout.
            rootLayout.setCenter(personOverview);

            // D� ao controlador acesso � the main app.
            PessoaOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna o palco principal.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public ObservableList<Pessoa> getListaPessoas() {
        return listaPessoas;
    }
    
    public boolean showPersonEditDialog(Pessoa pessoa) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/PessoaEditarDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Pessoa");
            dialogStage.getIcons().add(new Image("file:resources/imagens/book.png"));
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define a pessoa no controller.
            PessoaEditarDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPessoa(pessoa);

            // Mostra a janela e espera at� o usu�rio fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public File getPessoaFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    public void setPessoaFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("EnderecoApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("EnderecoApp");
        }
    }
    
    /**
     * Carrega os dados da pessoa do arquivo especificado. A pessoa atual
     * ser� substitu�da.
     * 
     * @param file
     */
    public void loadPersonDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PessoaListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PessoaListWrapper wrapper = (PessoaListWrapper) um.unmarshal(file);

            listaPessoas.clear();
            listaPessoas.addAll(wrapper.getPessoas());

            // Save the file path to the registry.
            setPessoaFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Dialogs.create()
                    .title("Erro")
                    .masthead("N�o foi poss�vel carregar dados do arquivo:\n" 
                              + file.getPath()).showException(e);
        }
    }

    /**
     * Salva os dados da pessoa atual no arquivo especificado.
     * 
     * @param file
     */
    public void savePersonDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PessoaListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Envolvendo nossos dados da pessoa.
            PessoaListWrapper wrapper = new PessoaListWrapper();
            wrapper.setPessoas(listaPessoas);

            // Enpacotando e salvando XML  no arquivo.
            m.marshal(wrapper, file);

            // Saalva o caminho do arquivo no registro.
            setPessoaFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Dialogs.create().title("Erro")
                    .masthead("N�o foi poss�vel salvar os dados do arquivo:\n" 
                              + file.getPath()).showException(e);
        }
    }
    
    
}