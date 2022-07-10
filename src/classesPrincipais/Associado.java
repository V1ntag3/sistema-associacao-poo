package classesPrincipais;

import java.util.ArrayList;

public class Associado {
	protected int numero;
	protected String nome;
	protected String telefone;
	protected long nascimento;
	protected long dataAssociacao;
	protected long remissao;
	protected int associacao;
	protected ArrayList<Pagamento> pags = new ArrayList<Pagamento>();

	public Associado(int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
		setNumero(numero);
		setNome(nome);
		setTelefone(telefone);
		setNascimento(nascimento);
		setDataAssociacao(dataAssociacao);
	}

	public Associado(int num, String nome, String telefone, long dataAssociacao, long nasc, int associacao) {
		// TODO Auto-generated constructor stub
		setNumero(num);
		setNome(nome);
		setTelefone(telefone);
		setNascimento(nasc);
		setDataAssociacao(dataAssociacao);
		setAssociacao(associacao);
	}

	public Associado() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public long getNascimento() {
		return nascimento;
	}

	public void setNascimento(long nascimento) {
		this.nascimento = nascimento;
	}

	public long getDataAssociacao() {
		return dataAssociacao;
	}

	public void setDataAssociacao(long dataAssociacao) {
		this.dataAssociacao = dataAssociacao;
	}

	public long getRemissao() {
		return remissao;
	}

	public ArrayList<Pagamento> getPags() {
		return pags;
	}

	public void setPags(ArrayList<Pagamento> pags) {
		this.pags = pags;
	}

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}
}
