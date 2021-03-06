package uy.edu.ort.ae2.practico;

import uy.edu.ort.ae2.huffman.ArbolHuffman;
import uy.edu.ort.ae2.huffman.CaracterFrecuencia;
import uy.edu.ort.ae2.huffman.NodoHuffman;
import uy.edu.ort.ae2.listas.Lista;
import uy.edu.ort.ae2.listas.ListaDobleCircularClase;

public
class Paso2ConstruccionDelArbol
{
    public
    ArbolHuffman construirArbolDesdeAbajo(Lista<CaracterFrecuencia> caracteresPorFrecuencias)
    {

        NodoHuffman raiz = new NodoHuffman();
        Lista<NodoHuffman> raices = new ListaDobleCircularClase<>();

        for (CaracterFrecuencia caracterFrecuencia : caracteresPorFrecuencias)
        {
            raices.agregarFin(new NodoHuffman(caracterFrecuencia));
        }
        while (raices.getLargo() > 1)
        {


            NodoHuffman raiz1 = popMinimo(raices);
            NodoHuffman raiz2 = popMinimo(raices);
            //Una vez tenemos las dos raices de menor de frecuencia los unimos bajo una misma raiz.
            NodoHuffman nuevaRaiz = NodoHuffman.unirNodos(raiz1, raiz2);
            raices.agregarComienzo(nuevaRaiz);
        }

        return new ArbolHuffman(raices.get(0));
    }

    public
    NodoHuffman popMinimo(Lista<NodoHuffman> raices)
    {
        //Obtener y remover el minimo
        NodoHuffman minimo = null;
        for (NodoHuffman r : raices)
        {
            /*
             * La idea de esto es que pongan en el mínimo el arbol que tiene la menor frecuencia.
             * Tipico código de P1/P2
             */
            throw new UnsupportedOperationException("Implemenar popMinimo Paso2");
        }

        raices.eliminar(minimo);
        return minimo;
    }
}
