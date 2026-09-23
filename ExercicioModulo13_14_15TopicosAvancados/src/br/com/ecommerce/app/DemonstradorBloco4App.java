package br.com.ecommerce.app;

import br.com.ecommerce.logistica.PacoteExpresso;
import br.com.ecommerce.util.AuditorMetadadosReflection;
import br.com.ecommerce.util.FilaArmazenamento;

/**
 * Demonstração do BLOCO 4: gerenciamento de memória (String Pool, Pool de
 * Wrappers), StringBuilder, wrappers utilitários e auditoria reflexiva.
 */
public class DemonstradorBloco4App {

    public static void main(String[] args) {
        System.out.println("=== BLOCO 4: Memória, Wrappers e Reflection ===\n");

        System.out.println("-- Pool de literais de String (== vs equals) --");
        String s1 = "LOGISTICA";
        String s2 = "LOGISTICA";
        String s3 = new String("LOGISTICA");
        System.out.println("  s1 == s2 (mesma referência no pool)? " + (s1 == s2));
        System.out.println("  s1 == s3 (new String => nova instância)? " + (s1 == s3));
        System.out.println("  s1.equals(s3) (mesmo estado/equivalência)? " + s1.equals(s3));

        System.out.println("\n-- Pool de Wrappers (cache de autoboxing -128..127) --");
        Integer a1 = 100;
        Integer a2 = 100;
        Integer b1 = 500;
        Integer b2 = 500;
        System.out.println("  a1 == a2 (100: dentro da faixa => mesmo objeto)? " + (a1 == a2));
        System.out.println("  b1 == b2 (500: fora da faixa => objetos distintos)? " + (b1 == b2));
        System.out.println("  b1.equals(b2) (equivalência de estado)? " + b1.equals(b2));

        System.out.println("\n-- StringBuilder x concatenação iterativa (imutabilidade) --");
        StringBuilder manifesto = new StringBuilder();
        for (int i = 1; i <= 5; i++) {
            manifesto.append("MLVF-000").append(i).append("; ");
        }
        System.out.println("  Manifesto montado via StringBuilder.append: " + manifesto);
        System.out.println("  Comprimento do manifesto: " + manifesto.length());

        System.out.println("\n-- Wrappers utilitários e constantes de Float --");
        System.out.println("  Character.isDigit('7')? " + Character.isDigit('7'));
        System.out.println("  Character.isUpperCase('A')? " + Character.isUpperCase('A'));
        System.out.println("  Integer.parseInt(\"150\") + 50 = " + (Integer.parseInt("150") + 50));
        System.out.println("  Float.NaN = " + Float.NaN);
        System.out.println("  Float.POSITIVE_INFINITY = " + Float.POSITIVE_INFINITY);

        System.out.println("\n-- Auditoria reflexiva (inspecionarClasse) --");
        System.out.println(">>> FilaArmazenamento.class (anotada com @AuditoriaLogistica):");
        AuditorMetadadosReflection.inspecionarClasse(FilaArmazenamento.class);
        System.out.println(">>> PacoteExpresso.class (classe SEM anotação, para contraste):");
        AuditorMetadadosReflection.inspecionarClasse(PacoteExpresso.class);
    }
}