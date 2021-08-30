package uy.edu.ort.lector.operaciones;

import uy.edu.ort.listas.Pila;
import uy.edu.ort.listas.PilaEnlazada;

public
class EvaluadorNotacionPolacaInversa
{

    /**
     * Las expresiones son en notacion polaca inversa,
     * esta notacion prescinde de parentesis como la polaca simple, solo que pone primero los terminos y luego las operaciónes
     * Ejemplos:
     *  2 3 + se traduce a 2+3
     *  5 10 /  se traduce a 5/10
     *  2 3 - 5 + se podria ver como ( 2 3 -) 5 + por lo que se traduce a (2 - 3) + 5
     *  2 10 5 + * se podria ver como  2 (10 5+) * que se traduce a 2*(10+5)
     *  5 1 - 10 2 + * se traduce a (5 - 1) * (10+2)
     * Para mas info: https://es.wikipedia.org/wiki/Notaci%C3%B3n_polaca_inversa
     * @param expresion la expresion en notacion polaca inversa
     * @return la evaluacion de la expresión
     */
    public float evaluar(String expresion){
        if(expresion==null||expresion.isBlank())return 0;
        Pila<Float> resultado=new PilaEnlazada<Float>();

        String[] partesExpresion=expresion.split(" "); //dividimos el string por espacios
        //Empezamos a meter los operadores en una pila
        for(String parteExp:partesExpresion){
            if(OperacionBinaria.isOperacion(parteExp)){
                //Si encontramos una operacion tomamos los últimos dos terminos que son numeros
                //La ejecutamos y volvemos a ingresar el resultado en la lista
                //Por ejemplo 2 3 +, cuando llegamos al '+' tenemos 2 y 3 operamos y nos da 5
                //Por ejemplo 2 3 5 + - (significa 2 -(3+5)), cuando llegamos al +, hacemos 3+5, ponemos el resultado en la pila
                //Con lo que nos queda la operacion equivalente 2 8 (esto es lo que hay en la pila) -. leemos el - y hacemos 2 - 8 dando el resultado.
                OperacionBinaria op=new OperacionBinaria(parteExp.charAt(0));
                Float termino2=resultado.pop();
                Float termino1=resultado.pop();

                resultado.push(op.operar(termino1,termino2));

            }else
            {
                //Es un termino
                resultado.push(Float.parseFloat(parteExp));
            }

        }
        return (Float)resultado.pop();
    }
}
