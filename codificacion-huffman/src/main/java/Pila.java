public
class Pila<T>
{

    private Lista<T> lista=new Lista<>();


    public  boolean esVacia(){
        return this.lista.esVacia();
    }
    public void push(T dato){
        lista.insertarInicio(dato);

    }

    public T pop(){
        return lista.eliminarInicio();
    }

    public void visitar(Visitador<T> valor){
        this.lista.visitar(valor);
    }
}
