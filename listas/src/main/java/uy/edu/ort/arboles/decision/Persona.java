package uy.edu.ort.arboles.decision;

import uy.edu.ort.listas.Lista;
import uy.edu.ort.listas.ListaDobleCircularClase;

public
class Persona
{
    String nombre;
    Lista<Respuesta> respuestas=new ListaDobleCircularClase<>();

    public
    String getRespuesta(final int preguntaIdx)
    {
        return respuestas.get(preguntaIdx).respuesta;
    }
}
