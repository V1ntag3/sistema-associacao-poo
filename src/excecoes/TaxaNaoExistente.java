package excecoes;

public class TaxaNaoExistente extends Exception {

	private static final long serialVersionUID = 1L;

	public TaxaNaoExistente() {
		super("Taxa não existente");
	}
}
