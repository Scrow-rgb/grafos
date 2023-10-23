import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Grafo grafo = new Grafo(5, true, false);
        Scanner leitor = new Scanner(System.in);

        grafo.inserirAresta(0, 1, 1);
        grafo.inserirAresta(0, 2, 1);
        grafo.inserirAresta(1, 3, 1);
        grafo.inserirAresta(1, 4, 1);
        grafo.inserirAresta(2, 3, 1);
        grafo.inserirAresta(2, 1, 1);

        int vertice;

        System.out.println("Informe o vertice a ser consultado");
        vertice = leitor.nextInt();

        System.out.println("Grau do vértice " + vertice + ": " + grafo.consultarGrauVertice(vertice));
        System.out.println("Grau do grafo: " + grafo.consultarGrauGrafo());

        int vizinho;

        System.out.println("Informe qual vertice voce quer saber o vizinho");
        vizinho = leitor.nextInt();

        List<Aresta> vizinhos = grafo.consultarVizinhos(vizinho);
        System.out.print("Vizinhos do vértice " + vizinho + ": ");
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


        int origem;

        System.out.println("Qual vertice quer comecar para fazer a busca?");
        origem = leitor.nextInt();

        System.out.println("Busca em Largura a partir do vértice " + origem + ": ");
        grafo.buscaEmLargura(origem);
        System.out.println();


        leitor.close();
    }
}