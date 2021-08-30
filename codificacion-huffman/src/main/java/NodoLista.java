import java.util.Comparator;
import java.util.function.Consumer;

public
class NodoLista<T>
{
    public T datum;
    public NodoLista<T> sig;
    public NodoLista<T> prev;

    public
    NodoLista()
    {
    }

    public
    NodoLista(final T datum)
    {
        this.datum = datum;
    }


    public NodoLista<T> agregarAntes(T datum){
        NodoLista<T> nuevoNodo=new NodoLista<>(datum);
        nuevoNodo.prev=this.prev;
        nuevoNodo.sig=this;
        this.prev.sig=nuevoNodo;
        this.prev=nuevoNodo;

        return nuevoNodo;
    }

    public NodoLista<T> agregarDespues(T datum){
        NodoLista<T> nodoAAgregar=new NodoLista<>();
        nodoAAgregar.sig=this.sig;
        nodoAAgregar.prev=this;
        this.sig=nodoAAgregar;
        this.sig.prev=nodoAAgregar;
        return nodoAAgregar;
    }


    public void visitar(NodoLista<T> inicio,Visitador<NodoLista<T>> visitador){

            visitador.visitar(this);
            if(this.sig!=null && this.sig!=inicio) {
                this.sig.visitar(inicio,visitador);
            }

    }
    public void visitarHaciaAtras(NodoLista<T> inicio,Visitador<NodoLista<T>> visitador){

            visitador.visitar(this);
            if(this.prev!=null && this.prev!=inicio){
                this.prev.visitar(inicio,visitador);
            }

    }

    public NodoLista<T> obtenerMid(){
        NodoLista<T> nodoMid=this;
        NodoLista<T> nodo2X=this;
        while(nodo2X!=null&&nodo2X.sig!=null){
            nodoMid=nodoMid.sig;
            nodo2X=nodo2X.sig.sig;
        }

        return nodoMid;
    }

    public NodoLista<T> mergeSort(Comparator<T> comparador){
       if(this.sig==null){
           return this;
       }
        NodoLista<T> mid=this.obtenerMid();

       if(mid.prev!=null){
            mid.prev.sig=null;
       }
       NodoLista<T> nodoPrimeraMitadOrdenada=this.mergeSort(comparador);
       NodoLista<T> nodoSegundaMitadOrdenada=mid.mergeSort(comparador);
       NodoLista<T> comienzo=null;
       NodoLista<T> nodoPrevio=null;
       while(nodoPrimeraMitadOrdenada!=null && nodoSegundaMitadOrdenada!=null){
           NodoLista<T> siguienteNodo=null;
           if(ComparatorUtils.esMenor(nodoPrimeraMitadOrdenada.datum,nodoSegundaMitadOrdenada.datum,comparador)){
               siguienteNodo=nodoPrimeraMitadOrdenada;
               nodoPrimeraMitadOrdenada=nodoPrimeraMitadOrdenada.sig;
           }else{
               siguienteNodo=nodoSegundaMitadOrdenada;
               nodoSegundaMitadOrdenada=nodoSegundaMitadOrdenada.sig;
           }
           if(nodoPrevio!=null){
               nodoPrevio.sig=siguienteNodo;

           }
           siguienteNodo.prev=nodoPrevio;
           nodoPrevio=siguienteNodo;
           if(comienzo==null){
               comienzo=siguienteNodo;
           }
       }
       while (nodoPrimeraMitadOrdenada!=null){
           NodoLista<T> siguienteNodo=nodoPrimeraMitadOrdenada;
           nodoPrimeraMitadOrdenada=nodoPrimeraMitadOrdenada.sig;
           if(nodoPrevio!=null){
               nodoPrevio.sig=siguienteNodo;
               siguienteNodo.prev=nodoPrevio;
           }
           nodoPrevio=siguienteNodo;
           if(comienzo==null){
               comienzo=siguienteNodo;
           }

       }
       while (nodoSegundaMitadOrdenada!=null){
           NodoLista<T> siguienteNodo=nodoSegundaMitadOrdenada;
           nodoSegundaMitadOrdenada=nodoSegundaMitadOrdenada.sig;
           if(nodoPrevio!=null){
               nodoPrevio.sig=siguienteNodo;
               siguienteNodo.prev=nodoPrevio;
           }
           nodoPrevio=siguienteNodo;
           if(comienzo==null){
               comienzo=siguienteNodo;
           }
       }
       return comienzo;
    }


    public
    NodoLista<T> eliminarme()
    {
        if(this.prev!=null){
            this.prev.sig=this.sig;

        }
        if(this.sig!=null){
            this.sig.prev=this.prev;
        }

        return this.sig;
    }

    public NodoLista<T> clonar(){
        NodoLista<T> nuevo=new NodoLista<>(this.datum);
        nuevo.sig=this.sig;
        nuevo.prev=this.prev;
        return nuevo;

    }

    public
    boolean estaOrdenada(final NodoLista<T> inicio,
                         final Comparator<T> comparator)
    {
        if(this.sig==inicio||this.sig==null)return true;
        return ComparatorUtils.esMenor(this.datum,this.sig.datum,comparator) && this.sig.estaOrdenada(inicio,comparator);
    }
}
