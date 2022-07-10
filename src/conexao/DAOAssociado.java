package conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classesPrincipais.Associado;
import excecoes.AssociadoRemido;

public class DAOAssociado {

	public void inserir(Associado a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		String cmd = "insert into associacao.associado (numero, nome, nascimento, telefone, data_associacao, associacao, discriminador, data_remissao) values ("
				+ a.getNumero() + ", \'" + a.getNome() + "\'" + "," + a.getNascimento() + ",'" + a.getTelefone() + "',"
				+ a.getDataAssociacao() + "," + a.getAssociacao() + ",";
		if (a instanceof AssociadoRemido) {
			cmd = cmd + 1 + "," + a.getRemissao() + ")";
		} else {
			cmd = cmd + 0 + "," + 0 + ")";
		}

		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public ArrayList<Associado> recuperarTodosDeUmaAssociacao(int numero) throws SQLException {
		ArrayList<Associado> aL = new ArrayList<Associado>();
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "select * from associacao.associado where associacao = " + numero;
		System.out.println(cmd);

		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			Associado a = new Associado();
			DAOPagamento pagamentoBD = new DAOPagamento();
			int num = rs.getInt("numero");
			String nome = rs.getString("nome");
			String telefone = rs.getString("telefone");
			long nasc = rs.getLong("nascimento");
			long dataAssociacao = rs.getLong("data_associacao");
			long remissao = rs.getLong("data_remissao");
			boolean discriminador = rs.getBoolean("discriminador");
			int associacao = rs.getInt("associacao");
			if (discriminador == false) {
				a = new Associado(num, nome, telefone, dataAssociacao, nasc, associacao);
			} else {
				a = new AssociadoRemido(num, nome, telefone, dataAssociacao, nasc, remissao, associacao);
			}
			a.setPags(pagamentoBD.recuperarTodosDeUmAssociado(num, associacao));
			aL.add(a);
		}
		st.close();
		rs.close();
		return aL;
	}

	public Associado recuperar(int n) throws SQLException {
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		DAOPagamento pagamentoBD = new DAOPagamento();
		String cmd = "select * from associacao.associado where numero = " + n;
		System.out.println(cmd);
		Associado a = new Associado();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			int num = rs.getInt("numero");
			String nome = rs.getString("nome");
			String telefone = rs.getString("telefone");
			long nasc = rs.getLong("nascimento");
			long dataAssociacao = rs.getLong("data_associacao");
			long remissao = rs.getLong("data_remissao");
			boolean discriminador = rs.getBoolean("discriminador");
			int associacao = rs.getInt("associacao");
			if (discriminador == false) {
				a = new Associado(num, nome, telefone, dataAssociacao, nasc, associacao);
			} else {
				a = new AssociadoRemido(num, nome, telefone, dataAssociacao, nasc, remissao, associacao);
			}
			a.setPags(pagamentoBD.recuperarTodosDeUmAssociado(num, associacao));
		}
		st.close();
		rs.close();
		return a;

	}

	public void alterar(Associado a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		remover(a.getNumero());
		String cmd = "insert into associacao.associado (numero, nome,nascimento,telefone,data_associacao,data_remissao,associacao,discriminador) values ("
				+ a.getNumero() + ", \'" + a.getNome() + "\'" + "," + a.getNascimento() + "," + a.getTelefone() + ","
				+ a.getDataAssociacao() + "," + a.getRemissao() + "," + a.getAssociacao();
		if (a instanceof AssociadoRemido) {
			cmd = cmd + 1 + ")";
		} else {
			cmd = cmd + 0 + ")";
		}
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void remover(int n) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.associado where numero = " + n;
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.associado";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

}
