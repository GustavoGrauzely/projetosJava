package br.com.patchexpress.bean;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import br.com.patchexpress.domain.log;
import br.com.patchexpress.util.Util;

public class ArquivoManagerBean{

	private Util util;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormat2 = new SimpleDateFormat("MMM, dd, yyyy");
	
	public List<log> GerarAtualizacao(String origem, String destino)
			throws IOException, ParserConfigurationException, SAXException {
		this.setUtil(new Util());
		List<log> logs = new ArrayList<log>();
		// Criar Diret�rio ,SubDiret�rio, Pasta, Subpasta e copiaArquivos
		File dirRaiz = new File(origem);
		String nomeArqRaiz = dirRaiz.getName();
		String dirDestino = destino + "\\" + nomeArqRaiz;
		File dirDestinoFinal = new File(dirDestino);
		this.getUtil().criarDiretorioPasta(dirDestinoFinal, dirRaiz, logs);
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
				logs.add(this.getUtil().getLogNome());
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
				this.getUtil().setLogNome(new log());
				this.getUtil().getLogNome().setLogDescricao(
						dateFormat.format(new Date()) + ": Arquivo '" + zipFile
								+ "' descompactado...");
				logs.add(this.getUtil().getLogNome());
				// fim log
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		// Copiar um Diretorio ou Pasta
		String adapatchLogs = dirDestinoFinal + "\\adpatch_logs";
		File criarAdpatchLogs = new File(adapatchLogs);
		this.getUtil().criarPastaArquivo(criarAdpatchLogs, "patch_query.txt",	nomeArqRaiz, logs);

		this.gerarReadme(dirDestinoFinal, nomeArqRaiz);
		// inicio log
		this.getUtil().setLogNome(new log());
		this.getUtil().getLogNome().setLogDescricao(dateFormat.format(new Date()) + ": Arquivo '"+dirDestinoFinal+"\\Readme.txt");
		logs.add(this.getUtil().getLogNome());
		// fim log
		
		return logs;
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
		// Criando o conte�do do arquivo
		writer.flush();
		// Fechando conex�o e escrita do arquivo.
		writer.close();
		
	}

	public Util getUtil() {
		return util;
	}

	public void setUtil(Util util) {
		this.util = util;
	}

}
