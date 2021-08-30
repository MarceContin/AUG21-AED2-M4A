import java.util.Map;

public
class Paso2OrdenarLosCaracteresPorFrecuencia
{
    public Lista<CaracterFrecuencia> ordenarCaracters(Map<String ,Integer> valores){
        Lista<CaracterFrecuencia> caracteresOrdenadosPorFrecuencia=new Lista<CaracterFrecuencia>();
        for(var valor:valores.entrySet()){
            caracteresOrdenadosPorFrecuencia.agregarAlFinal(new CaracterFrecuencia(valor.getKey(),valor.getValue()));
        }
        //caracteresOrdenadosPorFrecuencia.ordenar();

        return caracteresOrdenadosPorFrecuencia;
    }

}
