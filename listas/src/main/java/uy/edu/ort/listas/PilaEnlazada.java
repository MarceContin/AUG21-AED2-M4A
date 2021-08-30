package uy.edu.ort.listas;

import java.util.Iterator;

public
class PilaEnlazada<T> implements Pila<T>
{
    private Lista<T> lista=new ListaDobleCircularClase<T>();

    @Override
    public
    void push(final T dato)
    {
        this.lista.agregarComienzo(dato);
    }

    @Override
    public
    T pop()
    {
        return this.lista.eliminarComienzo();
    }

    @Override
    public
    int getLargo()
    {
        return this.lista.getLargo();
    }

    @Override
    public
    T peek()
    {
        return this.lista.get(0);
    }

    @Override
    public
    boolean esVacia()
    {
        return this.lista.esVacia();
    }

    @Override
    public
    Iterator<T> iterator()
    {
        return this.lista.iterator();
    }
}
