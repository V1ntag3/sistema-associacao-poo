package excecoes;

public class FrequenciaJaRegistrada extends Exception {

	private static final long serialVersionUID = 1L;

	public FrequenciaJaRegistrada() {
		super("Frequ�ncia j� foi registrada");
	}
}
