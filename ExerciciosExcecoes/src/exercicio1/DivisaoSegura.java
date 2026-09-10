package exercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Exercício 1 - Divisão Segura
 *
 * Lê dois números inteiros do usuário e tenta dividir o primeiro pelo
 * segundo, tratando:
 * - ArithmeticException (divisão por zero)
 * - InputMismatchException (usuário digita algo que não é número)
 * Sempre exibe "Fim da operação" no bloco finally.
 */
public class DivisaoSegura {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			System.out.print("Digite o primeiro número (dividendo): ");
			int numerador = scanner.nextInt();

			System.out.print("Digite o segundo número (divisor): ");
			int denominador = scanner.nextInt();

			int resultado = numerador / denominador;
			System.out.println("Resultado da divisão: " + resultado);

		} catch (ArithmeticException e) {
			System.out.println("Erro: não é possível dividir por zero.");

		} catch (InputMismatchException e) {
			System.out.println("Erro: digite apenas números inteiros.");

		} finally {
			System.out.println("Fim da operação");
			scanner.close();
		}
	}
}
