package uy.edu.ort.arboles.decision;

public class NodoArbolBinarioSimple<T>  extends NodoArbolBinario<T, NodoArbolBinarioSimple<T>>{

	
	
	public NodoArbolBinarioSimple() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NodoArbolBinarioSimple(T dato) {
		super(dato);
		// TODO Auto-generated constructor stub
	}

	@Override
	NodoArbolBinarioSimple<T> getNuevoNodo(T dato) {
		return new NodoArbolBinarioSimple<>(dato);
		
	}
}
