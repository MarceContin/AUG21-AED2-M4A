package uy.edu.ort.lector.operaciones;

import java.util.List;

public
class OperacionBinaria
{

    private final char simbolo;

    public
    OperacionBinaria(final char simbolo)
    {
        this.simbolo = simbolo;
    }

    public static
    boolean isOperacion(final String parteExp)
    {
        return List.of("+","-","/","*").contains(parteExp);
    }

    public float operar(float a,float b){
        switch (simbolo){
        case '+':return a+b;
        case '-':return  a-b;
        case '/':return a/b;
        case '*':return  a*b;
        default:return 0;
        }
    }

    public int precedencia()
    {
        switch (simbolo)
        {
        case '+':
            return 0;
        case '-':
            return 0;
        case '/':
            return 2;
        case '*':
            return 2;
        default:
            return 0;
        }
    }
}
