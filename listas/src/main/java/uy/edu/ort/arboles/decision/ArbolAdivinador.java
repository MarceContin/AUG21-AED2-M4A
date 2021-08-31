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

	NodoArbolBinarioAdivinador raiz;
	NodoArbolBinarioAdivinador nodoFlujo;
	
	public
    void iniciarFlujo()
    {
    	nodoFlujo=raiz;

    }

    public  Pregunta siguientePregunta(){
        return nodoFlujo.pregunta;
    }

    public void responder(String respuesta){
    	
    	if(nodoFlujo.izq.respuesta.equals(respuesta)) {
    		nodoFlujo=nodoFlujo.izq;
    	}else if(nodoFlujo.der.respuesta.equals(respuesta)) {
    		nodoFlujo=nodoFlujo.der;
    	}else {
    		//HORROR
    		throw new IllegalArgumentException("respuesta no esta en los datos. Arreglate");
    	}
    	

    }

    public
    Lista<Persona> candidatosHastaAhora(){
        return nodoFlujo.personasQueRespondieronRespAMiPadre;
    }

    public
    void construirArbol(final Respuestas respuestas)
    {
    	for (Pregunta p : respuestas.preguntas) {
			if(raiz==null) {
				raiz=new NodoArbolBinarioAdivinador();
				raiz.personasQueRespondieronRespAMiPadre=respuestas.personas;
				raiz.setearPregunta(p);		
				
			}else {
				raiz.insertarAlFinal(p);
				
			}
    	}
    	System.out.println("La altura es:"+alturaArbol());
    	System.out.println("La cantidad de nodos es "+NodoArbolBinarioAdivinador.cantidadNodos(raiz));
    	System.out.println("La cantidad de hojas es "+NodoArbolBinarioAdivinador.cantidadHojas(raiz));
    	imprimir();
    	

    }

	private int alturaArbol() {
		return NodoArbolBinarioAdivinador.alturaNodo(raiz);
	}

    public void imprimir() {
    	AGraphViz.imprimirArbol(raiz, v->String.format("%s/%s", 
    			v.respuesta!=null?v.respuesta:"",
    		    v.pregunta!=null?v.pregunta.pregunta:"")
    			 , v->Stream.of(v.izq,v.der).toList(), 20);
    }


    
}
