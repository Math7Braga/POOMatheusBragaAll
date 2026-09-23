package br.com.ecommerce.excecoes;

/**
 * Exceção UNCHECKED: deriva de RuntimeException.
 * Indicada para falhas operacionais e inconsistências lógicas de negócio
 * (não exige throws/tratamento obrigatório do compilador).
 */
public class EstoqueInvalidoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EstoqueInvalidoException(String mensagem) {
        super(mensagem);
    }
}