package exercicio4;

import java.util.Scanner;

/**
 * Exercício 4 - Conversor Numérico com Propagação (throws)
 *
 * O método converterParaInteiro declara "throws NumberFormatException",
 * propagando o erro para quem o chamar. O main trata o erro em um
 * bloco try-catch.
 */
public class ConversorNumerico {

	// Método que propaga a exceção usando "throws"
	static int converterParaInteiro(String texto) throws NumberFormatException {
		return Integer.parseInt(texto);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite um número para converter: ");
			String texto = scanner.nextLine();

			int numero = converterParaInteiro(texto);
			System.out.println("Número convertido com sucesso: " + numero);

		} catch (NumberFormatException e) {
			System.out.println("Erro: o texto digitado não é um número inteiro válido.");

		} finally {
			System.out.println("Fim da operação");
			scanner.close();
		}
	}
}
