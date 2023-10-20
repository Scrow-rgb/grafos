import java.util.*;

class Grafo {
    private int numVertices;
    private boolean direcionado;
    private boolean ponderado;
    private List<List<Aresta>> listaAdjacencia;
    private int[][] matrizAdjacencia;

    public Grafo(int numVertices, boolean direcionado, boolean ponderado) {
        this.numVertices = numVertices;
        this.direcionado = direcionado;
        this.ponderado = ponderado;
        this.listaAdjacencia = new ArrayList<>();
        this.matrizAdjacencia = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; ++i) {
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public void inserirAresta(int origem, int destino, int peso) {
        if (origem >= 0 && origem < numVertices && destino >= 0 && destino < numVertices) {
            if (ponderado) {
                matrizAdjacencia[origem][destino] = peso;
                if (!direcionado) {
                    matrizAdjacencia[destino][origem] = peso;
                }
            } else {
                matrizAdjacencia[origem][destino] = 1;
                if (!direcionado) {
                    matrizAdjacencia[destino][origem] = 1;
                }
            }

            if (ponderado) {
                listaAdjacencia.get(origem).add(new Aresta(destino, peso));
                if (!direcionado) {
                    listaAdjacencia.get(destino).add(new Aresta(origem, peso));
                }
            } else {
                listaAdjacencia.get(origem).add(new Aresta(destino));
                if (!direcionado) {
                    listaAdjacencia.get(destino).add(new Aresta(origem));
                }
            }
        }
    }

    public void removerAresta(int origem, int destino) {
        if (origem >= 0 && origem < numVertices && destino >= 0 && destino < numVertices) {
            matrizAdjacencia[origem][destino] = 0;
            if (!direcionado) {
                matrizAdjacencia[destino][origem] = 0;
            }

            Iterator<Aresta> iterator = listaAdjacencia.get(origem).iterator();
            while (iterator.hasNext()) {
                Aresta aresta = iterator.next();
                if (aresta.getDestino() == destino) {
                    iterator.remove();
                    break;
                }
            }

            if (!direcionado) {
                iterator = listaAdjacencia.get(destino).iterator();
                while (iterator.hasNext()) {
                    Aresta aresta = iterator.next();
                    if (aresta.getDestino() == origem) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    public int consultarGrauVertice(int vertice) {
        int grau = 0;
        for (int i = 0; i < numVertices; ++i) {
            if (matrizAdjacencia[vertice][i] != 0) {
                grau++;
            }
        }
        return grau;
    }

    public int consultarGrauGrafo() {
        int grau = 0;
        for (int i = 0; i < numVertices; ++i) {
            grau += consultarGrauVertice(i);
        }
        return grau;
    }

    public List<Aresta> consultarVizinhos(int vertice) {
        if (vertice >= 0 && vertice < numVertices) {
            return listaAdjacencia.get(vertice);
        }
        return null;
    }

    public boolean verificarConexo() {
        // Implemente a verificação de conexidade aqui
        return false;
    }

    public boolean verificarRegular() {
        // Implemente a verificação de regularidade aqui
        return false;
    }

    public boolean verificarCompleto() {
        // Implemente a verificação de completude aqui
        return false;
    }

    public void buscaEmProfundidade(int vertice) {
        // Implemente a busca em profundidade aqui
    }

    public void buscaEmLargura(int vertice) {
        // Implemente a busca em largura aqui
    }

    public boolean verificarCaminho(int origem, int destino) {
        // Implemente a verificação de caminho aqui
        return false;
    }

    public void exibirCaminho(int origem, int destino) {
        // Implemente a exibição do caminho aqui
    }
}

class Aresta {
    private int destino;
    private int peso;

    public Aresta(int destino) {
        this.destino = destino;
        this.peso = 0;
    }

    public Aresta(int destino, int peso) {
        this.destino = destino;
        this.peso = peso;
    }

    public int getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}
