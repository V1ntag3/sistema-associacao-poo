package classesPrincipais;

import java.util.ArrayList;

public class Associacao {
	private int numero;
	private String nome;
	private ArrayList<Associado> associados = new ArrayList<Associado>();
	private ArrayList<Reuniao> reunioes = new ArrayList<Reuniao>();
	private ArrayList<Taxa> taxas = new ArrayList<Taxa>();

	public Associacao(int num, String nome) {
		setNumero(num);
		setNome(nome);
	}

	public Associacao() {
		// TODO Auto-generated constructor stub
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public ArrayList<Associado> getAssociados() {
		return associados;
	}

	public void setAssociados(ArrayList<Associado> associados) {
		this.associados = associados;
	}

	public ArrayList<Reuniao> getReunioes() {
		return reunioes;
	}

	public void setReunioes(ArrayList<Reuniao> reunioes) {
		this.reunioes = reunioes;
	}

	public ArrayList<Taxa> getTaxas() {
		return taxas;
	}

	public void setTaxas(ArrayList<Taxa> taxas) {
		this.taxas = taxas;
	}
}
