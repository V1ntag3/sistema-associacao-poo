package classesPrincipais;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import conexao.DAOAssociacao;
import conexao.DAOAssociado;
import conexao.DAOFrequencia;
import conexao.DAOPagamento;
import conexao.DAOReuniao;
import conexao.DAOTaxa;
import excecoes.AssociacaoJaExistente;
import excecoes.AssociacaoNaoExistente;
import excecoes.AssociadoJaExistente;
import excecoes.AssociadoJaRemido;
import excecoes.AssociadoNaoExistente;
import excecoes.AssociadoRemido;
import excecoes.FrequenciaIncompativel;
import excecoes.FrequenciaJaRegistrada;
import excecoes.ReuniaoJaExistente;
import excecoes.ReuniaoNaoExistente;
import excecoes.TaxaJaExistente;
import excecoes.TaxaNaoExistente;
import excecoes.ValorInvalido;
import interfaceA.InterfaceAssociacao;

public class MinhaAssociacao implements InterfaceAssociacao {

	// Calcula a frequência de um associado nas reuniões ocorridas durante um
	// um número entre 0 e 1 (ex: 0,6, indicando que o associado participou de 60%
	// das reuniões.
	public double calcularFrequencia(int codigoAssociado, int numAssociacao, long inicio, long fim)
			throws AssociadoNaoExistente, AssociacaoNaoExistente {
		try {
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();
			Associado assoUsado = new Associado();
			double result;
			double quant = 0;
			double reuniao = 0;
			for (int i = 0; i < associacoes.size(); i++) {
				for (int j = 0; j < associacoes.get(i).getAssociados().size(); j++) {
					if (associacoes.get(i).getAssociados().get(j).getNumero() == codigoAssociado) {
						assoUsado = associacoes.get(i).getAssociados().get(j);
					}
				}
			}
			if (assoUsado == null || assoUsado.getNome() == null || assoUsado.getNome().equals("")
					|| assoUsado.getNome().equals(" ")) {
				throw new AssociadoNaoExistente();
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == numAssociacao) {
					for (int j = 0; j < associacoes.get(i).getReunioes().size(); j++) {
						for (int z = 0; z < associacoes.get(i).getReunioes().get(j).getAssociadosReuniao().size();) {
							/***************************************
							 * GAMBIARRA ON
							 ****************************************/
							if (associacoes.get(i).getReunioes().get(j).getAssociadosReuniao().get(z)
									.getNumero() == assoUsado.getNumero()
									&& associacoes.get(i).getReunioes().get(j).getData() >= inicio
									&& associacoes.get(i).getReunioes().get(j).getData() <= fim
									&& quant != associacoes.get(i).getReunioes().get(j).getAssociadosReuniao().size()) {
								quant = quant + 1;

							}

							z++;

						}
						if (associacoes.get(i).getReunioes().get(j).getData() >= inicio
								&& associacoes.get(i).getReunioes().get(j).getData() <= fim) {
							reuniao = reuniao + 1;
						}
					}

					result = (quant / reuniao);
					if (reuniao == 0) {
						return 0;
					} else {
						return result;
					}
				}
			}
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return 0;
	}

