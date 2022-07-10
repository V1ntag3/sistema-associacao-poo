package excecoes;

public class FrequenciaIncompativel extends Exception {

	private static final long serialVersionUID = 1L;

	public FrequenciaIncompativel() {
		super("Frequência é incompatível");
	}
}
