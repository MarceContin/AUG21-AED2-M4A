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
        //Igual a como hicimos en clase
        throw new UnsupportedOperationException("Implementar es hoja del nodohuffman");
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
        //SI NO ES UNA HOJA LA FRECUENCIA ES LA SUMA DE LAS FRECUENCIAS DE LOS HIJOS
        //Este metodo es similar al cantidad de nodos.
        throw new UnsupportedOperationException("Implementar getFrecuencia NodoHuffman");

        //   return cantidad;
    }

    public static
    NodoHuffman unirNodos(NodoHuffman raiz1,
                          NodoHuffman raiz2)
    {
        NodoHuffman nuevaRaiz = new NodoHuffman();
        //Aqui hay que crear una nuevaRaiz que tenga de izquierda y derecha a los viejos nodos.
        throw new UnsupportedOperationException("Implementar unir nodos huffman");
    }

    public
    String codificarCaracter(final char caracter,
                             final String prefijo)
    {
        //Cuando es una hoja debemos,chequeamos si el caracter es el que buscamos.
        //Si es este retornamos el prefijo que vamos a ir construyendo recursivamente. Por ejemplo 000101
        //Si no es ese, debemos retornar null que seria lo equivalente a decir que ese caracter no fue encontrado en
        //en el camino que estuvimos recorriendo.
        if (this.esHoja())
        {
            if (caracterConFrecuencia.getCaracter() == caracter)
            {
                return prefijo;
            }
            else
            {
                return null; // Si no se encontro el caracter es null
            }
        }
        else
        {
            //Tenemos que buscar el caracter recursivamente por la izquierda y la derecha
            //Como convencion los caracteres a la izquierda deben tener 0 antes, mientras que lso 1 van a la derecha
            throw new UnsupportedOperationException("Implementar el codificar caracter");
        }
    }
}
