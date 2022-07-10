package excecoes;

public class AssociadoJaExistente extends Exception {
	private static final long serialVersionUID = 1L;

	public AssociadoJaExistente() {
		super("Associado já existe");
	}
}
