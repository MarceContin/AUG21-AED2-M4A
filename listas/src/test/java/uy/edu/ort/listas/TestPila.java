package uy.edu.ort.listas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public
class TestPila
{
    private static <T> Pila<T> getImplementacion(){
        return new PilaEnlazada<T>();
    }

    @Test
    public void testOperacionesCola(){

        Pila<Integer> pila=getImplementacion();

        pila.push(1);
        pila.push(2);
        pila.push(3);
        pila.push(4);
        pila.push(5);
        Assertions.assertEquals(5,pila.pop());
        Assertions.assertEquals(4,pila.pop());
        Assertions.assertEquals(3,pila.pop());
        Assertions.assertEquals(2,pila.pop());
        Assertions.assertEquals(1, pila.pop());


        Assertions.assertTrue(pila.esVacia());
        pila.push(5);
        Assertions.assertEquals(5,pila.peek());
        pila.push(32);
        Assertions.assertEquals(32,pila.peek());
        Assertions.assertEquals(2,pila.getLargo());



    }

}
