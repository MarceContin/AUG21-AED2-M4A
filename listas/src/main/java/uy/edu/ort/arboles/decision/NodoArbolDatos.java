package uy.edu.ort.arboles.decision;

public class NodoArbolDatos<T> {
	private T dato;
	private NodoArbolDatos<T> izq;
	private NodoArbolDatos<T> der;
	
	
	public static <T> boolean sonIguales(NodoArbolDatos<T> a,NodoArbolDatos<T> b) {
		if(a==null && b==null) {
			return true;
		}else if(a==null && b!=null) {
			return false;
		}else if(b==null && a!=null) {
			return false;
		} else if(a.dato.equals(b.dato)) {
			return    sonIguales(a.izq, b.izq) && sonIguales(a.der, b.der);
		}else {
			return false;
		}
		
	}
	
	
	

}
