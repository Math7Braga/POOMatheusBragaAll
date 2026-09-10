package exercicio3;

/**
 * Exceção personalizada lançada quando uma idade fornecida é inválida
 * (menor que 0 ou maior que 150).
 */
public class IdadeInvalidaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdadeInvalidaException(String mensagem) {
		super(mensagem);
	}
}