	// Registra a frequencia de um associado em uma reunião. não deveria registrar
	// participacao em reunioes acontecidas antes da sua
	// filiacao na associação.
	public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
			throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente, FrequenciaJaRegistrada,
			FrequenciaIncompativel {
		try {
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();
			Associado assoUsado = new Associado();
			Associacao associacaoUsado = new Associacao();

			for (int i = 0; i < associacoes.size(); i++) {
				for (int j = 0; j < associacoes.get(i).getAssociados().size(); j++) {

					if (associacoes.get(i).getAssociados().get(j).getNumero() == codigoAssociado
							&& associacoes.get(i).getNumero() == numAssociacao) {
						assoUsado = associacoes.get(i).getAssociados().get(j);
						associacaoUsado = associacoes.get(i);
					}
				}
			}
			for (int i = 0; i < associacoes.size(); i++) {

				if (associacoes.get(i).getNumero() == numAssociacao) {
					associacaoUsado = associacoes.get(i);
				}
			}

			if (associacaoUsado.getNome() == null || associacaoUsado.getNome().equals("")
					|| associacaoUsado.getNome().equals(" ")) {
				throw new AssociacaoNaoExistente();
			}
			if (assoUsado.getNome() == null || assoUsado.getNome().equals("") || assoUsado.getNome().equals(" ")) {
				throw new AssociadoNaoExistente();
			}

			if (assoUsado.getDataAssociacao() > dataReuniao) {
				throw new FrequenciaIncompativel();
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == numAssociacao) {
					for (int j = 0; j < associacoes.get(i).getReunioes().size(); j++) {
						if (associacoes.get(i).getReunioes().get(j).getData() == dataReuniao) {
							for (int z = 0; z < associacoes.get(i).getReunioes().get(j).getAssociadosReuniao()
									.size(); z++) {

								if (associacoes.get(i).getReunioes().get(j).getAssociadosReuniao().get(z)
										.getNumero() == codigoAssociado) {
									throw new FrequenciaJaRegistrada();
								}
							}
							DAOFrequencia frequenciaBD = new DAOFrequencia();
							try {
								frequenciaBD.inserir(dataReuniao, codigoAssociado, numAssociacao);
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							return;
						}
					}
					throw new ReuniaoNaoExistente();
				}
			}
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

// Calcula o total de taxas previstas para um dado ano, em uma associação.
	public double calcularTotalDeTaxas(int numAssociacao, int vigencia) throws AssociacaoNaoExistente {
		try {
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();
			double valorTaxa = 0;
			int teste = 0;
			for (int z = 0; z < associacoes.size(); z++) {
				if (associacoes.get(z).getNumero() == numAssociacao) {
					teste = 1;
				}
			}
			if (teste == 0) {
				throw new AssociacaoNaoExistente();
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == numAssociacao) {
					for (int j = 0; j < associacoes.get(i).getTaxas().size(); j++) {
						if (associacoes.get(i).getTaxas().get(j).getVigencia() == vigencia) {
							valorTaxa = valorTaxa + associacoes.get(i).getTaxas().get(j).getValorAnual();
						}
					}
					if (valorTaxa != 0) {
						return valorTaxa;
					}
				}
			}
			return valorTaxa;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// Calcula o total de pagamentos realizado por um associado, em uma associação,
	// para uma taxa, que possui uma vigência,
	// dentro de um certo período de tempo.
	public double somarPagamentoDeAssociado(int numAssociacao, int numAssociado, String nomeTaxa, int vigencia,
			long inicio, long fim) throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
		try {
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();
			double valorPagamento = 0;
			Taxa taxaUsada = new Taxa();
			for (int i = 0; i < associacoes.size(); i++) {
				for (int j = 0; j < associacoes.get(i).getTaxas().size(); j++) {
					if (associacoes.get(i).getTaxas().get(j).getNome().equals(nomeTaxa)
							&& associacoes.get(i).getTaxas().get(j).getVigencia() == vigencia) {
						taxaUsada = associacoes.get(i).getTaxas().get(j);
					}
				}
			}
			if (taxaUsada.getNome() == null || taxaUsada.getNome().equals("")) {
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == numAssociacao) {
					for (int j = 0; j < associacoes.get(i).getAssociados().size(); j++) {
						if (associacoes.get(i).getAssociados().get(j).getNumero() == numAssociado) {
							for (int z = 0; z < associacoes.get(i).getAssociados().get(j).getPags().size(); z++) {

								if (associacoes.get(i).getAssociados().get(j).getPags().get(z).getTipo().getNome()
										.equals(nomeTaxa)
										&& associacoes.get(i).getAssociados().get(j).getPags().get(z).getTipo()
												.getVigencia() == vigencia
										&& associacoes.get(i).getAssociados().get(j).getPags().get(z)
												.getData() >= inicio
										&& associacoes.get(i).getAssociados().get(j).getPags().get(z)
												.getData() <= fim) {
									valorPagamento = valorPagamento
											+ associacoes.get(i).getAssociados().get(j).getPags().get(z).getValor();
								}
							}

							return valorPagamento;
						}
					}
					throw new AssociadoNaoExistente();
				}
			}
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	// Registra o pagamento de uma taxa, em uma associação, dentro uma determinada
	// competência, para um associado.
	// Associados remidos não deveriam mais realizar pagamentos
	// de taxas administrativas vigentes em datas antes da sua remissão, gerando
	// exceção de AssociadoJaRemido se houver tentativa de se
	// pagar algo para esse caso.
	//// Lembrar de verificar valores negativos.
	// O valor a ser pago não pode ser menor que uma parcela, embora não precise ser
	// exatamente duas parcelas. Uma parcela de R$20,00 por mês aceita um
	// pagamento de R$30,00, sendo uma parcela completa e um pedaço da próxima.
	// Caso o valor a ser pago seja menor que o mínimo (não sendo o ultimo do ano!)
	// ou gerando pagamento maior que a taxa anual, gerar exceção de ValorInvalido.
	public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data,
			double valor)
			throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
		try {
			DAOPagamento pagamentoBD = new DAOPagamento();
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTimeInMillis(data);
			if (vigencia > gc.get(Calendar.YEAR)) {
				throw new ValorInvalido();
			}
			if (numAssociacao < 0 || taxa == "" || vigencia < 0 || numAssociado < 0 || data < 0 || valor < 0
					|| taxa == " " || taxa == null) {
				throw new ValorInvalido();
			}
			Taxa taxaUsada = new Taxa();
			for (int i = 0; i < associacoes.size(); i++) {
				for (int j = 0; j < associacoes.get(i).getTaxas().size(); j++) {
					if (associacoes.get(i).getTaxas().get(j).getNome().equals(taxa)
							&& associacoes.get(i).getTaxas().get(j).getVigencia() == vigencia) {

						taxaUsada = associacoes.get(i).getTaxas().get(j);
					}
				}
			}

			if (taxaUsada.getNome() == null || taxaUsada.getNome().equals("") || taxaUsada.getNome().equals(" ")) {
				throw new TaxaNaoExistente();
			}
			double taxaPaga = 0;
			int parcelasPagas = 0;
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == numAssociacao) {
					for (int j = 0; j < associacoes.get(i).getAssociados().size(); j++) {
						if (associacoes.get(i).getAssociados().get(j).getNumero() == numAssociado) {
							for (int z = 0; z < associacoes.get(i).getAssociados().get(j).getPags().size(); z++) {
								if (associacoes.get(i).getAssociados().get(j).getPags().get(z).getTipo().getNome()
										.equals(taxa)
										&& associacoes.get(i).getAssociados().get(j).getPags().get(z).getTipo()
												.getVigencia() == vigencia) {
									parcelasPagas = parcelasPagas + 1;
									taxaPaga = taxaPaga
											+ associacoes.get(i).getAssociados().get(j).getPags().get(z).getValor();
								}
								if (associacoes.get(i).getAssociados().get(j).getPags().get(z).getTipo().getNome()
										.equals(taxa)
										&& associacoes.get(i).getAssociados().get(j).getPags().get(z).getTipo()
												.getVigencia() != vigencia) {
									throw new ValorInvalido();
								}
							}
						}
					}
				}
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == numAssociacao) {
					for (int j = 0; j < associacoes.get(i).getAssociados().size(); j++) {
						if (associacoes.get(i).getAssociados().get(j).getNumero() == numAssociado) {
							if (associacoes.get(i).getAssociados().get(j) instanceof AssociadoRemido) {
								if (associacoes.get(i).getAssociados().get(j).getRemissao() < data
										&& taxaUsada.isAdministrativa() == true) {
									throw new AssociadoJaRemido();
								}
							}
							if (((taxaUsada.getValorAnual() / taxaUsada.getQuantParcelas()) > valor)
									|| taxaUsada.getValorAnual() - taxaPaga < valor) {
								if (taxaUsada.getValorAnual() - taxaPaga != valor) {
									throw new ValorInvalido();
								}
							}
							if (associacoes.get(i).getAssociados().get(j).getDataAssociacao() > data) {
								throw new ValorInvalido();
							}
							Pagamento pags = new Pagamento();
							pags.setTipo(taxaUsada);
							pags.setData(data);
							pags.setValor(valor);
							pags.setAssociacao(numAssociacao);
							pags.setAssociado(numAssociado);
							pags.setVigencia(vigencia);
							pagamentoBD.inserir(pags);
							return;
						}
					}
					throw new AssociadoNaoExistente();

				}
			}
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**********************************************************************************************************************************************************************************************************************
	 * Metodos para adicionar valores no ArrayList
	 ***********************************************************************************************************************************************************************************************************************/
	// Adiciona uma associação a ser gerenciada. Valida todos os campos para evitar
	// dados não preenchidos.
	public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
		try {
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();

			if (a.getNome() == " " || a.getNome() == null || a == null || a.getNome() == "" || a.getNumero() < 0) {
				throw new ValorInvalido();
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == a.getNumero()
						&& associacoes.get(i).getNome().equals(a.getNome())) {
					throw new AssociacaoJaExistente();
				}
			}
			associacaoBD.inserir(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Adiciona um associado a uma associação. Valida todos os campos para evitar
	// dados não preenchidos.
	public void adicionar(int associacao, Associado a)
			throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
		try {
			a.setAssociacao(associacao);
			DAOAssociado associadoBD = new DAOAssociado();
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();

			if (a.getNome() == " " || a.getDataAssociacao() < 0 || a.getNascimento() < 0 || a.getRemissao() < 0
					|| (a.getNome() == null) || a == null || a.getNome() == "" || a.getNumero() < 0 || associacao < 0
					|| a.getTelefone() == "" || a.getTelefone() == null || a.getTelefone() == " "
					|| (a.getDataAssociacao() < a.getNascimento())) {
				throw new ValorInvalido();
			}
			if (a instanceof AssociadoRemido) {
				if ((a.getDataAssociacao() > a.getRemissao())) {
					throw new ValorInvalido();
				}
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == associacao) {
					for (int j = 0; j < associacoes.get(i).getAssociados().size(); j++) {
						if ((associacoes.get(i).getAssociados().get(j).getNumero() == a.getNumero())
								|| associacoes.get(i).getAssociados().get(j) == a) {
							throw new AssociadoJaExistente();
						}
					}
					associadoBD.inserir(a);
					return;
				}

			}
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Adiciona uma reunião a uma associação. Valida todos os campos para evitar
	// dados não preenchidos.
	public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
		try {
			r.setAssociacao(associacao);
			DAOReuniao reuniaoBD = new DAOReuniao();
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();
			if (r == null || r.getAta() == "" || r.getAta() == " " || r.getData() < 0 || associacao < 0
					|| r.getAta() == null) {
				throw new ValorInvalido();
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == associacao) {
					for (int j = 0; j < associacoes.get(i).getReunioes().size(); j++) {
						if (associacoes.get(i).getReunioes().get(j).getData() == r.getData()) {
							throw new ReuniaoJaExistente();
						}
					}
					reuniaoBD.inserir(r);
					return;
				}

			}
			throw new AssociacaoNaoExistente();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Adiciona uma taxa a uma associação. Valida todos os campos para evitar dados
	// não preenchidos.
	public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
		try {
			t.setAssociacao(associacao);
			DAOTaxa taxaBD = new DAOTaxa();
			DAOAssociacao associacaoBD = new DAOAssociacao();
			ArrayList<Associacao> associacoes = new ArrayList<Associacao>();
			associacoes = associacaoBD.recuperarTodos();

			if (t == null || t.getNome() == "" || t.getNome() == null || t.getQuantParcelas() < 0
					|| t.getValorAnual() < 0 || t.getVigencia() < 0 || associacao < 0 || t.getNome() == " ") {

				throw new ValorInvalido();
			}
			if ((t.getQuantParcelas() == 0 && t.getValorAnual() != 0)
					|| (t.getValorAnual() == 0 && t.getQuantParcelas() != 0)) {
				throw new ValorInvalido();
			}
			for (int i = 0; i < associacoes.size(); i++) {
				if (associacoes.get(i).getNumero() == associacao) {
					for (int j = 0; j < associacoes.get(i).getTaxas().size(); j++) {
						if (associacoes.get(i).getTaxas().get(j).getNome().equals(t.getNome())
								&& associacoes.get(i).getTaxas().get(j).getVigencia() == t.getVigencia()) {
							throw new TaxaJaExistente();
						}
					}
					taxaBD.inserir(t);
					return;
				}

			}
			throw new AssociacaoNaoExistente();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void limparBanco() {
		DAOTaxa taxaBD = new DAOTaxa();
		DAOAssociacao associacaoBD = new DAOAssociacao();
		DAOReuniao reuniaoBD = new DAOReuniao();
		DAOAssociado associadoBD = new DAOAssociado();
		DAOPagamento pagamentoBD = new DAOPagamento();
		DAOFrequencia frequenciaBD = new DAOFrequencia();
		try {
			taxaBD.removerTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			associacaoBD.removerTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reuniaoBD.removerTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			associadoBD.removerTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			pagamentoBD.removerTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			frequenciaBD.removerTodos();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}