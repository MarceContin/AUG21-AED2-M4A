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
	
	

	
}
