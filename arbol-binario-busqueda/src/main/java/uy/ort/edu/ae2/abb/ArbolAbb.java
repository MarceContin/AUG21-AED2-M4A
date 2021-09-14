package uy.ort.edu.ae2.abb;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import uy.edu.ort.util.AGraphViz;

public class ArbolAbb{
	private NodoAbb raiz;
	
	void insertar(Animal animal) {
		raiz=NodoAbb.insertar(raiz,animal);
	}
	
	void buscar(long codigo) {
		
	}
	void borrar(long codigo) {
		
	}
	
	Animal animalMasAntiguo() {//Obttener minimo
		return null;
	}
	
	Animal animalMasReciente() {//Obttener maximo
		return null;
	}
	
	Animal borrarMasAntiguo() {
		
		return null;
	}
	
	int cantidad() {
		return NodoAbb.cantidad(raiz);
	}
	
	
	void imprimirGraphViz() {
		
		AGraphViz.<NodoAbb>imprimirArbol(raiz,
				n->n!=null ?n.toString():"null", 
				n->Stream.of(n.getIzq(),n.getDer()).collect(Collectors.toList()), 2220);
	}

}
