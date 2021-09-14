package uy.ort.edu.ae2.lista;

public
class NodoLista<T>
{
    T dato;
    NodoLista<T> sig;
    NodoLista<T> prev;

    public
    NodoLista(final T dato)
    {
        this.dato = dato;
    }

    public
    NodoLista()
    {
    }

    public NodoLista<T> clonar(){
        NodoLista<T> clon=new NodoLista<>(dato);
        clon.sig=this.sig;
        clon.prev=this.prev;
        return clon;
    }

    public NodoLista<T> eliminarme(){

        this.prev.sig=this.sig;
        this.sig.prev=this.prev;

        return this.sig;
    }

    public NodoLista<T> agregarAntes(T dato){
        NodoLista<T> nuevoNodo=new NodoLista<>(dato);
        if(this.prev!=null){
            this.prev.sig=nuevoNodo;
        }

        nuevoNodo.sig=this;
        nuevoNodo.prev=this.prev;
        this.prev=nuevoNodo;
        return nuevoNodo;
    }

    public NodoLista<T> agregarDespues(T dato){
        NodoLista<T> nuevoNodo=new NodoLista<>(dato);
        nuevoNodo.sig=this.sig;
        nuevoNodo.prev=this;
        if(this.sig!=null){
            this.sig.prev=nuevoNodo;
        }
        this.sig=nuevoNodo;
        return nuevoNodo;

    }


}
