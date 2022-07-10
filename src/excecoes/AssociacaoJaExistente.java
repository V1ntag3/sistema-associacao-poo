package excecoes;

public class AssociacaoJaExistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssociacaoJaExistente() {
		super("Associação já existe");
	}
}
