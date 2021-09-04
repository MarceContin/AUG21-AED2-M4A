package uy.edu.ort.ad;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


import uy.edu.ort.algoritmos.AlgoritmoACorrer;

public
class AlgoritmoABB implements AlgoritmoACorrer
{
    private  class  NodoArbol<T extends Comparable<T>>{
        private  T datum;
        private  NodoArbol<T> left;
        private  NodoArbol<T> right;


        public void agregar(T nuevoDato){
            int compare=datum.compareTo(nuevoDato);
            if(compare>0){
                if(left!=null){
                    left.agregar(nuevoDato);
                }else{
                    left=new NodoArbol<>();
                    left.datum=nuevoDato;
                }

            }else if(compare<0){
                if(right!=null){
                    right.agregar(nuevoDato);

                }else{
                    right=new NodoArbol<>();
                    right.datum=nuevoDato;
                }
            }

        }

        boolean esHoja(){
            return this.left==null&& this.right==null;
        }
        @Override
        public
        String toString()
        {
            return datum.toString();
        }
    }
    @Override
    public
    String nombre()
    {
        return "Arbol binario de busqueda";
    }

    @Override
    public
    void run(final InterfazGraficaAlgoritmo it)
        throws
        InterruptedException
    {
        NodoArbol<Integer> raiz=new NodoArbol<>();
        raiz.datum=20;
        raiz.agregar(15);
        raiz.agregar(7);
        raiz.agregar(12);
        raiz.agregar(22);
        raiz.agregar(422);
        raiz.agregar(1235);
        raiz.agregar(125);
        it.dibujarArbolNArio("abb",raiz,r-> r.esHoja()? List.of():Stream.of(r.left, r.right).collect(Collectors.toList()),2);


    }
}
