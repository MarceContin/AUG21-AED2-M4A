package uy.edu.ort.ae2.huffman;

public
class ArbolHuffman
{
    private NodoHuffman raiz;

    public
    ArbolHuffman()
    {
        this.raiz = new NodoHuffman();
    }

    public
    ArbolHuffman(NodoHuffman raiz)
    {
        this.raiz = raiz;
    }

    public
    String codificarCaracter(final char caracter)
    {
        return raiz.codificarCaracter(caracter, "");
    }

    public
    IteradorHuffman iterador()
    {
        return new IteradorHuffman(raiz);
    }
}
