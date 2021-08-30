import java.util.Comparator;
import java.util.function.Consumer;

public
class Lista<T>
{

    private NodoLista<T> inicio;//Se maneja circular
    int largo=0;

    public void insertarInicio(T datum){
        this.insertar(0,datum);
    }
     public T eliminarInicio(){
        if(largo<=0){
            return null;
        }
        T dato=this.inicio.datum;
        if(largo==1)
        {
            this.inicio = null;
        }else {
            this.inicio=this.inicio.eliminarme();
        }
        largo--;
        return dato;
    }

    public T eliminarFin(){
        if(largo<=0){
            return null;
        }
        T dato=this.inicio.prev.datum;
        if(largo==1)
        {
            this.inicio = null;
        }else {
            this.inicio.prev.eliminarme();
        }
        largo--;
        return dato;

    }
    public void agregarAlFinal(T datum){
        largo++;
        if(inicio==null){
            setearInicio(datum);
        }else{
            this.inicio.agregarAntes(datum);
        }
    }
    public void insertar(int pos,T datum){
        if(pos>this.largo){
            throw new IllegalArgumentException("pos");
        }
        if(inicio==null){
            setearInicio(datum);
            return;
        }else if(pos==0){
            this.inicio=inicio.agregarAntes(datum);
            return;
        }
        NodoLista<T> nodo=inicio;
        while(pos>0 && nodo.sig!=null){
            pos--;
        }
        nodo.agregarAntes(datum);
        largo++;
    }

    private
    void setearInicio(final T datum)
    {
        this.inicio=new NodoLista<>(datum);
        this.inicio.prev=this.inicio;
        this.inicio.sig=this.inicio;
    }

    public int getLargo(){
        return this.largo;
    }
    //Hace lo mismo que el anterior, solo qque en el anteiror usa una variable auxiliar y en este caso lo calcula de la lista
    public int getSize(){

        if(this.inicio==null)return 0;
        int largo=1;
        NodoLista<T> nodo=this.inicio;
        while (nodo.sig!=null && nodo.sig!=this.inicio ){
            largo++;
            nodo=nodo.sig;
        }

        return largo;

    }

    public
    void visitar(final Visitador<T> valor)
    {
        this.inicio.visitar(this.inicio, new Visitador<NodoLista<T>>()
        {
            @Override
            public
            void visitar(final NodoLista<T> dato)
            {
                valor.visitar(dato.datum);
            }
        });
    }

    public
    boolean esVacia()
    {
        return this.inicio==null;
    }



    public void ordenar(Comparator<T> comparador){
        this.inicio.prev.sig=null;
        this.inicio.prev=null;

        this.inicio=this.inicio.mergeSort(comparador);


    }

    public
    boolean estaOrdenada(final Comparator<T> comparator)
    {
        return inicio.estaOrdenada(inicio,comparator);
    }
}
