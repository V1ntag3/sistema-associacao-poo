package excecoes;

public class AssociadoJaRemido extends Exception {

	private static final long serialVersionUID = 1L;

	public AssociadoJaRemido() {
		super("Associado já é remido");
	}
}
