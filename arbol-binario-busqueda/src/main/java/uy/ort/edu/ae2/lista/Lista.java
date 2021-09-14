package uy.ort.edu.ae2.lista;

import java.util.Comparator;

public
interface Lista<T> extends Iterable<T>
{

    void agregarFin(T dato);
    void agregarComienzo(T dato);
    T get(int n);

    T eliminarComienzo();
    T eliminarFin();

    int getLargo();

    boolean esVacia();

    void ordenar(Comparator<T> comparator);

    boolean estaOrdenada(Comparator<T> comparador);
}
