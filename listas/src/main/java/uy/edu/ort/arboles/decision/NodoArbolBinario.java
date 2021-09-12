package uy.edu.ort.arboles.decision;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import uy.edu.ort.util.AGraphViz;

public abstract class NodoArbolBinario<T,U extends NodoArbolBinario<T, U>> {

	protected T dato;
	protected U izq;
	protected U der;
	
	public NodoArbolBinario() {
		
	}
	public  NodoArbolBinario(T dato) {
		this.dato=dato;
	}
	
	
	
	abstract U getNuevoNodo(T dato);
	
	
	public void imprimirGraphViz(int nivelMaximo) {
		AGraphViz.imprimirArbol(this, 
				v->v.dato.toString(), 
				v-> Stream.of(izq, der).collect(Collectors.toList()),
				nivelMaximo);
	}
	public int alturaNodo() {
		return 0;
	}
}
