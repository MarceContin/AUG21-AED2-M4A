package uy.edu.ort.expresiones;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import uy.edu.ort.lector.operaciones.EvaluadorNotacionInFijo;

public
class TestNotacionInfijo
{

    @Test
    public void testNotacionInFijoSinPrecedencia(){
        EvaluadorNotacionInFijo inFijo=new EvaluadorNotacionInFijo();
        float evaluacion=inFijo.evaluarSinPrecedencia("(1+3)*5");
        Assertions.assertEquals(20,evaluacion);
        evaluacion=inFijo.evaluarSinPrecedencia("(1+(4*5)+4)/5");
        Assertions.assertEquals(5,evaluacion);
        evaluacion=inFijo.evaluarSinPrecedencia("(1+(1-3)+5)*5*(6-4)");
        Assertions.assertEquals(40,evaluacion);

    }
    @Test
    public void testNotacionInFijoConPrecedencia(){
        EvaluadorNotacionInFijo inFijo=new EvaluadorNotacionInFijo();
        float evaluacion=inFijo.evaluarConPrecedencia("1+3*5");
        Assertions.assertEquals(16,evaluacion);
        evaluacion=inFijo.evaluarConPrecedencia("3*5+1");
        Assertions.assertEquals(16,evaluacion);
        evaluacion=inFijo.evaluarConPrecedencia("(1+(4*5)+4)/5");
        Assertions.assertEquals(5,evaluacion);
        evaluacion=inFijo.evaluarConPrecedencia("(1+(1-3)+5)*5*(6-4)");
        Assertions.assertEquals(40,evaluacion);

    }
}
