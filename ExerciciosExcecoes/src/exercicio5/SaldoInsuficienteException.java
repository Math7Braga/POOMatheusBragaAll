package exercicio5;

/**
 * Exceção personalizada lançada quando se tenta sacar um valor maior
 * do que o saldo disponível na conta.
 */
public class SaldoInsuficienteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SaldoInsuficienteException(String mensagem) {
		super(mensagem);
	}
}
