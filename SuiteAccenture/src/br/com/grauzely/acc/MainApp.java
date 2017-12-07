package br.com.grauzely.acc;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.controlsfx.dialog.Dialogs;

import br.com.grauzely.acc.view.LoginAccController;
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
     
    public MainApp() {

     }
    

    @Override
    public void start(Stage primaryStage) {
        try{
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Suite Accenture");
        this.primaryStage.getIcons().add(new Image("file:resources/imagens/setup.png"));
        // Carrega o root layout do arquivo fxml.
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

    public static void main(String[] args) {
        launch(args);
    }
    
      
}