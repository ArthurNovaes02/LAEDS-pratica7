package Grafos;
public class JGrafo {
  public static class Aresta {
    private int v1, v2, distancia, tempo;
    public Aresta (int v1, int v2, int distancia, int tempo) {
      this.v1 = v1; this.v2 = v2; this.distancia = distancia; this.tempo = tempo;
    }
    public int distancia () { return this.distancia; }
    public int tempo () { return this.tempo; }
    public int v1 () { return this.v1; }
    public int v2 () { return this.v2; }
  }
  private int mat[][][]; // @{\it pesos do tipo inteiro}@
  private int numVertices;
  private int pos[]; // @{\it posi\c{c}\~ao atual ao se percorrer os adjs de um v\'ertice v}@
  private static final byte dist  = 0;
  private static final byte temp  = 1;

  public JGrafo (int numVertices) {
    this.mat = new int[numVertices][numVertices][2];
    this.pos = new int[numVertices]; 
    this.numVertices = numVertices; 
    for (int i = 0; i < this.numVertices; i++) {
      for (int j = 0; j < this.numVertices; j++){
          this.mat[i][j][temp] = 0;
          this.mat[i][j][dist] = 0;
      }
      this.pos[i] = -1; 
    }
  }
  public void insereAresta (int v1, int v2, int distancia, int tempo) {
    this.mat[v1][v2][dist] = distancia; 
    this.mat[v1][v2][temp] = tempo; 
  }
  public boolean existeAresta (int v1, int v2) {
    return (this.mat[v1][v2][temp] > 0 || this.mat[v1][v2][dist] > 0);
  }
  public boolean listaAdjVazia (int v) {
    for (int i =0; i < this.numVertices; i++)
      if (this.mat[v][i][temp] > 0 || this.mat[v][i][dist] > 0) return false;
    return true;
  }
  public Aresta primeiroListaAdj (int v) {
    // @{\it Retorna a primeira aresta que o v\'ertice v participa ou}@ 
    // @{\it {\bf null} se a lista de adjac\^encia de v for vazia}@
    this.pos[v] = -1; return this.proxAdj (v);
  }
  public Aresta proxAdj (int v) {
    // @{\it Retorna a pr\'oxima aresta que o v\'ertice v participa ou}@ 
    // @{\it {\bf null} se a lista de adjac\^encia de v estiver no fim}@ 
    this.pos[v] ++;
    while ((this.pos[v] < this.numVertices) && 
           (this.mat[v][this.pos[v]][temp] == 0 || this.mat[v][this.pos[v]][dist] == 0)) this.pos[v]++;
    if (this.pos[v] == this.numVertices) return null;
    else return new Aresta (v, this.pos[v], this.mat[v][this.pos[v]][dist], this.mat[v][this.pos[v]][temp]);
  }
  public Aresta retiraAresta (int v1, int v2) {
    if (this.mat[v1][v2][temp] == 0 && this.mat[v1][v2][dist] == 0) return null; // @{\it Aresta n\~ao existe}@
    else {
      Aresta aresta = new Aresta (v1, v2, this.mat[v1][v2][dist], this.mat[v1][v2][temp]);
      this.mat[v1][v2][dist] = 0; 
      this.mat[v1][v2][temp] = 0; 
      return aresta;
    }
  }
  public void imprime () {
    System.out.print ("   ");
    for (int i = 0; i < this.numVertices; i++) 
      System.out.print (i + "   "); 
    System.out.println ();
    for (int i = 0; i < this.numVertices; i++) { 
      System.out.print (i + "  ");
      for (int j = 0; j < this.numVertices; j++)
        System.out.print (this.mat[i][j] + "   ");
      System.out.println ();
    }
  }
  public int numVertices () { return this.numVertices; }
  public JGrafo grafoTransposto () {
    JGrafo grafoT = new JGrafo (this.numVertices); 
    for (int v = 0; v < this.numVertices; v++)
      if (!this.listaAdjVazia (v)) {
        Aresta adj = this.primeiroListaAdj (v);
        while (adj != null) {
          grafoT.insereAresta (adj.v2 (), adj.v1 (), adj.distancia (), adj.tempo());
          adj = this.proxAdj (v);
        }
      }
    return grafoT;
  }
}
