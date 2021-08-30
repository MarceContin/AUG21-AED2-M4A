import com.sun.jdi.PrimitiveValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

public
class TestsLista
{

    @Test
    public void testAgregar_ok(){
        Lista<Integer> lista=new Lista<>();

        lista.agregarAlFinal(1);
        lista.agregarAlFinal(2);
        lista.agregarAlFinal(3);
        lista.agregarAlFinal(4);
        lista.agregarAlFinal(5);

        Assertions.assertEquals(5,lista.eliminarFin());
        Assertions.assertEquals(4,lista.eliminarFin());
        Assertions.assertEquals(3,lista.eliminarFin());
        Assertions.assertEquals(2,lista.eliminarFin());
        Assertions.assertEquals(1,lista.eliminarFin());
        Assertions.assertEquals(0,lista.getLargo());


    }
    private  class  ComparadorNumeros implements Comparator<Integer>{
        @Override
        public
        int compare(final Integer o1,
                    final Integer o2)
        {
            return o1-o2;
        }
    }
    @Test
    public void testListaDesordenada_ok(){
        Lista<Integer> lista=new Lista<>();
        lista.agregarAlFinal(5);
        lista.agregarAlFinal(1);
        lista.agregarAlFinal(4);
        lista.agregarAlFinal(2);
        lista.agregarAlFinal(3);

        lista.ordenar(new ComparadorNumeros());
        lista.visitar(v->{System.out.print(v + ",");});
Assertions.assertTrue(lista.estaOrdenada(new ComparadorNumeros()));
    }
    @Test
    public void testListaOrdenada_ok(){
        Lista<Integer> lista=new Lista<>();

        lista.agregarAlFinal(1);
        lista.agregarAlFinal(2);
        lista.agregarAlFinal(3);
        lista.agregarAlFinal(4);
        lista.agregarAlFinal(5);

        lista.ordenar(new ComparadorNumeros());
        lista.visitar(v->{System.out.print(v + ",");});
        Assertions.assertTrue(lista.estaOrdenada(new ComparadorNumeros()));
    }

    @Test
    public void testListaDesordenada2_ok(){
        Lista<Integer> lista=new Lista<>();

        lista.agregarAlFinal(1);
        lista.agregarAlFinal(2);
        lista.agregarAlFinal(3);
        lista.agregarAlFinal(4);
        lista.agregarAlFinal(5);

        lista.ordenar(new ComparadorNumeros());
        lista.visitar(v->{System.out.print(v + ",");});
        Assertions.assertTrue(lista.estaOrdenada(new ComparadorNumeros()));
    }
}
