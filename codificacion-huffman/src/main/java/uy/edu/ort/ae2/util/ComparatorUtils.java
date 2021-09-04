package uy.edu.ort.ae2.util;

import java.util.Comparator;

public
class ComparatorUtils
{

    public static
    <T> boolean esMayor(T a,
                        T b,
                        Comparator<T> comp)
    {
        return comp.compare(a, b) > 0;
    }

    public static
    <T> boolean esMayorOIgual(T a,
                              T b,
                              Comparator<T> comp)
    {
        return comp.compare(a, b) >= 0;
    }

    public static
    <T> boolean esMenor(T a,
                        T b,
                        Comparator<T> comp)
    {
        return comp.compare(a, b) < 0;
    }

    public static
    <T> boolean esMenorOIgual(T a,
                              T b,
                              Comparator<T> comp)
    {
        return comp.compare(a, b) <= 0;
    }

    public static
    <T> boolean esIgual(T a,
                        T b,
                        Comparator<T> comp)
    {
        return comp.compare(a, b) == 0;
    }
}
