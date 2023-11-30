import java.util.*;

class Aresta {
    int destino;
    int peso;

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }
}

class Vertice {
    int valor;
    List<Aresta> arestas;

    public Vertice(int valor) {
        this.valor = valor;
        this.arestas = new ArrayList<>();
    }
}

class Grafo {
    List<Vertice> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    public void adicionarVertice(int valor) {
        vertices.add(new Vertice(valor));
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        Vertice verticeOrigem = encontrarVertice(origem);
        Vertice verticeDestino = encontrarVertice(destino);

        if (verticeOrigem != null && verticeDestino != null) {
            verticeOrigem.arestas.add(new Aresta(destino, peso));
            // Se o grafo for não direcionado, adicione a aresta no outro sentido também
            verticeDestino.arestas.add(new Aresta(origem, peso));
        }
    }

    public void removerAresta(int origem, int destino) {
        Vertice verticeOrigem = encontrarVertice(origem);
        if (verticeOrigem != null) {
            verticeOrigem.arestas.removeIf(a -> a.destino == destino);
        }
    }

    public int grauDoVertice(int valorVertice) {
        Vertice vertice = encontrarVertice(valorVertice);
        return (vertice != null) ? vertice.arestas.size() : -1;
    }

    public int grauDoGrafo() {
        int grau = 0;
        for (Vertice vertice : vertices) {
            grau += vertice.arestas.size();
        }
        return grau;
    }

    public List<Integer> vizinhosDoVertice(int valorVertice) {
        List<Integer> vizinhos = new ArrayList<>();
        Vertice vertice = encontrarVertice(valorVertice);

        if (vertice != null) {
            for (Aresta aresta : vertice.arestas) {
                vizinhos.add(aresta.destino);
            }
        }
        return vizinhos;
    }

    public boolean grafoConexo() {
        if (vertices.isEmpty()) {
            // Grafo vazio é considerado conexo
            return true;
        }

        // Inicia a busca em profundidade a partir do primeiro vértice
        Set<Integer> visitados = new HashSet<>();
        dfs(vertices.get(0), visitados);

        // Verifica se todos os vértices foram visitados
        return visitados.size() == vertices.size();
    }

    private void dfs(Vertice vertice, Set<Integer> visitados) {
        visitados.add(vertice.valor);
        for (Aresta aresta : vertice.arestas) {
            if (!visitados.contains(aresta.destino)) {
                dfs(encontrarVertice(aresta.destino), visitados);
            }
        }
    }

    public boolean grafoRegular() {
        if (vertices.isEmpty()) {
            // Grafo vazio é considerado regular
            return true;
        }

        int grau = grauDoVertice(vertices.get(0).valor);

        for (Vertice vertice : vertices) {
            if (grauDoVertice(vertice.valor) != grau) {
                return false;
            }
        }

        return true;

    }

    public boolean grafoCompleto() {
        if (vertices.isEmpty()) {
            // Grafo vazio é considerado completo
            return true;
        }

        int numVertices = vertices.size();
        Set<Integer> verticesVisitados = new HashSet<>();

        for (Vertice vertice : vertices) {
            verticesVisitados.add(vertice.valor);

            // Verifica se todos os outros vértices são conectados ao vértice atual
            Set<Integer> vizinhos = new HashSet<>(vizinhosDoVertice(vertice.valor));
            vizinhos.add(vertice.valor); // Adiciona o próprio vértice à lista de vizinhos

            if (vizinhos.size() != numVertices) {
                return false;
            }
        }

        // Verifica se todos os vértices foram visitados
        return verticesVisitados.size() == numVertices;

    }


    private Vertice encontrarVertice(int valor) {
        for (Vertice vertice : vertices) {
            if (vertice.valor == valor) {
                return vertice;
            }
        }
        return null;
    }

    
}
