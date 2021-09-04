package uy.edu.ort.ae2.huffman;

public
class NodoHuffman
{

    private CaracterFrecuencia caracterConFrecuencia;
    private NodoHuffman izq;//a la izquierda le agregamos un 0
    private NodoHuffman der;//a la derecha le agregamos un 1

    public
    NodoHuffman()
    {
    }

    public
    NodoHuffman(CaracterFrecuencia caracterNodo)
    {
        this.caracterConFrecuencia = caracterNodo;
    }

    public
    CaracterFrecuencia getCaracterConFrecuencia()
    {
        return caracterConFrecuencia;
    }

    public
    NodoHuffman getIzq()
    {
        return izq;
    }

    public
    NodoHuffman getDer()
    {
        return der;
    }

    public
    boolean esHoja()
    {
        return this.izq == null && this.der == null;
    }

    public
    int getFrecuencia()
    {
        //SI es una hoja sabemos que hay un caracter
        if (esHoja())
        {
            return this.caracterConFrecuencia.getFrecuencia();
        }

        int cantidad = 0;
        if (this.izq != null)
        {
            cantidad += this.izq.getFrecuencia();
        }
        if (this.der != null)
        {
            cantidad += this.der.getFrecuencia();
        }
        return cantidad;
    }

    public static
    NodoHuffman unirNodos(NodoHuffman raiz1,
                          NodoHuffman raiz2)
    {
        NodoHuffman nuevaRaiz = new NodoHuffman();
        nuevaRaiz.izq = raiz1;
        nuevaRaiz.der = raiz2;
        return nuevaRaiz;
    }

    public
    String codificarCaracter(final char caracter,
                             final String prefijo)
    {
        if (this.esHoja())
        {
            if (caracterConFrecuencia.getCaracter() == caracter)
            {
                return prefijo;
            }
            else
            {
                return null;
            }
        }
        else
        {
            String caracterPorIzq = this.izq != null ? this.izq.codificarCaracter(caracter, prefijo + "0") : null;
            String caracterPorDer = this.der != null ? this.der.codificarCaracter(caracter, prefijo + "1") : null;
            if (caracterPorDer != null)
            {
                return caracterPorDer;
            }
            return caracterPorIzq;
        }
    }
}
