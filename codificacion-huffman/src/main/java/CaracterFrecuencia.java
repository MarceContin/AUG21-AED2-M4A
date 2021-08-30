public
class CaracterFrecuencia implements Comparable<CaracterFrecuencia>
{
    public String cadena;
    public int frecuencia;

    public
    CaracterFrecuencia()
    {
    }

    public
    CaracterFrecuencia(final String cadena,
                       final int frecuencia)
    {
        this.cadena = cadena;
        this.frecuencia = frecuencia;
    }

    @Override
    public
    int compareTo(final CaracterFrecuencia o)
    {
        return frecuencia-o.frecuencia;
    }
}
