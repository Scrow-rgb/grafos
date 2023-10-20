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
        boolean[] visitado = new boolean[numVertices];
        buscaEmProfundidade(0, visitado);

        for (boolean v : visitado) {
            if (!v) {
                return false;
            }
        }

        return true;
    }

    private void buscaEmProfundidade(int vertice, boolean[] visitado) {
        visitado[vertice] = true;
        for (Aresta aresta : listaAdjacencia.get(vertice)) {
            int destino = aresta.getDestino();
            if (!visitado[destino]) {
                buscaEmProfundidade(destino, visitado);
            }
        }
    }

    public boolean verificarRegular() {
        int grauPrimeiroVertice = consultarGrauVertice(0);

        for (int i = 1; i < numVertices; i++) {
            if (consultarGrauVertice(i) != grauPrimeiroVertice) {
                return false;
            }
        }

        return true;
    }

    public boolean verificarCompleto() {
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i != j && matrizAdjacencia[i][j] != 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public void buscaEmLargura(int vertice) {
        boolean[] visitado = new boolean[numVertices];
        Queue<Integer> fila = new LinkedList<>();
        visitado[vertice] = true;
        fila.add(vertice);

        while (!fila.isEmpty()) {
            int v = fila.poll();
            System.out.print(v + " ");

            for (Aresta aresta : listaAdjacencia.get(v)) {
                int destino = aresta.getDestino();
                if (!visitado[destino]) {
                    visitado[destino] = true;
                    fila.add(destino);
                }
            }
        }
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
