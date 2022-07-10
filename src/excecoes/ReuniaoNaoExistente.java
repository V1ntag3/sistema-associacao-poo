package excecoes;

public class ReuniaoNaoExistente extends Exception {

	private static final long serialVersionUID = 1L;

	public ReuniaoNaoExistente() {
		super("Reuniao não existe");
	}

}
