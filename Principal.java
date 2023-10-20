import java.util.List;

public class Principal {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(5, false, false);

        grafo.inserirAresta(0, 1, 1);
        grafo.inserirAresta(0, 2, 1);
        grafo.inserirAresta(1, 3, 1);
        grafo.inserirAresta(2, 3, 1);
        grafo.inserirAresta(2, 4, 1);
        grafo.inserirAresta(3, 4, 1);

        System.out.println("Grau do vértice 2: " + grafo.consultarGrauVertice(2));
        System.out.println("Grau do grafo: " + grafo.consultarGrauGrafo());

        List<Aresta> vizinhos = grafo.consultarVizinhos(2);
        System.out.print("Vizinhos do vértice 2: ");
        for (Aresta aresta : vizinhos) {
            System.out.print(aresta.getDestino() + " ");
        }
        System.out.println();

        boolean conexo = grafo.verificarConexo();
        System.out.println("O grafo é conexo? " + conexo);

        boolean regular = grafo.verificarRegular();
        System.out.println("O grafo é regular? " + regular);

        boolean completo = grafo.verificarCompleto();
        System.out.println("O grafo é completo? " + completo);

        System.out.println("Busca em Largura a partir do vértice 0:");
        grafo.buscaEmLargura(0);
        System.out.println();
    }
}