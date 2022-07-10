package conexao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

@SuppressWarnings("unused")
public class DAOFrequencia {
	public void inserir(long data, int associado, int associacao) throws SQLException {
		Connection con = Conexao.getConexao();
		// insert into associacao (numero, nome) values (1, 'ADUFPI')
		String cmd = "insert into associacao.frequencia (data, associacao, associado) values (" + data + ", "
				+ associacao + ", " + associado + ")";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao.frequencia";
		System.out.println(cmd);
		Statement st = con.createStatement();
		st.executeUpdate(cmd);
		st.close();
	}
}
