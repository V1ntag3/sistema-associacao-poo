package excecoes;

public class AssociadoJaRemido extends Exception {

	private static final long serialVersionUID = 1L;

	public AssociadoJaRemido() {
		super("Associado j� � remido");
	}
}
