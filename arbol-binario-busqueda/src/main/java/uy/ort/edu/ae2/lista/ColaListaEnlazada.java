package uy.ort.edu.ae2.lista;

import java.util.Iterator;

public
class ColaListaEnlazada<T> implements Cola<T>
{
    private Lista<T> lista=new ListaDobleCircularClase<T>();

    @Override
    public synchronized
    void push(final T dato)
    {
        this.lista.agregarFin(dato);
    }

    @Override
    public synchronized
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
    public synchronized
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
