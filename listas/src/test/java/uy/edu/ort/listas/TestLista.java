package uy.edu.ort.listas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public
class TestLista
{

    static <T> Lista<T> getImplementacion(){

        return new ListaDobleCircularClase<>();
    }
    @Test
    public void test_agregar_ok(){
        Lista<Integer> lista=getImplementacion();

        lista.agregarFin(1);
        Assertions.assertEquals(1,lista.get(0));
        lista.agregarFin(2);
        Assertions.assertEquals(2,lista.get(1));
        lista.agregarFin(3);
        Assertions.assertEquals(3,lista.get(2));
        lista.agregarComienzo(5);
        Assertions.assertEquals(2,lista.get(2));
        Assertions.assertEquals(5,lista.get(0));

    }

    private class ComparadorNumeros implements Comparator<Integer>{
        @Override
        public
        int compare(final Integer o1,
                    final Integer o2)
        {
            return o1-o2;
        }
    }
    @Test
    public void test_ordenar(){
        Lista<Integer> lista=getImplementacion();
        lista.agregarFin(1);
        lista.agregarFin(5);
        lista.agregarFin(32);
        lista.agregarFin(29);
        lista.agregarFin(15);
        lista.agregarFin(16);
        lista.agregarFin(-1);
        lista.agregarFin(2);
        lista.agregarFin(19);
        lista.agregarFin(40);
        lista.ordenar(new ComparadorNumeros());

        Assertions.assertTrue(lista.estaOrdenada(new ComparadorNumeros()));
    }

    @Test
    public void test_iterador(){


        Lista<Integer> lista=getImplementacion();
        lista.agregarFin(1);
        lista.agregarFin(2);
        lista.agregarFin(3);
        lista.agregarFin(4);
        lista.agregarFin(5);
        int totalRecorrido=0;

        Iterator<Integer> iterador=lista.iterator();
        int numeroEsperado=1;
        while (iterador.hasNext()){
            int numeroObtenido=iterador.next();
            Assertions.assertEquals(numeroEsperado,numeroObtenido);
            numeroEsperado++;
            totalRecorrido++;
        }
        Assertions.assertEquals(totalRecorrido,lista.getLargo());


    }
}
