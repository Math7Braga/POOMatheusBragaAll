package modulo10;

import java.util.Vector;

public class RegistradorEventos {

    // O Vector e uma implementacao de List que e "synchronized", ou seja,
    // seus metodos possuem controle de concorrencia interno (locks),
    // tornando-o seguro para uso por multiplas threads simultaneamente
    // (por exemplo, varios operadores registrando logs de auditoria ao
    // mesmo tempo).
    //
    // Ja o ArrayList NAO e sincronizado. Em ambiente multithread, multiplas
    // threads acessando/modificando um ArrayList ao mesmo tempo podem gerar
    // dados corrompidos, comportamento inconsistente ou lancar
    // ConcurrentModificationException.
    //
    // Trade-off: a sincronizacao do Vector tem um custo de desempenho
    // (overhead de lock), entao em cenarios single-thread o ArrayList e
    // geralmente preferido. Para concorrencia moderna, tambem se usa
    // Collections.synchronizedList(new ArrayList<>()) ou CopyOnWriteArrayList.
    private Vector<String> logs = new Vector<>();

    public synchronized void registrarEvento(String evento) {
        logs.add(evento);
    }

    public void exibirLogs() {
        System.out.println("Logs de auditoria registrados: " + logs);
    }
}
