package conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import classesPrincipais.Associado;
import classesPrincipais.Reuniao;

public class DAOReuniao {
	public void inserir(Reuniao a) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		String cmd = "insert into associacao.reuniao (pauta, data,associacao) values (\'" + a.getAta() + "\', "
				+ a.getData() + "," + a.getAssociacao() + ")";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public ArrayList<Reuniao> recuperarTodosDeUmaAssociacao(int numero) throws SQLException {
		ArrayList<Reuniao> aL = new ArrayList<Reuniao>();
		ArrayList<Associado> aLassociado = new ArrayList<Associado>();

		Connection con = Conexao.getConexao();
		String cmd = "select * from associacao.reuniao where associacao = " + numero;
		System.out.println(cmd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			DAOAssociado associadoBD1 = new DAOAssociado();
			Reuniao a = new Reuniao();
			Associado associado = new Associado();
			int associacao = rs.getInt("associacao");
			long data = rs.getLong("data");
			String ata = rs.getString("pauta");
			a.setAta(ata);
			a.setAssociacao(associacao);
			a.setData(data);
			cmd = "select * from associacao.frequencia where data = " + a.getData() + " and associacao ="
					+ a.getAssociacao();
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(cmd);
			while (rs1.next()) {
				associado = associadoBD1.recuperar(rs1.getInt("associado"));
				aLassociado.add(associado);
			}
			a.setAssociadosReuniao(aLassociado);
			aL.add(a);
			st1.close();
			rs1.close();
		}
		st.close();
		rs.close();
		return aL;
	}

	public void remover(long numero) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.reuniao where data = " + numero;
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.reuniao";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public Reuniao recuperar(long numero) throws SQLException {
		ArrayList<Associado> aL = new ArrayList<Associado>();
		DAOAssociado associadoBD = new DAOAssociado();
		Associado associado = new Associado();
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "select * from associacao.reuniao where data = " + numero;
		System.out.println(cmd);
		Reuniao a = new Reuniao();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		if (rs.next()) {
			int associacao = rs.getInt("associacao");
			long data = rs.getLong("data");
			String ata = rs.getString("pauta");

			a.setAta(ata);
			a.setAssociacao(associacao);
			a.setData(data);
		}
		st.close();
		cmd = "select * from associacao.frequencia where data = " + a.getData() + " and associacao ="
				+ a.getAssociacao();
		Statement st1 = con.createStatement();
		ResultSet rs1 = st1.executeQuery(cmd);
		while (rs1.next()) {
			associado = associadoBD.recuperar(rs1.getInt("associado"));
			aL.add(associado);
		}
		a.setAssociadosReuniao(aL);
		return a;
	}

	public ArrayList<Reuniao> recuperarTodos() throws SQLException {
		ArrayList<Reuniao> aL = new ArrayList<Reuniao>();
		ArrayList<Associado> aLassociado = new ArrayList<Associado>();

		DAOAssociado associadoBD1 = new DAOAssociado();
		Connection con = Conexao.getConexao();
		// select * from associacao where numero = 2
		String cmd = "select * from associacao.associacao";
		System.out.println(cmd);

		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			Reuniao a = new Reuniao();
			Associado associado = new Associado();
			int associacao = rs.getInt("associacao");
			long data = rs.getLong("data");
			String ata = rs.getString("pauta");

			a.setAta(ata);
			a.setAssociacao(associacao);
			a.setData(data);

			cmd = "select * from associacao.frequencia where data = " + a.getData() + " and associacao ="
					+ a.getAssociacao();
			Statement st1 = con.createStatement();
			ResultSet rs1 = st1.executeQuery(cmd);
			while (rs1.next()) {
				associado = associadoBD1.recuperar(rs1.getInt("associado"));
				aLassociado.add(associado);
			}
			a.setAssociadosReuniao(aLassociado);
			aL.add(a);
			st1.close();
			rs1.close();

		}
		st.close();
		rs.close();
		return aL;
	}

}
