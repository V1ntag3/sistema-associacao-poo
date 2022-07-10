package excecoes;

public class TaxaJaExistente extends Exception {
	private static final long serialVersionUID = 1L;

	public TaxaJaExistente() {
		super("A taxa já existe");
	}
}
