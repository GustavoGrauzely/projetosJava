import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.HashSet;
import java.util.LinkedHashSet;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class testarMetodos {

	/*
	 * public static void copiarArquivos(File origem,File destino) throws
	 * IOException{ FileInputStream fisOrigem = new FileInputStream(origem);
	 * FileOutputStream fisDestino = new FileOutputStream(destino); FileChannel
	 * fcOrigem = fisOrigem.getChannel(); FileChannel fcDestino =
	 * fisDestino.getChannel(); fcOrigem.transferTo(0, fcOrigem.size(),
	 * fcDestino); fisOrigem.close(); fisDestino.close(); }
	 * 
	 * public static void copiarArquivosExt(File origem,File destino) throws
	 * IOException{ File[] arqOrigem = origem.listFiles(); for (File arq :
	 * arqOrigem) { if (arq.isDirectory()){ copiarArquivosExt(arq, destino);
	 * }else{ if(arq.getName().toLowerCase().endsWith(".xml")){ File xgeq = new
	 * File(destino+"\\xgeq"); xgeq.mkdir(); File mds = new File(xgeq+"\\mds");
	 * mds.mkdir(); File xml = new File(mds+"\\"+arq.getName());
	 * copiarArquivos(arq, xml);
	 * System.out.println("Copiando arquivos .xml: "+arq.getName()); } } } }
	 */

	public static void main(String[] args) throws IOException,
			ParserConfigurationException, SAXException {
		
		LinkedHashSet <String> set = new LinkedHashSet <String>();
		 
		boolean[] inseriu = new boolean[4];
		 
		inseriu[0] = set.add("Rodrigo"); //adicionando "Rodrigo"
		inseriu[1] = set.add("Carlos");  //adicionando "Carlos"
		inseriu[2] = set.add("Maria");   //adicionando "Maria""
		inseriu[3] = set.add("Rodrigo"); // N�o ser� adicionado "Rodrigo" pois j� existe na cole��o
		         
		//a saida deste la�o ser� true, true, true, false mostrando que o �ltimo add n�o foi funcionou 
		for(int i = 0 ; i < inseriu.length ; i++){
		    System.out.println( inseriu[i] );
		}
		         
		//percorrendo o conjunto com o "for aprimorado"
		for(String nome : set){
		    System.out.println(nome);
		}
		//sa�da foi Maria, Carlos, Rodrigo 

		/*
		 * String Origem = "C:\\12GEQ_SPCE043_EBS_001"; String Destino =
		 * "C:\\Destino"; File dirRaiz = new File(Origem); String nomeArq =
		 * dirRaiz.getName(); File inFolder = new File(Destino + "\\" +
		 * nomeArq); System.out.print(inFolder);
		 */

		/*
		 * File diretorioOri = new
		 * File("C:\\AmbienteTeste\\12GEQ_SPCE043_EBS_001"); File diretorioDes =
		 * new File("C:\\AmbienteTeste\\Destino");
		 * copiarArquivosExt(diretorioOri, diretorioDes);
		 */

		/*
		 * BufferedReader br = new BufferedReader( new FileReader(
		 * "C:\\Users\\g.da.silva.alves\\Downloads\\12GEQ_SPCE043_EBS_001\\xgeq\\forms\\PTB\\GEQRICTRLPGLP.fmb"
		 * )); String versao = ""; while (br.ready()) { String linha =
		 * br.readLine(); if (linha.contains("$Header")) { String[] header =
		 * linha.split(" "); for (int i = 0; i < header.length; i++) { if
		 * (header[i].contains("120")) { versao = header[i]; } } break; } }
		 * br.close(); System.out.print(versao);
		 */
		/*
		 * String nomeArq = "12GEQ_SPCE043_EBS_001"; String[] separado =
		 * nomeArq.split("_"); String parametroArq =
		 * separado[1]+"_"+separado[separado.length - 1]; String path =
		 * "C:\\AmbienteTeste\\Destino\\12GEQ_SPCE043_EBS_001\\teste.txt"; File
		 * arquivo = new File(path); arquivo.createNewFile(); long begin =
		 * System.currentTimeMillis(); BufferedWriter writer = new
		 * BufferedWriter(new FileWriter(arquivo)); writer.write(
		 * "SQL> SELECT patch_name,creation_date FROM ad_applied_patches WHERE PATCH_NAME like '%121"
		 * + parametroArq + "%'"); writer.newLine();
		 * writer.write("2  ORDER BY 1;"); writer.newLine();
		 * writer.write("PATCH_NAME           CREATION_DATE"); writer.newLine();
		 * writer.write("==================== ===============");
		 * writer.newLine(); writer.newLine(); writer.write("SQL> spool off");
		 * //Criando o conte�do do arquivo writer.flush(); //Fechando conex�o e
		 * escrita do arquivo. writer.close();
		 * System.out.println("Arquivo gravado em: " + path);
		 */

	}

}