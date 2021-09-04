package uy.edu.ort.ae2.huffman;

import uy.edu.ort.ae2.huffman.NodoHuffman;

public
class IteradorHuffman
{
    private NodoHuffman raiz;
    private NodoHuffman actual;

    public
    IteradorHuffman(NodoHuffman raiz)
    {
        this.raiz = raiz;
        this.reiniciar();
    }

    public
    void siguiente(char bit)
    {
        //Esto es similar a lo que hicimos en clase. hay que actualizar el nodo actual al nodo que corresponde dependiendo del
        //bit que leimos
        throw new UnsupportedOperationException("Implementar siguiente iterador huffman");
    }

    public
    void reiniciar()
    {
        this.actual = raiz;
    }

    public
    boolean resolviCaracter()
    {
        return actual != null && actual.esHoja();
    }

    public
    char getCaracter()
    {
        return actual.getCaracterConFrecuencia()
                     .getCaracter();
    }
}
