package exercicio5;

/**
 * Testa a ContaBancaria fazendo um saque válido e um saque que estoura
 * o saldo, capturando a SaldoInsuficienteException nesse segundo caso.
 */
public class TesteContaBancaria {

	public static void main(String[] args) {
		ContaBancaria conta = new ContaBancaria(1000.0);

		System.out.println("Saldo inicial: R$ " + conta.getSaldo());

		// Saque válido
		try {
			conta.sacar(300.0);
		} catch (SaldoInsuficienteException e) {
			System.out.println("Erro: " + e.getMessage());
		}

		// Saque que estoura o saldo
		try {
			conta.sacar(5000.0);
		} catch (SaldoInsuficienteException e) {
			System.out.println("Erro: " + e.getMessage());
		} finally {
			System.out.println("Fim da operação");
		}
	}
}
