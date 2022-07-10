package excecoes;

public class ReuniaoJaExistente extends Exception {

	private static final long serialVersionUID = 1L;

	public ReuniaoJaExistente() {
		super("Reuniao já existe");
	}
}
