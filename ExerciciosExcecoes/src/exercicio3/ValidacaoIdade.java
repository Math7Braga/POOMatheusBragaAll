package exercicio3;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Exercício 3 - Validação de Idade
 *
 * Lê a idade de uma pessoa e lança IdadeInvalidaException (RuntimeException
 * personalizada) se a idade for menor que 0 ou maior que 150. A exceção é
 * capturada no main, que imprime a mensagem de erro.
 */
public class ValidacaoIdade {

	// Método que valida a idade e lança a exceção personalizada se necessário
	static void validarIdade(int idade) {
		if (idade < 0 || idade > 150) {
			throw new IdadeInvalidaException("Idade inválida: " + idade + ". A idade deve estar entre 0 e 150.");
		}
		System.out.println("Idade válida: " + idade + " anos.");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite a idade da pessoa: ");
			int idade = scanner.nextInt();

			validarIdade(idade);

		} catch (IdadeInvalidaException e) {
			System.out.println("Erro: " + e.getMessage());

		} catch (InputMismatchException e) {
			System.out.println("Erro: digite apenas números inteiros.");

		} finally {
			System.out.println("Fim da operação");
			scanner.close();
		}
	}
}
