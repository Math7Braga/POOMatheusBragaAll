package exercicio2;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Exercício 2 - Acesso a Posições de Array
 *
 * Array fixo com 5 cidades. O usuário digita um índice de 0 a 4 para
 * exibir a cidade correspondente. Trata ArrayIndexOutOfBoundsException
 * caso o índice seja inválido (ex.: 5 ou -1).
 */
public class AcessoArray {

	public static void main(String[] args) {
		String[] cidades = { "Rio de Janeiro", "São Paulo", "Belo Horizonte", "Salvador", "Curitiba" };

		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite um número de 0 a 4 para escolher uma cidade: ");
			int indice = scanner.nextInt();

			String cidadeEscolhida = cidades[indice];
			System.out.println("Cidade escolhida: " + cidadeEscolhida);

		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Erro: índice inválido. Digite um número entre 0 e 4.");

		} catch (InputMismatchException e) {
			System.out.println("Erro: digite apenas números inteiros.");

		} finally {
			System.out.println("Fim da operação");
			scanner.close();
		}
	}
}
