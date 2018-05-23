package Grafos;
public class JAEDsMaps {
  private int antecessor[];
  private double pTemp[];
  private double pDist[];
  private JGrafo grafo;

  public JAEDsMaps (JGrafo grafo) { this.grafo = grafo; }  
  public void obterArvoreCMC (int raiz) throws Exception {
    int n = this.grafo.numVertices();
    this.pDist = new double[n]; // @{\it peso dos v\'ertices}@
    this.pTemp = new double[n]; // @{\it peso dos v\'ertices}@
    int vs[] = new int[n+1]; // @{\it v\'ertices}@
    this.antecessor = new int[n];
    for (int u = 0; u < n; u ++) {
      this.antecessor[u] = -1;
      pDist[u] = Double.MAX_VALUE; // @$\infty$@
      pTemp[u] = Double.MAX_VALUE; // @$\infty$@
      vs[u+1] = u; // @{\it Heap indireto a ser constru\'{\i}do}@
    }
    pDist[raiz] = 0;
    pTemp[raiz] = 0;
    FPHeapMinIndireto heap = new FPHeapMinIndireto (pDist, pTemp, vs); 
    heap.constroi ();
    while (!heap.vazio ()) {
      int u = heap.retiraMin (); 
      if (!this.grafo.listaAdjVazia (u)) {
        JGrafo.Aresta adj = grafo.primeiroListaAdj (u);
        while (adj != null) {
          int v = adj.v2 ();
          if (this.pDist[v] > (this.pDist[u] + adj.distancia()) && this.pTemp[v] > (this.pTemp[u] + adj.tempo())) {
            antecessor[v] = u; 
            heap.diminuiChave (v, this.pTemp[u] + adj.tempo(), this.pDist[u] + adj.distancia());
          }
          adj = grafo.proxAdj (u);
        }
      }
    }
  }
  public int antecessor (int u) { return this.antecessor[u]; }
  public double pesoDist (int u) { return this.pDist[u]; }
  public double pesoTemp (int u) { return this.pTemp[u]; }
  public void imprime () {
    for (int u = 0; u < this.pDist.length; u++)
      if (this.antecessor[u] != -1) 
        System.out.println ("(" +antecessor[u]+ "," +u+ ") -- pDist:" + pesoDist (u) + "-- pTemp:" + pesoTemp (u));

  }
  public void imprimeCaminho (int origem, int v) {
    if (origem == v) System.out.println (origem);
    else if (this.antecessor[v] == -1) 
      System.out.println ("Nao existe caminho de " +origem+ " ate " +v);
    else {
      imprimeCaminho (origem, this.antecessor[v]);
      System.out.println (v);
    }    
  }

}