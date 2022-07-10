package classesPrincipais;

public class Pagamento {
	private Taxa tipo;
	private double valor;
	private long data;
	private int associado;
	private int associacao;
	private int vigencia;

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public Taxa getTipo() {
		return tipo;
	}

	public void setTipo(Taxa tipo) {
		this.tipo = tipo;
	}

	public int getAssociado() {
		return associado;
	}

	public void setAssociado(int associado) {
		this.associado = associado;
	}

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}

	public int getVigencia() {
		return vigencia;
	}

	public void setVigencia(int vigencia) {
		this.vigencia = vigencia;
	}
}
