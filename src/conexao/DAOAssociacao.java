package conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classesPrincipais.Associacao;

public class DAOAssociacao {

	public void inserir(Associacao a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		String cmd = "insert into associacao.associacao (numero, nome) values (" + a.getNumero() + ", \'" + a.getNome()
				+ "\')";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public ArrayList<Associacao> recuperarTodos() throws SQLException {
		ArrayList<Associacao> aL = new ArrayList<Associacao>();
		DAOAssociado associadoBD = new DAOAssociado();
		DAOReuniao reuniaoBD = new DAOReuniao();
		DAOTaxa taxaBD = new DAOTaxa();

		Connection con = Conexao.getConexao();
		String cmd = "select * from associacao.associacao";
		System.out.println(cmd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			Associacao a = new Associacao();
			int num = rs.getInt("numero");
			String nome = rs.getNString("nome");
			a.setAssociados(associadoBD.recuperarTodosDeUmaAssociacao(num));
			a.setReunioes(reuniaoBD.recuperarTodosDeUmaAssociacao(num));
			a.setTaxas(taxaBD.recuperarTodosDeUmaAssociacao(num));
			a.setNome(nome);
			a.setNumero(num);
			aL.add(a);
		}
		rs.close();
		st.close();
		return aL;
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.associacao";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public Associacao recuperar(int numero) throws SQLException {
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		DAOAssociado associadoBD = new DAOAssociado();
		DAOReuniao reuniaoBD = new DAOReuniao();
		DAOTaxa taxaBD = new DAOTaxa();
		String cmd = "select * from associacao.associacao where numero = " + numero;
		System.out.println(cmd);
		Associacao a = new Associacao();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			int num = rs.getInt("numero");
			String nome = rs.getString("nome");
			a.setAssociados(associadoBD.recuperarTodosDeUmaAssociacao(num));
			a.setReunioes(reuniaoBD.recuperarTodosDeUmaAssociacao(num));
			a.setTaxas(taxaBD.recuperarTodosDeUmaAssociacao(num));
			a = new Associacao(num, nome);
		}
		st.close();
		return a;
	}

	public void alterar(Associacao a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		remover(a.getNumero());
		String cmd = "insert into associacao.associacao (numero, nome) values ( \'" + a.getNumero() + ", \'"
				+ a.getNome() + "\')";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void remover(int numero) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.associacao where numero = " + numero;
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

}
