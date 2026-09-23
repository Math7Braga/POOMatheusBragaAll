package br.com.ecommerce.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import br.com.ecommerce.excecoes.LogisticaException;
import br.com.ecommerce.logistica.PacoteExpresso;

/**
 * Demonstração do BLOCO 3: classe anônima vs. lambda na ordenação e
 * pipeline de processamento com a API de Streams.
 */
public class ProcessadorStreamsApp {

    private static final double LIMITE_TARIFARIO = 25.0;

    public static void main(String[] args) throws LogisticaException {
        System.out.println("=== BLOCO 3: Programação Funcional e Streams ===\n");

        List<PacoteExpresso> lote = carregarLote();

        System.out.println("-- 1) Ordenação com CLASSE INTERNA ANÔNIMA (Comparator) --");
        List<PacoteExpresso> imperativo = new ArrayList<>(lote);
        Collections.sort(imperativo, new Comparator<PacoteExpresso>() {
            @Override
            public int compare(PacoteExpresso p1, PacoteExpresso p2) {
                return Double.compare(p1.calcularFreteBase(), p2.calcularFreteBase());
            }
        });
        exibirEmOrdem("Classe anônima", imperativo);

        System.out.println("\n-- 2) Mesma ordenação com EXPRESSÃO LAMBDA (boiler-plate eliminado) --");
        List<PacoteExpresso> funcional = new ArrayList<>(lote);
        Collections.sort(funcional, (p1, p2) -> Double.compare(p1.calcularFreteBase(), p2.calcularFreteBase()));
        exibirEmOrdem("Lambda", funcional);

        System.out.println("\n-- 3) Pipeline com a API de Streams --");

        // filter + Method Reference + collect(toList)
        List<PacoteExpresso> envioNoMesmoDia = lote.stream()
                .filter(PacoteExpresso::isEntregaNoMesmoDia)
                .collect(Collectors.toList());
        System.out.println("  filter(::isEntregaNoMesmoDia) + collect(toList) -> " + envioNoMesmoDia.size()
                + " pacote(s) habilitados para entrega no mesmo dia");

        // mapToDouble + average + getAsDouble
        OptionalDouble mediaOpt = lote.stream()
                .mapToDouble(PacoteExpresso::calcularFreteBase)
                .average();
        System.out.printf("  mapToDouble(::calcularFreteBase).average().getAsDouble() -> média de frete = R$ %.2f%n",
                mediaOpt.getAsDouble());

        // filter tarifário + count
        long acimaDoLimite = lote.stream()
                .filter(p -> p.calcularFreteBase() > LIMITE_TARIFARIO)
                .count();
        System.out.println("  count() de pacotes com frete > R$ " + LIMITE_TARIFARIO + ": " + acimaDoLimite);

        // min + Comparator.comparingDouble + ifPresent
        lote.stream()
                .min(Comparator.comparingDouble(PacoteExpresso::calcularFreteBase))
                .ifPresent(menor -> System.out.printf(
                        "  min(comparingDouble).ifPresent() -> pacote mais barato do lote: %s (R$ %.2f)%n",
                        menor.getCodigoEtiqueta(), menor.calcularFreteBase()));
    }

    private static List<PacoteExpresso> carregarLote() throws LogisticaException {
        List<PacoteExpresso> lote = new ArrayList<>();
        lote.add(new PacoteExpresso("PAC-0001", 1.2));
        lote.add(new PacoteExpresso("PAC-0002", 8.0, 0.40, true));
        lote.add(new PacoteExpresso("PAC-0003", 3.3, 0.15, true));
        lote.add(new PacoteExpresso("PAC-0004", 0.7));
        lote.add(new PacoteExpresso("PAC-0005", 5.5, 0.22, false));
        lote.add(new PacoteExpresso("PAC-0006", 2.0, 0.08, true));
        return lote;
    }

    private static void exibirEmOrdem(String rotulo, List<PacoteExpresso> ordenados) {
        System.out.println("  [" + rotulo + "] ordem crescente de frete base:");
        for (PacoteExpresso p : ordenados) {
            System.out.printf("    %s -> R$ %.2f%n", p.getCodigoEtiqueta(), p.calcularFreteBase());
        }
    }
}