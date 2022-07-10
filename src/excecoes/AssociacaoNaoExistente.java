package excecoes;

public class AssociacaoNaoExistente extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AssociacaoNaoExistente() {
		super("Associação não existe");
	}
}
