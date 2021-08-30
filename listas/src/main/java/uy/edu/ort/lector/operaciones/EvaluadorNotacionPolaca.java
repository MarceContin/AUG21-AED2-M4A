package uy.edu.ort.lector.operaciones;

import uy.edu.ort.listas.Pila;
import uy.edu.ort.listas.PilaEnlazada;

public
class EvaluadorNotacionPolaca
{
    /**
     * Las expresiones son en notacion polaca, esta notacion prescinde de parentesis poniendo primero la operacion y luego los terminos.
     * Ejemplos, se leen de izquierda a izquierda:
     *  + 2 3 se traduce a 2+3
     *  / 5 10 se traduce a 5/10
     *  + - 2 3 5 se podria ver como + (- 2 3) 5 por lo que se traduce a (2 - 3) + 5
     *  * 2 + 10 5 se podria ver como * 2 (+ 10 5) que se traduce a 2*(10+5)
     *  * - 5 1 + 10 2 se traduce a (5 - 1) * (10+2)
     * Para mas info: https://es.wikipedia.org/wiki/Notaci%C3%B3n_polaca
     * @param expresion la expresion en notacion polaca
     * @return la evaluacion de la expresión
     */
    public float evaluar(String expresion){
        if(expresion==null||expresion.isBlank())return 0;
        Pila<Object> resultado=new PilaEnlazada<Object>();

        String[] partesExpresion=expresion.split(" "); //dividimos el string por espacios
        //Empezamos a meter los operadores en una pila
        for(String parteExp:partesExpresion){
            if(OperacionBinaria.isOperacion(parteExp)){
                OperacionBinaria op=new OperacionBinaria(parteExp.charAt(0));
                resultado.push(op);
            }else{ //Es un termino

                Float resultadoParcial=Float.parseFloat(parteExp);
                //Vemos si hay otro termino en el stack
                while (resultado.peek() instanceof  Float)
                {
                    Float termino1=(Float) resultado.pop(); //Obtenemos el termino y la operación
                    OperacionBinaria op = (OperacionBinaria) resultado.pop();
                    //El resultado parcial que obtenemos es la operación, en primer lugar va a ser con el segundo termino pero necesitamos
                    //ir deshaciendo la exppresion por ejemplo + 2 - 3 5
                    // cuando tenemos el 5, sacamos 3 y - y hacemos 3 - 5 -> vemos si no hay una operación mas para hacer
                    // y vamos calculando hacia atras
                    resultadoParcial=op.operar(termino1,resultadoParcial);

                }
                resultado.push(resultadoParcial);
        }


    }

        return (Float)resultado.pop();
    }
}
