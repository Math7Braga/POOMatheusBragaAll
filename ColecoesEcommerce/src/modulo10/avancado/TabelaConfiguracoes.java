package modulo10.avancado;

import java.util.Enumeration;
import java.util.Hashtable;

public class TabelaConfiguracoes {

    // Hashtable e uma classe LEGADA (presente desde o Java 1.0, antes do
    // Collections Framework atual, introduzido no Java 1.2). Assim como o
    // Vector, e sincronizada internamente (thread-safe), o que traz um
    // overhead de desempenho. Diferente do HashMap, o Hashtable NAO aceita
    // chaves nem valores nulos (lanca NullPointerException).
    private Hashtable<String, String> sessoesAtivas = new Hashtable<>();

    public void abrirSessao(String idSessao, String usuario) {
        sessoesAtivas.put(idSessao, usuario);
    }

    public void encerrarSessao(String idSessao) {
        sessoesAtivas.remove(idSessao);
    }

    public void listarSessoesViaEnumeration() {
        // Enumeration e a interface de iteracao LEGADA, anterior ao Iterator.
        // Oferece apenas hasMoreElements() e nextElement(), sem suporte a
        // remocao segura durante a iteracao (nao existe metodo remove()).
        System.out.println("Sessoes ativas no sistema:");
        Enumeration<String> chaves = sessoesAtivas.keys();
        while (chaves.hasMoreElements()) {
            String idSessao = chaves.nextElement();
            String usuario = sessoesAtivas.get(idSessao);
            System.out.println("  Sessao [" + idSessao + "] -> Usuario: " + usuario);
        }
    }

    public int totalSessoesAtivas() {
        return sessoesAtivas.size();
    }
}
