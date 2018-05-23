/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pratica7;

import Grafos.JAEDsMaps;
import Grafos.JGrafo;

/**
 *
 * @author arthur
 */
public class Pratica7 {

    // declaracao das variaveis estaticas para exemplo 2
    private static final byte a  = 0;
    private static final byte b  = 1;
    private static final byte c  = 2;
    private static final byte d  = 3;
    private static final byte e  = 4;
    
    public static void main(String[] args) throws Exception {
        
        System.out.println("Exemplo 1");
        // colcou 6 vértices por que 
        JGrafo g1 = new JGrafo(6);
        
        g1.insereAresta(1, 2, 8, 3);
        g1.insereAresta(2, 5, 9, 5);
        g1.insereAresta(1, 4, 5, 2);
        g1.insereAresta(1, 3, 4, 10);
        g1.insereAresta(4, 3, 5, 7);
        g1.insereAresta(3, 4, 1, 4);
        g1.insereAresta(4, 5, 2, 7);
        g1.insereAresta(3, 5, 5, 2);
        
        JAEDsMaps jm = new JAEDsMaps(g1);
        
        jm.obterArvoreCMC(1); 
        jm.imprimeCaminho(1, 5);
        
        System.out.println("\n---------------\nExemplo 2");
        
        JGrafo g2 = new JGrafo(6);
        
        g2.insereAresta(a, b, 3, 3);
        g2.insereAresta(a, d, 5, 5);
        g2.insereAresta(b, c, 2, 6);
        g2.insereAresta(b, d, 2, 2);
        g2.insereAresta(c, e, 2, 2);
        g2.insereAresta(d, b, 3, 1);
        g2.insereAresta(d, c, 5, 4);
        g2.insereAresta(d, e, 9, 6);
        g2.insereAresta(e, a, 6, 3);
        g2.insereAresta(e, c, 4, 7);
        
        
        jm = new JAEDsMaps(g2);
        
        jm.obterArvoreCMC(d); 
        jm.imprimeCaminho(d, a);
        System.out.println("a=0; b=1, c=2, d=3, e=4");
    }
    
}
