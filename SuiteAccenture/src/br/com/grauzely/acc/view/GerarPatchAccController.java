package br.com.grauzely.acc.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import br.com.grauzely.acc.MainApp;
import br.com.grauzely.acc.model.Log;
import br.com.grauzely.acc.model.ServiceRequests;
import br.com.grauzely.acc.util.Util;

public class GerarPatchAccController implements Initializable {

	@FXML
	private TextField origem;
	@FXML
	private TextField destino;
	@FXML
	private Button btnOrigem;
	@FXML
	private Button btnDestino;
	@FXML
	private ListView<String> listaViewLogs;
	@FXML
	private ProgressBar barra;
	@FXML
	private Button btnGerarPatch;
	@FXML
	private Button btnResetar;

	private MainApp mainApp;
	private Stage dialogOraclePatchStage;
	ObservableList<String> listaLogs = FXCollections.observableArrayList();
	List<Log> listaLogControle = new ArrayList<Log>();
	private Util util;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM, dd, yyyy");
	

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	public void setDialogOraclePatchStage(Stage dialogOraclePatchStage) {
		this.dialogOraclePatchStage = dialogOraclePatchStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		barra.setProgress(0);

	}

	public void buscarOrigem() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Escolha um diretório de origem...");
		File file = directoryChooser.showDialog(null);
		if (file != null) {
			origem.setText(file.getAbsolutePath());
		}
	}

	public void buscarDestino() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Escolha um diretório de destino...");
		File file = directoryChooser.showDialog(null);
		if (file != null) {
			destino.setText(file.getAbsolutePath());
		}
	}

	public void gerarPatch() throws IOException, ParserConfigurationException,
			SAXException, InterruptedException {
		File dirOrigem = new File(origem.getText());
		File dirDestino = new File(destino.getText());
		if (dirOrigem.exists()) {
			if (!dirDestino.exists()) {
				dirDestino.mkdir();
			}
			if (!dirOrigem.toString().equalsIgnoreCase(dirDestino.toString())) {
				listaLogControle = GerarAtualizacao(origem.getText(), destino.getText());
				double cont = 0.0;
				int i = 1;
				// verificar
				for (Log log : listaLogControle){
					listaLogs.add(log.getLogDescricao());
					listaViewLogs.setItems(listaLogs);
					cont = (1*i)/listaLogControle.size();
					i++;
					barra.setProgress(cont);
				}
				btnGerarPatch.setDisable(true);
				infoBox("Patch gerado com sucesso!", "Atenção!", null);
			} else {
				infoBox("Por favor digite diretórios diferentes", "Atenção!", null);
			}
		} else {
			if (!dirOrigem.exists()) {
				infoBox("Diretório origem não existe", "Atenção!", null);
			}
		}
	}
	
	public void resetar() {
		origem.setText("");
		destino.setText("");
		listaViewLogs.setItems(null);
		barra.setProgress(0);
		btnGerarPatch.setDisable(false);
	}
	
	public void barraProgresso() {
		for (int i = 0; i <= 100; i++ ){
			barra.setProgress(i);
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
	
	public List<Log> GerarAtualizacao(String origem, String destino)
			throws IOException, ParserConfigurationException, SAXException {
		this.setUtil(new Util());
		List<Log> Logs = new ArrayList<Log>();
		// Criar Diretório ,SubDiretório, Pasta, Subpasta e copiaArquivos
		File dirRaiz = new File(origem);
		String nomeArqRaiz = dirRaiz.getName();
		String dirDestino = destino + "\\" + nomeArqRaiz;
		File dirDestinoFinal = new File(dirDestino);
		this.getUtil().criarDiretorioPasta(dirDestinoFinal, dirRaiz, Logs);
		// busca de zip na origem
		File diretorio = new File(origem);
		File[] listarPastas = diretorio.listFiles(new FileFilter() {
			public boolean accept(File nomeZip) {
				return nomeZip.getName().endsWith(".zip");
			}
		});
		if (listarPastas.length != 0) {
			String nomeArqZip = listarPastas[0].getName();
			String dirOrigemZip = origem + "\\" + nomeArqZip;
			String dirDestinoZip = dirDestinoFinal + "\\" + nomeArqZip;
			// lista os aquivos .zip
			// for(File f : listFiles) {
			// System.out.println(f.getName());
			// }
			// Copiar um Arquivo
			File inFoldercopiar = new File(dirOrigemZip);
			File outFoldercopiar = new File(dirDestinoZip);
			try {
				this.getUtil().copyArquivo(inFoldercopiar, outFoldercopiar);
				// inicio log
				this.getUtil().logAtual(outFoldercopiar);
				Logs.add(this.getUtil().getLogNome());
				// fim log
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Descompactar arquivo Zipado
			File zipFile = new File(dirDestinoZip);
			zipFile.exists();
			File dir = new File(dirDestino);
			dir.isDirectory();
			try {
				this.getUtil().unzip(zipFile, dir);
				// inicio log
				this.getUtil().setLogNome(new Log());
				this.getUtil().getLogNome().setLogDescricao(
						dateFormat.format(new Date()) + ": Arquivo '" + zipFile
								+ "' descompactado...");
				Logs.add(this.getUtil().getLogNome());
				// fim log
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Copiar um Diretorio ou Pasta
		String adapatchLogs = dirDestinoFinal + "\\adpatch_logs";
		File criarAdpatchLogs = new File(adapatchLogs);
		this.getUtil().criarPastaArquivo(criarAdpatchLogs, "patch_query.txt",	nomeArqRaiz, Logs);

		this.gerarReadme(dirDestinoFinal, nomeArqRaiz);
		// inicio log
		this.getUtil().setLogNome(new Log());
		this.getUtil().getLogNome().setLogDescricao(dateFormat.format(new Date()) + ": Arquivo '"+dirDestinoFinal+"\\Readme.txt");
		Logs.add(this.getUtil().getLogNome());
		// fim log
		
		return Logs;
	}
	
	public void gerarReadme(File dirDestinoFinal, String nomeArqRaiz) throws IOException{
		File readme = new File(dirDestinoFinal+"\\Readme.txt");
		readme.createNewFile();
		// escrever SQL no arquivo
		String[] separado = nomeArqRaiz.split("_");
		String parametroArq = separado[1] + "_" + separado[separado.length - 1];
		BufferedWriter writer = new BufferedWriter(new FileWriter(readme));
		writer.write("==============================================================================");
		writer.newLine();
		writer.write("Atualizacao - "+dirDestinoFinal.getName());
		writer.newLine();
		writer.write("Produto     - XGEQ");
		writer.newLine();
		writer.write("Data        - "+dateFormat2.format(new Date()));
		writer.newLine();
		writer.write("HotPatch    - S");
		writer.newLine();
		writer.write("Forncedor   - Grupo Edson Queiroz");
		writer.newLine();
		writer.write("Descricao   - ");
		writer.newLine();
		writer.newLine();
		writer.write("Instrucoes para aplicar este patch");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("Tarefas preparatorias");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("Tarefas pre-instalacao");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.write("Aplicacao do patch "+dirDestinoFinal.getName()+".zip");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("Aplicacao do Patch");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("Tarefas pos-instalacao");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("Instrucoes");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.newLine();
		writer.write("1. Aplique o patch");
		writer.newLine();
		writer.write("Este patch contem o seguinte driver que deve ser aplicado com o AutoPatch:");
		writer.newLine();
		writer.write("u121"+parametroArq+".drv");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("Informacoes Adicionais");
		writer.newLine();
		writer.write("==============================================================================");
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.newLine();
		writer.write("==============================================================================");
		// Criando o conteúdo do arquivo
		writer.flush();
		// Fechando conexão e escrita do arquivo.
		writer.close();
		
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}


}
