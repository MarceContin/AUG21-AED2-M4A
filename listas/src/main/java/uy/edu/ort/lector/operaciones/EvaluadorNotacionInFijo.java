package uy.edu.ort.lector.operaciones;

import uy.edu.ort.listas.Pila;
import uy.edu.ort.listas.PilaEnlazada;

import java.util.ArrayList;
import java.util.List;

public
class EvaluadorNotacionInFijo
{
    /**
     * Las expresiones aritmeticas de siempre con parentesis.
     * No toma en cuenta la precedencia
     * @param expresion la expresion en notacion de siempre
     * @return la evaluacion de la expresión
     */
	//3*5+2 = 15+2 esto es precendecia. En este caso va a hacer 5*7
	//2*(3+5-6)=
	// 2 3 5 6
	// * ( + -
	//pop,pop=> 5 -6 => push(-1)
	// 2 3 -1
	// * ( +
	// pop op => 3 + (-1) => push (2)
	// 2 2
	// * (
	// pop veo que es parentesis
	// 2 2
	// *
	// pop,pop => 2 * 2 =>push
	// 4
    public float evaluarSinPrecedencia(String expresion){
        if(expresion==null||expresion.isBlank())return 0;
        //NEcesitamos dos pilas, una de operadores y otra de resultados
        Pila<Float> resultados=new PilaEnlazada<>();
        Pila<String> operadores=new PilaEnlazada<>();//Tratamos los paretentesis como operadores

        List<String> partesExpresion=splitEntreSimbolos(expresion); //dividimos el string por operadores

        for(String parteExp:partesExpresion){
            if(OperacionBinaria.isOperacion(parteExp) || parteExp.equals("(")){

                operadores.push(parteExp);

            }else if(parteExp.equals(")")){
                //Una vez obtuvimos el parentesis cerrado tenemos que ir para atras aplicando todas las operaciones

                while (!operadores.peek().equals("(")){
                    OperacionBinaria op=new OperacionBinaria(operadores.pop().charAt(0));
                    Float t2=resultados.pop();
                    Float t1=resultados.pop();

                    resultados.push(op.operar(t1,t2));
                }
                operadores.pop();
            }
            else{ //Es un termino
                resultados.push(Float.parseFloat(parteExp));
            }
        }
        while (!operadores.esVacia()){
            OperacionBinaria op=new OperacionBinaria(operadores.pop().charAt(0));
            Float t2=resultados.pop();
            Float t1=resultados.pop();
            resultados.push(op.operar(t1,t2));

        }
        return (Float)resultados.pop();
    }

    private
    List<String> splitEntreSimbolos(final String expresion)
    {
        List<String> partes=new ArrayList<>();
        StringBuilder currentStr= new StringBuilder();
        for (int i = 0; i < expresion.length(); i++)
        {
            char currentChar=expresion.charAt(i);
            if(Character.isDigit(currentChar)){
                currentStr.append(currentChar);
            }else
            {
                if (currentStr.length() > 0)
                {

                    partes.add(currentStr.toString());
                    currentStr.setLength(0);
                }
                partes.add(currentChar+"");
            }

        }
        if(currentStr.length()>0){
            partes.add(currentStr.toString());
        }
        return partes;
    }

    public
    float evaluarConPrecedencia(final String expresion)
    {


        if (expresion == null || expresion.isBlank())
            return 0;
        //NEcesitamos dos pilas, una de operadores y otra de resultados
        Pila<Float> resultados = new PilaEnlazada<>();
        Pila<String> operadores = new PilaEnlazada<>();//Tratamos los paretentesis como operadores

        List<String> partesExpresion = splitEntreSimbolos(expresion); //dividimos el string por operadores

        for (String parteExp : partesExpresion)
        {
            if (OperacionBinaria.isOperacion(parteExp) )
            {

                OperacionBinaria aAgregar=new OperacionBinaria(parteExp.charAt(0));
                while(!operadores.esVacia()
                      && OperacionBinaria.isOperacion(operadores.peek())
                      && aAgregar.precedencia()<=new OperacionBinaria(operadores.peek().charAt(0)).precedencia() ){
                    OperacionBinaria op=new OperacionBinaria(operadores.pop().charAt(0));
                    Float t2=resultados.pop();
                    Float t1=resultados.pop();
                    resultados.push(op.operar(t1,t2));

                }
                operadores.push(parteExp);
            }else if(parteExp.equals("(")){
                operadores.push("(");
            }
            else if (parteExp.equals(")"))
            {
                //Una vez obtuvimos el parentesis cerrado tenemos que ir para atras aplicando todas las operaciones

                while (!operadores.peek()
                                  .equals("("))
                {
                    OperacionBinaria op = new OperacionBinaria(operadores.pop()
                                                                         .charAt(0));
                    Float t2 = resultados.pop();
                    Float t1 = resultados.pop();

                    resultados.push(op.operar(t1, t2));
                }
                operadores.pop();
            }
            else
            { //Es un termino
                resultados.push(Float.parseFloat(parteExp));
            }
        }
        while (!operadores.esVacia())
        {
            OperacionBinaria op = new OperacionBinaria(operadores.pop()
                                                                 .charAt(0));
            Float t2 = resultados.pop();
            Float t1 = resultados.pop();
            resultados.push(op.operar(t1, t2));
        }
        return (Float) resultados.pop();

    }
}
