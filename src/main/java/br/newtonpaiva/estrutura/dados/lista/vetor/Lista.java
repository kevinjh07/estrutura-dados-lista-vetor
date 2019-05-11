package br.newtonpaiva.estrutura.dados.lista.vetor;

import java.util.Scanner;

import br.newtonpaiva.estrutura.dados.lista.vetor.model.Aluno;

public class Lista {
	private static final int INSERIR_NO_INICIO = 1;
	private static final int INSERIR_ENTRE_ELEMENTOS = 2;
	private static final int INSERIR_NO_FINAL = 3;
	private static final int REMOVER_NO_INICIO = 4;
	private static final int REMOVER_ENTRE_ELEMENTOS = 5;
	private static final int REMOVER_NO_FIANAL = 6;
	private static final int EXIBIR_LISTA = 7;
	private static final int ORDENAR_POR_RA = 8;
	private static final int LOCALIZAR_POR_RA = 9;
	private static final int SAIR = 10;
	
	private static int ultimo = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Tamanho da lista:");
		int tamanhoLista = sc.nextInt();
		Aluno[] lista = new Aluno[tamanhoLista];
		int opcao = 0;

		while (opcao != 10) {
			StringBuilder builder = new StringBuilder()
					.append("Insira uma opção abaixo:%n")
					.append("%d - Inserir no início%n")
					.append("%d - Inserir entre elementos%n")
					.append("%d - Inserir no final%n")
					.append("%d - Remover no início%n")
					.append("%d - Remover entre elementos%n")
					.append("%d - Remover no final%n")
					.append("%d - Exibir lista%n")
					.append("%d - Ordenar lista por RA%n")
					.append("%d - Localizar por RA%n")
					.append("%d - Sair%n");
			System.out.printf(builder.toString(), INSERIR_NO_INICIO, INSERIR_ENTRE_ELEMENTOS, 
					INSERIR_NO_FINAL, REMOVER_NO_INICIO, REMOVER_ENTRE_ELEMENTOS, REMOVER_NO_FIANAL, EXIBIR_LISTA, 
					ORDENAR_POR_RA, LOCALIZAR_POR_RA, SAIR);
			opcao = sc.nextInt();
			System.out.println();

			try {
				switch (opcao) {
				case INSERIR_NO_INICIO:
					solicitarInserirNoInicio(sc, lista);
					break;
				case INSERIR_ENTRE_ELEMENTOS:
					solicitarInserirEntreElementos(sc, lista);
					break;
				case INSERIR_NO_FINAL:
					solicitarInserirNoFinal(sc, lista);
					break;
				case REMOVER_NO_INICIO:
					verificarListaVazia();
					removerNoInicio(lista);
					break;
				case REMOVER_ENTRE_ELEMENTOS:
					solicitarRemoverEntreElementos(sc, lista);
					break;
				case REMOVER_NO_FIANAL:
					verificarListaVazia();
					removerNoFinal();
					break;
				case EXIBIR_LISTA:
					exibirLista(lista);
					break;
				case ORDENAR_POR_RA:
					ordenarListaPorRa(lista);
					break;
				case LOCALIZAR_POR_RA:
					localizarPorRa(sc, lista);
					break;
				case SAIR:
					sc.close();
					break;
				default:
					System.out.println("Opção inválida!");
					break;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	private static void verificarListaCheia(int tamanhoLista) throws Exception {
		if (ultimo == tamanhoLista - 1) {
			throw new Exception("Lista cheia!");
		}
	}

	private static void verificarListaVazia() throws Exception {
		if (ultimo == -1) {
			throw new Exception("Lista vazia!");
		}
	}
	
	private static Aluno obterNovoAluno(Scanner sc) {
		System.out.println("Insira os dados do aluno");
		Aluno aluno = new Aluno();

		System.out.println("RA:");
		aluno.setRa(sc.nextInt());
		sc.nextLine();

		System.out.println("Nome:");
		aluno.setNome(sc.nextLine());

		System.out.println("Curso:");
		aluno.setCurso(sc.nextLine());
		System.out.println();

		return aluno;
	}

	private static void solicitarInserirNoInicio(Scanner sc, Aluno[] lista) throws Exception {
		verificarListaCheia(lista.length);
		Aluno aluno = obterNovoAluno(sc);
		inserirNoInicio(lista, aluno);
	}

	private static void solicitarRemoverEntreElementos(Scanner sc, Aluno[] lista) throws Exception {
		verificarListaVazia();
		System.out.println("RA do aluno a ser removido:");
		int ra = sc.nextInt();
		sc.nextLine();
		int index = -1;
		for (int i = 0; i < ultimo; i++) {
			if (lista[i].getRa() == ra) {
				index = i;
				break;
			}
		}
		if (index != -1) {
			removerEntreElementos(lista, index);
		} else {
			throw new Exception("Aluno não encontrado!");
		}
	}

	private static void solicitarInserirNoFinal(Scanner sc, Aluno[] lista) throws Exception {
		verificarListaCheia(lista.length);
		Aluno aluno = obterNovoAluno(sc);
		if (ultimo == -1) {
			inserirNoInicio(lista, aluno);
		} else {
			inserirNoFinal(lista, aluno);
		}
	}
	
	private static void solicitarInserirEntreElementos(Scanner sc, Aluno[] lista) throws Exception {
		verificarListaCheia(lista.length);
		System.out.println("Posição desejada:");
		int posicao = sc.nextInt();
		Aluno aluno = obterNovoAluno(sc);
		if (posicao > ultimo) {
			inserirNoFinal(lista, aluno);
		} else {
			inserirEntreElementos(lista, aluno, posicao);
		}
	}

	private static void ordenarListaPorRa(Aluno[] lista) throws Exception {
		verificarListaVazia();
		boolean trocou = true;
		while (trocou) {
			trocou = false;
			for (int i = 0; i < ultimo; i++) {
				if (lista[i].getRa() > lista[i + 1].getRa()) {
					Aluno atual = lista[i];
					lista[i] = lista[i + 1];
					lista[i + 1] = atual;
					trocou = true;
				}

			}
		}
	}

	private static void inserirNoInicio(Aluno[] lista, Aluno aluno) {
		for (int i = ultimo + 1; i > 0; i--) {
			lista[i] = lista[i - 1];
		}
		lista[0] = aluno;
		++ultimo;
	}

	private static void inserirEntreElementos(Aluno[] lista, Aluno aluno, int posicao) {
		for (int i = ultimo + 1; i > posicao; i--) {
			lista[i] = lista[i - 1];
		}
		lista[posicao] = aluno;
		++ultimo;
	}

	private static void inserirNoFinal(Aluno[] lista, Aluno aluno) {
		lista[ultimo + 1] = aluno;
		++ultimo;
	}

	private static void removerNoInicio(Aluno[] lista) {
		for (int i = 0; i < ultimo; i++) {
			lista[i] = lista[i + 1];
		}
		--ultimo;
	}

	private static void removerEntreElementos(Aluno[] lista, int index) {
		for (int i = index; i < ultimo; i++) {
			lista[i] = lista[i + 1];
		}
		--ultimo;
	}

	private static void removerNoFinal() throws Exception {
		verificarListaVazia();
		--ultimo;
	}

	private static void localizarPorRa(Scanner sc, Aluno[] lista) throws Exception {
		verificarListaVazia();
		System.out.println("Digite o RA:");
		int ra = sc.nextInt();
		sc.nextLine();
		Aluno aluno = pesquisarAluno(lista, ra);
		if (aluno != null) {
			System.out.println("Aluno:");
			System.out.println(aluno.toString());
		} else {
			throw new Exception("Aluno não encontrado!");
		}
	}

	private static Aluno pesquisarAluno(Aluno[] lista, int ra) {
		Aluno aluno = null;
		for (int i = 0; i < ultimo; i++) {
			if (lista[i].getRa() == ra) {
				aluno = lista[i];
				break;
			}
		}
		return aluno;
	}

	private static void exibirLista(Aluno[] lista) throws Exception {
		verificarListaVazia();
		System.out.println("Elementos:");
		for (int i = 0; i <= ultimo; i++) {
			System.out.println(lista[i].toString());
		}
		System.out.println();
	}

}
