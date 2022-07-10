package excecoes;

public class AssociadoNaoExistente extends Exception {

	private static final long serialVersionUID = 1L;

	public AssociadoNaoExistente() {
		super("Associado não existe");
	}
}
