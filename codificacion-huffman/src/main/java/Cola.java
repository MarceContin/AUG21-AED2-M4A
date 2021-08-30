import java.util.function.Consumer;

public
class Cola<T>
{
    private Lista<T> lista=new Lista<>();


    public  boolean esVacia(){
        return this.lista.esVacia();
    }
    public void push(T dato){
        lista.agregarAlFinal(dato);

    }

    public T pop(){
        return lista.eliminarInicio();
    }

    public void visitar(Visitador<T> valor){
        this.lista.visitar(valor);
    }




}
