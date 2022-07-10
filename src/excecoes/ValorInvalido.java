package excecoes;

public class ValorInvalido extends Exception {
	private static final long serialVersionUID = 1L;

	public ValorInvalido() {
		super("O que valor cadastrado é invalido");
	}
}