package uy.edu.ort.expresiones;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.ort.lector.operaciones.EvaluadorNotacionPolaca;

public
class TestNotacionPrefijo
{

    @Test
    public void testPrefijoSimple(){
        EvaluadorNotacionPolaca eval=new EvaluadorNotacionPolaca();

        float resultado=eval.evaluar("+ 2 3");
        Assertions.assertEquals(5,resultado);
        resultado=eval.evaluar("+ 2 - 5 2");
        Assertions.assertEquals(5,resultado);
        resultado=eval.evaluar("* - 10 2 - 5 2");//-> (10-2) * (5-2)
        Assertions.assertEquals(24,resultado);
        resultado=eval.evaluar("- * / 15 - 7 + 1 1 3 + 2 + 1 1");// ->  (15/(7 - (1+1)))* 3    -  (2+(1+1))
        Assertions.assertEquals(5,resultado);
        resultado=eval.evaluar("/ - * 2 5 * 1 2 - 11 9");
        Assertions.assertEquals(4,resultado);
        resultado=eval.evaluar("6");
        Assertions.assertEquals(6,resultado);

    }
}
