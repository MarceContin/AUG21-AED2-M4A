import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

//El primer paso es contar las frecuencias de los caracteres
public
class Paso1ContadorDeFrecuencia implements Consumer<String>
{
    Map<String,Integer> frecuenciaPorCaracter=new HashMap<>();


    @Override
    public
    void accept(final String s)
    {
        for(int i=0;i<s.length();i++){

            char c=s.charAt(i);
            if(frecuenciaPorCaracter.containsKey(c+"")){
                frecuenciaPorCaracter.put(c+"",1);
            }else{
                frecuenciaPorCaracter.put(c+"",frecuenciaPorCaracter.get(c+"")+1);
            }
        }


    }
}
