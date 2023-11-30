import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Informe o número de vértices do grafo: ");
        int numVertices = scanner.nextInt();

        Grafo grafo = new Grafo();

        for (int i = 1; i <= numVertices; i++) {
            grafo.adicionarVertice(i);
        }

        boolean sair = false;

        while (!sair) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Adicionar aresta");
            System.out.println("2. Remover aresta");
            System.out.println("3. Consultar grau de um vértice");
            System.out.println("4. Consultar grau do grafo");
            System.out.println("5. Consultar vizinhos de um vértice");
            System.out.println("6. Verificar se o grafo é conexo");
            System.out.println("7. Verificar se o grafo é regular");
            System.out.println("8. Verificar se o grafo é completo");
            System.out.println("9. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Informe a origem da aresta: ");
                    int origem = scanner.nextInt();

                    System.out.print("Informe o destino da aresta: ");
                    int destino = scanner.nextInt();

                    System.out.print("Informe o peso da aresta: ");
                    int peso = scanner.nextInt();

                    grafo.adicionarAresta(origem, destino, peso);
                    break;

                case 2:
                    System.out.print("Informe a origem da aresta a ser removida: ");
                    int origemRemover = scanner.nextInt();

                    System.out.print("Informe o destino da aresta a ser removida: ");
                    int destinoRemover = scanner.nextInt();

                    grafo.removerAresta(origemRemover, destinoRemover);
                    break;

                case 3:
                    System.out.print("Informe o vértice para consultar o grau: ");
                    int verticeConsulta = scanner.nextInt();

                    int grauVertice = grafo.grauDoVertice(verticeConsulta);
                    System.out.println("Grau do vértice " + verticeConsulta + ": " + grauVertice);
                    break;

                case 4:
                    int grauGrafo = grafo.grauDoGrafo();
                    System.out.println("Grau do grafo: " + grauGrafo);
                    break;

                case 5:
                    System.out.print("Informe o vértice para consultar os vizinhos: ");
                    int verticeVizinhos = scanner.nextInt();

                    List<Integer> vizinhos = grafo.vizinhosDoVertice(verticeVizinhos);
                    System.out.println("Vizinhos do vértice " + verticeVizinhos + ": " + vizinhos);
                    break;

                case 6:
                    boolean grafoConexo = grafo.grafoConexo();
                    System.out.println("O grafo é conexo? " + grafoConexo);
                    break;

                case 7:
                    boolean grafoRegular = grafo.grafoRegular();
                    System.out.println("O grafo é regular? " + grafoRegular);
                    break;

                case 8:
                    boolean grafoCompleto = grafo.grafoCompleto();
                    System.out.println("O grafo é completo? " + grafoCompleto);
                    break;

                case 9:
                    sair = true;
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        }

        scanner.close();
    }
}