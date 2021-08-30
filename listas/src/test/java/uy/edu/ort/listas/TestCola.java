package uy.edu.ort.listas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public
class TestCola
{

    private static <T> Cola<T> getImplementacion(){
        return new ColaListaEnlazada<>();
    }

    @Test
    public void testOperacionesCola(){

        Cola<Integer> cola=getImplementacion();

        cola.push(1);
        cola.push(2);
        cola.push(3);
        cola.push(4);
        cola.push(5);

        Assertions.assertEquals(1,cola.pop());
        Assertions.assertEquals(2,cola.pop());
        Assertions.assertEquals(3,cola.pop());
        Assertions.assertEquals(4,cola.pop());
        Assertions.assertEquals(5,cola.pop());

        Assertions.assertTrue(cola.esVacia());
        cola.push(5);
        Assertions.assertEquals(5,cola.peek());
        cola.push(32);
        Assertions.assertEquals(5,cola.peek());
        Assertions.assertEquals(2,cola.getLargo());



    }
}
