package uy.edu.ort.ad2;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Function;

import javafx.scene.paint.Color;

public
interface InterfazGraficaAlgoritmo
{

    void dibujarArray(String nombre,
                      int[] valores);

    void animarArray(final String nombre,
                     final int[] valores,
                     double tiempo);

    <T> void dibujarArbol(final String nombre,
                          final T nodoRaiz,
                          Function<T, Collection<T>> obtenerHijos);

    <T> void dibujarArbolNArio(final String nombre,
                               final T nodoRaiz,
                               Function<T, Collection<T>> obtenerHijos,
                               int n);

    void imprimirMatriz(int[][] valores);

    void dibujarMatrizBinaria(String nombre,
                              int[][] valores,
                              Color color0,
                              Color color1);
    // void dibujarMatrizValores(String nombre, int[][] valores,Function<CeldaMatriz<Integer>, CeldaProps> props);

    void sleep(int i);

    void dibujarCeldaDeMatriz(String nombre,
                              int[][] valores,
                              int i,
                              int j,
                              Color color);

    void dibujarCeldaArray(String nombre,
                           int[] datos,
                           int posicionPivoteFinal,
                           Color red);
}
