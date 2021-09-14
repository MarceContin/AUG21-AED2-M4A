package uy.ort.edu.ae2.lista;

public
interface Pila<T> extends Iterable<T>
{

    void push(T dato);
    T peek();
    T pop();

    int getLargo();

    boolean esVacia();

}
