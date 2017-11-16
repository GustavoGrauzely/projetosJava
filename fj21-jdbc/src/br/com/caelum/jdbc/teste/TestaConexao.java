package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.dao.FuncionarioDao;
import br.com.caelum.jdbc.model.Contato;
import br.com.caelum.jdbc.model.Funcionario;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		// pronto para gravar
		Contato contato1 = new Contato();
		contato1.setId(2L);
		contato1.setNome("Alura");
		contato1.setEmail("contato2@alura.com.br");
		contato1.setEndereco("R. Rio 3185 cj57");
		contato1.setDataNascimento(Calendar.getInstance());

		// grave nessa conexão!!!
		//ContatoDao daoAdicionar = new ContatoDao();

		// método elegante
		//daoAdicionar.adiciona(contato1);
		
		// alterar nessa conexão!!!
		//ContatoDao daoAlterar = new ContatoDao();

		// método elegante
		//daoAlterar.altera(contato1);
		
		// remover nessa conexão!!!
		//ContatoDao daoRemover = new ContatoDao();

		// método elegante
		//daoRemover.remove(contato1);
		
		ContatoDao dao = new ContatoDao();
		List<Contato> contatos = dao.getLista();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (Contato contato : contatos) {
			System.out.println("Nome: " + contato.getNome());
			System.out.println("Email: " + contato.getEmail());
			System.out.println("Endereço: " + contato.getEndereco());
			String dataFormatada = sdf.format(contato.getDataNascimento().getTime());
			System.out.println("Data de Nascimento: " + dataFormatada + "\n");
			}

		// Funcionarios
		// pronto para gravar
				Funcionario funcionario = new Funcionario();
				funcionario.setId(2L);
				funcionario.setNome("Gustavo");
				funcionario.setUsuario("GustavoGrauzely");
				funcionario.setSenha("12345678");
			
				// grave nessa conexão!!!
				//FuncionarioDao daoAdicionar = new FuncionarioDao();

				// método elegante
				//daoAdicionar.adiciona(funcionario);
				
				// alterar nessa conexão!!!
				//FuncionarioDao daoAlterar = new FuncionarioDao();

				// método elegante
				//daoAlterar.altera(funcionario);
				
				// remover nessa conexão!!!
				FuncionarioDao daoRemover = new FuncionarioDao();

				// método elegante
				daoRemover.remove(funcionario);
				
				FuncionarioDao daoFunc = new FuncionarioDao();
				List<Funcionario> funcionarios = daoFunc.getLista();
				for (Funcionario funcionario1 : funcionarios) {
					System.out.println("Nome: " + funcionario1.getNome());
					System.out.println("Usuário: " + funcionario1.getUsuario());
					System.out.println("Senha: " + funcionario1.getSenha());
					}
			
	}

}
