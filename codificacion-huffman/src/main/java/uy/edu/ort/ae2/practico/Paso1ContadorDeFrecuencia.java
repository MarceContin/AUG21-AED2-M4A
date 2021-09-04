package uy.edu.ort.ae2.practico;

import uy.edu.ort.ae2.huffman.CaracterFrecuencia;
import uy.edu.ort.ae2.listas.Lista;
import uy.edu.ort.ae2.listas.ListaDobleCircularClase;
import uy.edu.ort.ae2.util.LectorLineas;

//El primer paso es contar las frecuencias de los caracteres
public
class Paso1ContadorDeFrecuencia
    implements LectorLineas
{
    private final Lista<CaracterFrecuencia> frecuenciasDeCaracter = new ListaDobleCircularClase<>();

    public
    Lista<CaracterFrecuencia> getFrecuenciasDeCaracter()
    {
        return frecuenciasDeCaracter;
    }

    @Override
    public
    void leerLinea(final String s)
    {

        for (int i = 0; i < s.length(); i++)
        {

            char c = s.charAt(i);
            boolean actualizado = false;
            for (CaracterFrecuencia freq : frecuenciasDeCaracter)
            {
                if (freq.getCaracter() == c)
                {
                    freq.setFrecuencia(freq.getFrecuencia() + 1);
                    actualizado = true;
                }
            }
            if (!actualizado)
            {
                frecuenciasDeCaracter.agregarFin(new CaracterFrecuencia(c, 1));
            }
        }
    }
}
