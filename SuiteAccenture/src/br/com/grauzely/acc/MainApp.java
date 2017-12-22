package br.com.grauzely.acc;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import br.com.grauzely.acc.controller.CadastrarAccController;
import br.com.grauzely.acc.controller.LoginAccController;
import br.com.grauzely.acc.controller.MenuAccController;
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
    private AnchorPane loginAcc;
    private AnchorPane cadastrarAcc;
    private AnchorPane menuAcc;
    public Stage menuStage = new Stage(); 
    
    public MainApp() {

     }
    

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Suite Accenture");
        this.primaryStage.getIcons().add(new Image("file:resources/imagens/setup.png"));
        this.telaLogin();
    }
    
    /**
     * Mostra o person overview dentro do root layout.
     */
    public void telaLogin() {
    	try {
    		// Carrega o login layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/LoginAcc.fxml"));
            loginAcc = (AnchorPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(loginAcc);
            primaryStage.setScene(scene);

            // Dá ao controller o acesso ao main app.
            LoginAccController controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void cadastrarLogin() {
    	try {
    		 // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/cadastrarAcc.fxml"));
            cadastrarAcc = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage cadastrarLoginStage = new Stage();
            cadastrarLoginStage.setTitle("Cadastrar Usuário");
            cadastrarLoginStage.getIcons().add(new Image("file:resources/imagens/setup.png"));
            cadastrarLoginStage.initModality(Modality.WINDOW_MODAL);
            cadastrarLoginStage.initOwner(primaryStage);
            Scene scene = new Scene(cadastrarAcc);
            cadastrarLoginStage.setScene(scene);

            // Define a pessoa no controller.
            CadastrarAccController controller = loader.getController();
            controller.setDialogStage(cadastrarLoginStage);
  
            // Mostra a janela e espera até o usuário fechar.
            cadastrarLoginStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void abrirMenu(){
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(MainApp.class.getResource("view/MenuAcc.fxml"));
            menuAcc = (AnchorPane) loader.load();
    		
         // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(menuAcc);
            menuStage.setTitle("Suite Optimizer");
            menuStage.getIcons().add(new Image("file:resources/imagens/setup.png"));
            menuStage.setScene(scene);
            
         // Dá ao controller o acesso ao main app.
            MenuAccController controller = loader.getController();
            controller.setMainApp(this);
            menuStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }
    
      
}