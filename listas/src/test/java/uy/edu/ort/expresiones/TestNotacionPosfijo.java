package uy.edu.ort.expresiones;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.ort.lector.operaciones.EvaluadorNotacionPolaca;
import uy.edu.ort.lector.operaciones.EvaluadorNotacionPolacaInversa;

public
class TestNotacionPosfijo
{
    @Test
    public void testPosSimple(){
        EvaluadorNotacionPolacaInversa eval=new EvaluadorNotacionPolacaInversa();

        float resultado=eval.evaluar("2 3 +");
        Assertions.assertEquals(5, resultado);
        //(5 - 1) * (10+2)
        resultado= eval.evaluar("5 1 - 10 2 + *");
        Assertions.assertEquals(48, resultado);
        //5 * (10+2) - 25 +25/5 - 24/3 = 32
        resultado= eval.evaluar("5 10 2 + * 25 - 25 5 / + 24 3 / -");
        Assertions.assertEquals(32, resultado);

    }
}
