package conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classesPrincipais.Pagamento;

public class DAOPagamento {
	public void inserir(Pagamento p) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		String cmd = "insert into associacao.pagamento (data, valor, associado, vigencia ,taxa, associacao) values ("
				+ p.getData() + "," + p.getValor() + "," + p.getAssociado() + "," + p.getVigencia() + ",'"
				+ p.getTipo().getNome() + "'," + p.getAssociacao() + ")";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public ArrayList<Pagamento> recuperarTodosDeUmAssociado(int numero, int associacao) throws SQLException {
		ArrayList<Pagamento> aL = new ArrayList<Pagamento>();
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "select * from associacao.pagamento where associado = " + numero + " and associacao = "
				+ associacao;
		System.out.println(cmd);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);

		while (rs.next()) {
			DAOTaxa taxaBD = new DAOTaxa();
			Pagamento a = new Pagamento();
			long data = rs.getLong("data");
			double valor = rs.getDouble("valor");
			int associacao2 = rs.getInt("associacao");
			int associado = rs.getInt("associado");
			String taxa = rs.getString("taxa");
			int vigencia = rs.getInt("vigencia");
			a.setAssociacao(associacao2);
			a.setAssociado(associado);
			a.setData(data);
			a.setTipo(taxaBD.recuperar(taxa, vigencia));
			a.setValor(valor);
			a.setVigencia(vigencia);

			aL.add(a);

		}
		st.close();
		rs.close();
		return aL;
	}

	public void alterar(Pagamento p) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		remover(p);
		String cmd = "insert into associacao.pagamento (data, valor, associado, vigencia ,taxa, associacao) values ("
				+ p.getData() + "," + p.getValor() + "," + p.getAssociado() + "," + p.getVigencia() + ",'"
				+ p.getTipo().getNome() + "'," + p.getAssociacao() + ")";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void remover(Pagamento p) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.pagamento where data = " + p.getData() + " and " + "valor = "
				+ p.getValor() + " and " + "associado = " + p.getAssociado() + " and " + "vigencia =" + p.getVigencia()
				+ " and " + "taxa = \'" + p.getTipo().getNome() + " \' and " + "associacao = " + p.getAssociacao();
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.pagamento";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public Pagamento recuperar(Pagamento p) throws SQLException {
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "delete from associacao.pagamento where data = " + p.getData() + " and " + "valor = "
				+ p.getValor() + " and " + "associado = " + p.getAssociado() + " and " + "vigencia =" + p.getVigencia()
				+ " and " + "taxa = \'" + p.getTipo().getNome() + " \' and " + "associacao = " + p.getAssociacao();
		System.out.println(cmd);
		DAOTaxa taxaBD = new DAOTaxa();
		Pagamento a = new Pagamento();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			long data = rs.getLong("data");
			double valor = rs.getInt("valor");
			int associacao = rs.getInt("associacao");
			int associado = rs.getInt("associado");
			String taxa = rs.getString("taxa");
			int vigencia = rs.getInt("vigencia");
			a.setAssociacao(associacao);
			a.setAssociado(associado);
			a.setData(data);
			a.setTipo(taxaBD.recuperar(taxa, vigencia));
			a.setValor(valor);
			a.setVigencia(vigencia);
		}
		st.close();
		return a;
	}

	public ArrayList<Pagamento> recuperarTodos() throws SQLException {
		ArrayList<Pagamento> aL = new ArrayList<Pagamento>();
		DAOTaxa taxaBD = new DAOTaxa();
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "select * from associacao.pagamento";
		System.out.println(cmd);
		Pagamento a = new Pagamento();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			long data = rs.getLong("data");
			double valor = rs.getInt("valor");
			int associacao = rs.getInt("associacao");
			int associado = rs.getInt("associado");
			String taxa = rs.getString("taxa");
			int vigencia = rs.getInt("vigencia");
			a.setAssociacao(associacao);
			a.setAssociado(associado);
			a.setData(data);
			a.setTipo(taxaBD.recuperar(taxa, vigencia));
			a.setValor(valor);
			a.setVigencia(vigencia);

			aL.add(a);
		}
		st.close();
		return aL;
	}

}
