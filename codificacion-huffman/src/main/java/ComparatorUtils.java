import java.util.Comparator;

public
class ComparatorUtils
{
    public static <T>  boolean esMayor(T a, T b,
                            Comparator<T> comparador){
        return comparador.compare(a,b)>0;
    }
    public static <T> boolean  esMenor(T a,T b,Comparator<T> comparador){
        return comparador.compare(a,b)<0;
    }
}
