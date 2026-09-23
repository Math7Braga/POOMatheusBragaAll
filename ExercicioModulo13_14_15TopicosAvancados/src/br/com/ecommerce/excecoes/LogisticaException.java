package br.com.ecommerce.excecoes;

/**
 * Exceção CHECKED: exige cláusula throws nos métodos sinalizadores.
 * Indicada para falhas preveníveis e validações no momento da instanciação.
 */
public class LogisticaException extends Exception {

    private static final long serialVersionUID = 1L;

    public LogisticaException(String mensagem) {
        super(mensagem);
    }

    public LogisticaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}