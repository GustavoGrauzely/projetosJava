package br.com.caelum.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.jdbc.ConnectionFactory;
import br.com.caelum.jdbc.model.Contato;
import br.com.caelum.jdbc.model.Funcionario;

public class FuncionarioDao {

	private Connection conn;

	public FuncionarioDao() {
		this.conn = new ConnectionFactory().getConnection();
	}

	public void adiciona(Funcionario funcionario) {
		String sql = "insert into funcionarios "
				+ "(id,nome,usuario,senha)"
				+ " values (?,?,?,?)";

		try {
			// prepared statement para inser��o
			PreparedStatement stmt = conn.prepareStatement(sql);

			// seta os valores
			stmt.setLong(1, funcionario.getId());
			stmt.setString(2, funcionario.getNome());
			stmt.setString(3, funcionario.getUsuario());
			stmt.setString(4, funcionario.getSenha());

			// executa
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new DAOException(e);
		}

	}

	public List<Funcionario> getLista() {
		try {
			List<Funcionario> funcionarios = new ArrayList<Funcionario>();

			PreparedStatement stmt = this.conn
					.prepareStatement("Select * from funcionarios");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto Contato
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getLong("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setUsuario(rs.getString("usuario"));
				funcionario.setSenha(rs.getString("senha"));
				funcionarios.add(funcionario);
			}
			rs.close();
			stmt.close();
			return funcionarios;
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	public void altera(Funcionario funcionario) {
		String sql = "update funcionarios set nome=?, usuario=?, senha=?"
				+ " where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getUsuario());
			stmt.setString(3, funcionario.getSenha());
			stmt.setLong(4, funcionario.getId());
		    stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remove(Funcionario funcionario) {
		try {
			PreparedStatement stmt = conn.prepareStatement("delete "+ "from funcionarios where id=?");
			stmt.setLong(1, funcionario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}
