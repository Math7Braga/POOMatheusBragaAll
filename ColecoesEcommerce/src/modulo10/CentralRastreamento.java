package modulo10;

public class CentralRastreamento {

    public static void inspecionarItem(Object item) {
        // instanceof verifica, em tempo de execucao, se o objeto recebido
        // realiza (implementa) o contrato Rastreavel, sem acoplar o codigo
        // a uma implementacao concreta especifica.
        if (item instanceof Rastreavel) {
            // Casting explicito: convertemos a referencia de Object para
            // Rastreavel para podermos chamar o metodo definido na interface.
            Rastreavel rastreavel = (Rastreavel) item;
            System.out.println(rastreavel.getStatusRastreio());
        } else {
            System.out.println("Item nao e passivel de rastreamento: " + item);
        }
    }
}
