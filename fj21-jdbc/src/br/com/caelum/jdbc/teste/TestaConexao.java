package br.com.caelum.jdbc.teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.dao.ContatoDao;
import br.com.caelum.jdbc.model.Contato;

public class TestaConexao {

	public static void main(String[] args) throws SQLException {
		// pronto para gravar
		Contato contato1 = new Contato();
		contato1.setId(2L);
		contato1.setNome("Caelum2");
		contato1.setEmail("contato2@caelum.com.br");
		contato1.setEndereco("R. Vergueiro 3185 cj57");
		contato1.setDataNascimento(Calendar.getInstance());

		// grave nessa conex�o!!!
		ContatoDao dao1 = new ContatoDao();

		// m�todo elegante
		dao1.adiciona(contato1);
		
		ContatoDao dao = new ContatoDao();
		List<Contato> contatos = dao.getLista();
		for (Contato contato : contatos) {
			System.out.println("Nome: " + contato.getNome());
			System.out.println("Email: " + contato.getEmail());
			System.out.println("Endere�o: " + contato.getEndereco());
			System.out.println("Data de Nascimento: " +
			contato.getDataNascimento().getTime() + "\n");
			}

	}

}
