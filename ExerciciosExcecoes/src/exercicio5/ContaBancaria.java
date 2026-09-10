package exercicio5;

/**
 * Exercício 5 - Cadastro de Conta Bancária
 *
 * Classe simples com um saldo e um método de saque que lança
 * SaldoInsuficienteException quando o valor do saque é maior que o saldo.
 */
public class ContaBancaria {

	private double saldo;

	public ContaBancaria(double saldoInicial) {
		this.saldo = saldoInicial;
	}

	public double getSaldo() {
		return saldo;
	}

	public void sacar(double valor) {
		if (valor > saldo) {
			throw new SaldoInsuficienteException(
					"Saldo insuficiente. Saldo atual: R$ " + saldo + " | Valor solicitado: R$ " + valor);
		}
		saldo -= valor;
		System.out.println("Saque de R$ " + valor + " realizado com sucesso. Novo saldo: R$ " + saldo);
	}
}
