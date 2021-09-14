package uy.ort.edu.ae2.abb;

public class NodoAbb {
	
	private Animal animal;
	private NodoAbb izq;
	private NodoAbb der;
	
	
	public NodoAbb(Animal animal) {
		super();
		this.animal = animal;
	}
	public Animal getAnimal() {
		return animal;
	}
	public void setAnimal(Animal animal) {
		this.animal = animal;
	}
	public NodoAbb getIzq() {
		return izq;
	}
	public void setIzq(NodoAbb izq) {
		this.izq = izq;
	}
	public NodoAbb getDer() {
		return der;
	}
	public void setDer(NodoAbb der) {
		this.der = der;
	}
	@Override
	public String toString() {
		return animal.toString();
	}
	

	public static int cantidad(NodoAbb raiz) {
		if(raiz!=null) {
			return 1+cantidad(raiz.izq)+cantidad(raiz.der);
		}
		return 0;
	}
	
	static void insertar(NodoAbb nodo,Animal a) {
		if(nodo.animal.equals(a)) {
			throw new IllegalArgumentException("Repetido :(");
		}else if(a.esMayor(nodo.animal)) {
			if(nodo.der==null) {
				nodo.der=new NodoAbb(a);
			}else {
				insertar(nodo.der,a);	
			}
			
		}else {
			if(nodo.izq==null) {
				nodo.izq=new NodoAbb(a);
			}else {
				insertar(nodo.izq,a);	
			}
		}
		
	}
	static NodoAbb insertarCheto(NodoAbb nodo,Animal a) {
		if(nodo==null){
			//El arbol esvacio
			return new NodoAbb(a);
		}else if(nodo.animal.equals(a)) {
			throw new IllegalArgumentException("Repetido :(");
		}else if(a.esMayor(nodo.animal)) {
			nodo.der=insertarCheto(nodo.der,a);	
			return nodo;			
		}else {
			nodo.izq=insertarCheto(nodo.izq,a);
			return nodo;
		}
		
	}
	
	public NodoAbb clon() {
		NodoAbb nuevoClon=new NodoAbb(this.animal);
		nuevoClon.animal=this.animal;
		if(this.der!=null)
			nuevoClon.der=this.der.clon();//me clono el subarbol derecho;
		if(this.izq!=null)
			nuevoClon.izq=this.izq.clon();//me clono el subarbol izq;
		return nuevoClon;
	}

	
}
