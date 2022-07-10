package conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classesPrincipais.Taxa;

public class DAOTaxa {
	public void inserir(Taxa a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		String cmd = "insert into associacao.taxa (nome, vigencia, valor, parcelas, associacao, administrativa) values ("
				+ "\'" + a.getNome() + "\', " + a.getVigencia() + ", " + a.getValorAnual() + ", " + a.getQuantParcelas()
				+ ", " + a.getAssociacao();
		if (a.isAdministrativa() == false)
			cmd = cmd + ", 0)";
		else {
			cmd = cmd + ",1)";
		}
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void alterar(Taxa a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		remover(a.getNome(), a.getVigencia());
		String cmd = "insert into associacao.taxa (nome, vigencia, valor, parcelas, associacao, administrativa) values ("
				+ "\'" + a.getNome() + "\', " + a.getVigencia() + ", " + a.getValorAnual() + ", " + a.getQuantParcelas()
				+ "," + a.getAssociacao();
		if (a.isAdministrativa() == false)
			cmd = cmd + "0)";
		else {
			cmd = cmd + "1)";
		}
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void remover(String nome, int vigencia) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.taxa where nome = \'" + nome + "\' and " + "vigencia = " + vigencia;
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.taxa";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public Taxa recuperar(String nome, int vigencia) throws SQLException {
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "select * from associacao.taxa where nome = \'" + nome + "\' and " + "vigencia = " + vigencia;
		System.out.println(cmd);
		Taxa a = null;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			String nome1 = rs.getString("nome");
			int vigencia1 = rs.getInt("vigencia");
			double valor = rs.getDouble("valor");
			int parcelas = rs.getInt("parcelas");
			int associacao = rs.getInt("associacao");
			boolean ad = rs.getBoolean("administrativa");
			if (ad == false) {
				a = new Taxa(nome1, vigencia1, valor, parcelas, false, associacao);
			} else {
				a = new Taxa(nome1, vigencia1, valor, parcelas, true, associacao);
			}
		}
		rs.close();
		st.close();
		return a;
	}

	public ArrayList<Taxa> recuperarTodos() throws SQLException {
		ArrayList<Taxa> aL = new ArrayList<Taxa>();
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "select * from associacao.taxa";
		System.out.println(cmd);
		Taxa a = null;
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			String nome1 = rs.getString("nome");
			int vigencia1 = rs.getInt("vigencia");
			double valor = rs.getDouble("valor");
			int parcelas = rs.getInt("parcelas");
			int associacao = rs.getInt("associacao");

			if (rs.getInt("administrativo") == 0) {
				a = new Taxa(nome1, vigencia1, valor, parcelas, false, associacao);
			} else {
				a = new Taxa(nome1, vigencia1, valor, parcelas, true, associacao);
			}
			aL.add(a);
		}
		st.close();
		rs.close();
		return aL;
	}

	public ArrayList<Taxa> recuperarTodosDeUmaAssociacao(int numero) throws SQLException {

		ArrayList<Taxa> aL = new ArrayList<Taxa>();
		Connection con = Conexao.getConexao();
		Statement st = con.createStatement();
		String cmd = "select * from associacao.taxa where associacao = " + numero;
		System.out.println(cmd);
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			Taxa a = new Taxa();
			String nome1 = rs.getString("nome");
			int vigencia1 = rs.getInt("vigencia");
			double valor = rs.getDouble("valor");
			int parcelas = rs.getInt("parcelas");
			int associacao = rs.getInt("associacao");
			boolean ad = rs.getBoolean("administrativa");
			if (ad == false) {
				a.setAdministrativa(ad);
				a.setAssociacao(associacao);
				a.setNome(nome1);
				a.setQuantParcelas(parcelas);
				a.setValorAnual(valor);
				a.setVigencia(vigencia1);
			} else {
				a.setAdministrativa(true);
				a.setAssociacao(associacao);
				a.setNome(nome1);
				a.setQuantParcelas(parcelas);
				a.setValorAnual(valor);
				a.setVigencia(vigencia1);
			}
			aL.add(a);
		}

		st.close();
		rs.close();
		return aL;
	}
}
