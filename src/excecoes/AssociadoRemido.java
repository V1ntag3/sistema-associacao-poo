package excecoes;

import classesPrincipais.Associado;

public class AssociadoRemido extends Associado {

	public AssociadoRemido(int i, String string, String string2, long data1, long nasc, long remissao) {
		setNumero(i);
		setNome(string);
		setTelefone(string);
		setNascimento(nasc);
		setDataAssociacao(data1);
		setRemissao(remissao);
	}

	public AssociadoRemido(int num, String nome, String telefone, long dataAssociacao, long nasc, long remissao,
			int associacao) {
		// TODO Auto-generated constructor stub
		setNumero(num);
		setNome(nome);
		setTelefone(telefone);
		setNascimento(nasc);
		setDataAssociacao(dataAssociacao);
		setRemissao(remissao);
		setAssociacao(associacao);
	}

	public void setRemissao(long remissao) {
		this.remissao = remissao;
	}

}
