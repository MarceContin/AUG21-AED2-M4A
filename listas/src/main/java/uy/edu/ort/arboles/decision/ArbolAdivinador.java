package uy.edu.ort.arboles.decision;

import uy.edu.ort.listas.Lista;
import uy.edu.ort.listas.ListaDobleCircularClase;
import uy.edu.ort.util.AGraphViz;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public
class ArbolAdivinador
{


    public  Pregunta siguientePregunta(){
        return new Pregunta();
    }

    public void responder(String respuesta){


    }

    public
    Lista<Persona> candidatosHastaAhora(){
        return new ListaDobleCircularClase<>();
    }

    public
    void construirArbol(final Respuestas respuestas)
    {

    }



    public
    void iniciarFlujo()
    {


    }
}
