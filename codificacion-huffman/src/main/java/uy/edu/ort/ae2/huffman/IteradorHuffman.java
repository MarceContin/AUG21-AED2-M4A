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
        if ('1' == bit)
        {
            actual = actual.getDer();
        }
        else
        {
            actual = actual.getIzq();
        }
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
