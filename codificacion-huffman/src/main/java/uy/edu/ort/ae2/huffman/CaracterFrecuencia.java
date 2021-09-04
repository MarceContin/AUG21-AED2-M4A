package uy.edu.ort.ae2.huffman;

public
class CaracterFrecuencia
    implements Comparable<CaracterFrecuencia>
{
    private char caracter;
    private int frecuencia;

    public
    CaracterFrecuencia()
    {
    }

    public
    CaracterFrecuencia(final char caracter,
                       final int frecuencia)
    {
        this.caracter = caracter;
        this.frecuencia = frecuencia;
    }

    @Override
    public
    int compareTo(final CaracterFrecuencia o)
    {
        return frecuencia - o.frecuencia;
    }

    public
    char getCaracter()
    {
        return caracter;
    }

    public
    void setCaracter(final char caracter)
    {
        this.caracter = caracter;
    }

    public
    int getFrecuencia()
    {
        return frecuencia;
    }

    public
    void setFrecuencia(final int frecuencia)
    {
        this.frecuencia = frecuencia;
    }
}
