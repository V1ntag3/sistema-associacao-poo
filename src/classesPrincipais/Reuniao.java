package classesPrincipais;

import java.util.ArrayList;

public class Reuniao {
	private ArrayList<Associado> associadosReuniao = new ArrayList<Associado>();
	private String ata;
	private long data;
	private int associacao;

	public Reuniao(long data, String ata) {
		setData(data);
		setAta(ata);
	}

	public Reuniao() {
		// TODO Auto-generated constructor stub
	}

	public String getAta() {
		return ata;
	}

	public void setAta(String ata) {
		this.ata = ata;
	}

	public long getData() {
		return data;
	}

	public void setData(long data) {
		this.data = data;
	}

	public ArrayList<Associado> getAssociadosReuniao() {
		return associadosReuniao;
	}

	public void setAssociadosReuniao(ArrayList<Associado> associadosReuniao) {
		this.associadosReuniao = associadosReuniao;
	}

	public int getAssociacao() {
		return associacao;
	}

	public void setAssociacao(int associacao) {
		this.associacao = associacao;
	}
}
