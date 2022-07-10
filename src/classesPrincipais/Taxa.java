package classesPrincipais;

public class Taxa {
	private String nome;
	private double valorAnual;
	private int quantParcelas;
	private int vigencia;
	private boolean administrativa;
	private int associacao;

	public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa) {
		setNome(nome);
		setVigencia(vigencia);
		setValorAnual(valorAno);
		setQuantParcelas(parcelas);
		setAdministrativa(administrativa);

	}

	public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa, int associacao) {
		setNome(nome);
		setVigencia(vigencia);
		setValorAnual(valorAno);
		setQuantParcelas(parcelas);
		setAdministrativa(administrativa);
		setAssociacao(associacao);

	}

	public Taxa() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValorAnual() {
		return valorAnual;
	}

	public void setValorAnual(double valorAnual) {
		this.valorAnual = valorAnual;
	}

	public int getQuantParcelas() {
		return quantParcelas;
	}

	public void setQuantParcelas(int quantParcelas) {
		this.quantParcelas = quantParcelas;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}

	public boolean isAdministrativa() {
		return administrativa;
	}

	public void setAdministrativa(boolean administrativa) {
		this.administrativa = administrativa;
	}

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}
}
