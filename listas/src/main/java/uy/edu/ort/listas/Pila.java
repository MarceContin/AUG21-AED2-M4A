package uy.edu.ort.listas;

public
interface Pila<T> extends Iterable<T>
{

    void push(T dato);
    T peek();
    T pop();

    int getLargo();

    boolean esVacia();

}
