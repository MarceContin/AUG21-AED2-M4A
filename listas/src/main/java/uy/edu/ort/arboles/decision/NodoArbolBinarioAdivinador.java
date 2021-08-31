package uy.edu.ort.arboles.decision;

import uy.edu.ort.listas.Lista;
import uy.edu.ort.listas.ListaDobleCircularClase;

public class NodoArbolBinarioAdivinador {

	
	Pregunta pregunta;//la hoja no va a tener esto
	String respuesta;//la raiz no va tener esto
	Lista<Persona> personasQueRespondieronRespAMiPadre;//en la raiz estan todos los noditos
	NodoArbolBinarioAdivinador izq;//Respuesta 1
	NodoArbolBinarioAdivinador der;//Respuesta 2
	public void insertarAlFinal(Pregunta p) {
	
		if(this.izq!=null || this.der!=null){
			//Izq o der no es null
			this.izq.insertarAlFinal(p);
			this.der.insertarAlFinal(p);
			
		}else {//En este caso estoy en una hoja
			//izq y der son null
			if(personasQueRespondieronRespAMiPadre.getLargo()>1) {
				setearPregunta(p);	
			}
		}
	}
	public void setearPregunta(Pregunta p) {
		this.pregunta=p;//Las hojas no tenian preguntas asignadas
		String r1 =p.respuestas.get(0);
		String r2 =p.respuestas.get(1);
		
		NodoArbolBinarioAdivinador nuevoNodoIzq=new NodoArbolBinarioAdivinador();
		nuevoNodoIzq.respuesta=r1;
		nuevoNodoIzq.personasQueRespondieronRespAMiPadre=filtrarMisPersonasPorRespuesta(r1);
		this.izq=nuevoNodoIzq;
		NodoArbolBinarioAdivinador nuevoNodoDer=new NodoArbolBinarioAdivinador();
		nuevoNodoDer.respuesta=r2;
		nuevoNodoDer.personasQueRespondieronRespAMiPadre=filtrarMisPersonasPorRespuesta(r2);
		this.der=nuevoNodoDer;
	}
	private Lista<Persona> filtrarMisPersonasPorRespuesta(String r1) {
		Lista<Persona> personasFiltradas=new ListaDobleCircularClase<>();
		for (Persona persona : this.personasQueRespondieronRespAMiPadre) {
			Respuesta laRespuestaQueDioALaPreguntaDeThis=persona.respuestas.get(this.pregunta.idx);
			if (laRespuestaQueDioALaPreguntaDeThis.respuesta.equals(r1)) {
				personasFiltradas.agregarFin(persona);
			}
		}
		return personasFiltradas;
	}
	
	
	static int cantidadHojas(NodoArbolBinarioAdivinador nodo) {
		if(nodo==null)return 0;
		else if(esHoja(nodo)) {
			return 1;
			//+ cantidadHojas(nodo.izq)+ cantidadHojas(nodo.der) = 0 por ser hoja
		}else {
			return cantidadHojas(nodo.izq)+ cantidadHojas(nodo.der); 
			
		}
	}
	
	
	static int cantidadNodos(NodoArbolBinarioAdivinador nodo) {
		if(nodo==null)return 0;
		else{
			return 1
					+ cantidadNodos(nodo.izq)
					+ cantidadNodos(nodo.der); 
			
		}
	}
	
	private static boolean esHoja(NodoArbolBinarioAdivinador nodo) {
		
		return nodo.izq== null && nodo.der==null;
	}
	static int alturaNodo(NodoArbolBinarioAdivinador nodo) {
		
		if(nodo==null)return 0;
		else if (esHoja(nodo))return 0;
		else {
			return 1+Math.max(alturaNodo(nodo.izq), alturaNodo(nodo.der));
		}
	}
	
	

	
}
