package uy.ort.edu.ae2.lista;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import uy.edu.ort.util.ComparatorUtils;

public class ListaDobleCircularClase<T> implements Lista<T> {

	private class NodoListaDobleCircular<T>{
		T dato;
		NodoListaDobleCircular<T> sig;
		NodoListaDobleCircular<T> ant;
		
		public NodoListaDobleCircular(T dato) {
			this.dato=dato;
		}
		public NodoListaDobleCircular() {
			
		}
		
		public 
		NodoListaDobleCircular<T>  agregarAntes(T dato){
			NodoListaDobleCircular<T> nuevo=
					new NodoListaDobleCircular<>(dato);
			NodoListaDobleCircular<T> actual=this;
			NodoListaDobleCircular<T> previo=this.ant;
			
			actual.ant=nuevo;
			previo.sig=nuevo;
			nuevo.sig=actual;
			nuevo.ant=previo;
			
			return nuevo;
			
		}
		public boolean estaOrdenado(ListaDobleCircularClase<T>.NodoListaDobleCircular<T> inicio,
				Comparator<T> comparador) {
			if(this.sig==inicio) {//Es el fin de la iteracion, de la lista
				return true;//Esto seria como una lista de un nodo.
			}
			if(comparador.compare(this.dato, this.sig.dato)>=0){//this.dato >= this.sig.dato
				return false;
			
			}else {
				return this.sig.estaOrdenado(inicio,comparador);
			}

			
		}
		public T eliminame() {
			this.ant.sig=this.sig;
			this.sig.ant=this.ant;
			this.ant=null;//Por el garbage collector
			this.sig=null;//Por el garbage collector
			return this.dato;
					
		}

		//ASUMIMOS QUE LA LISTA NO ES CIRCULAR PARA EVITAR CONFUSIONES
		public
		NodoListaDobleCircular<T> mergeSort(Comparator<T> comparator)
		{
			if(this.sig==null){
				return this;//Una lista de un elemento esta ordenada
			}

			NodoListaDobleCircular<T> mid=obtenerMitadDeLaLista();
			if(mid.ant!=null)
			{
				mid.ant.sig = null; //Al romper esta referencia es como si separaramos la lista en 2
				mid.ant=null; //Esta eliminacion no impporta, pero es lo correcto para mantener coherencia.
			}

			// Como rompimos la referencia con la mitad de la lista, lo que tenemos ahora para ordenar con this
			// Es la primera mitad de la lista
			NodoListaDobleCircular<T> inicioOrdenadoPrimeraMitad=this.mergeSort(comparator);
			//Como "dividimos" la lista en el  puntero del medio, tenemos que este es el comienzo de la segunda mitad de la  lista desordenada.
			NodoListaDobleCircular<T> inicioOrdenadoSegundaMidad=mid.mergeSort(comparator);


			return intercalarIterativo(inicioOrdenadoPrimeraMitad,inicioOrdenadoSegundaMidad,comparator);
		}

		/*
		* Esta version tiene un codigo que se puede simplificar, igual esta bueno que vean la diferencia con el recursivo.
		 */
		private
		NodoListaDobleCircular<T> intercalarIterativo(
			final NodoListaDobleCircular<T> inicioSubLista1,
			final NodoListaDobleCircular<T> inicioSubLista2,
			Comparator<T> comparator){
			NodoListaDobleCircular<T> nodoLista1=inicioSubLista1;
			NodoListaDobleCircular<T> nodoLista2=inicioSubLista2;
			NodoListaDobleCircular<T> inicioListaIntercalada=null;
			NodoListaDobleCircular<T> nodoActualListaIntercalada=null;
			//Las dos listas no son vacias
			while (nodoLista1!=null && nodoLista2!=null){
				NodoListaDobleCircular<T> nodoPrevioIntercalada=nodoActualListaIntercalada;
				if(ComparatorUtils.esMenor(nodoLista1.dato,nodoLista2.dato,comparator)){
					nodoActualListaIntercalada=nodoLista1;
					nodoLista1=nodoLista1.sig;
				}else{
					nodoActualListaIntercalada=nodoLista2;
					nodoLista2=nodoLista2.sig;
				}
				if(inicioListaIntercalada==null){
					inicioListaIntercalada=nodoActualListaIntercalada;
				}
				if(nodoPrevioIntercalada!=null){
					nodoPrevioIntercalada.sig=nodoActualListaIntercalada;
					nodoActualListaIntercalada.ant=nodoPrevioIntercalada;
				}

			}
			while (nodoLista1!=null){
				NodoListaDobleCircular<T> nodoPrevioIntercalada=nodoActualListaIntercalada;
				nodoActualListaIntercalada=nodoLista1;
				if(inicioListaIntercalada==null){
					inicioListaIntercalada=nodoActualListaIntercalada;
				}
				if(nodoPrevioIntercalada!=null){
					nodoPrevioIntercalada.sig=nodoActualListaIntercalada;
					nodoActualListaIntercalada.ant=nodoPrevioIntercalada;
				}
				nodoLista1=nodoLista1.sig;
			}

			while (nodoLista2!=null){
				NodoListaDobleCircular<T> nodoPrevioIntercalada=nodoActualListaIntercalada;
				nodoActualListaIntercalada=nodoLista2;
				if(inicioListaIntercalada==null){
					inicioListaIntercalada=nodoActualListaIntercalada;
				}
				if(nodoPrevioIntercalada!=null){
					nodoPrevioIntercalada.sig=nodoActualListaIntercalada;
					nodoActualListaIntercalada.ant=nodoPrevioIntercalada;
				}
				nodoLista2=nodoLista2.sig;
			}
			return inicioListaIntercalada;

		}



		//Pre las dos listas estan ordenadas
		private
		NodoListaDobleCircular<T> intercalarRecursivo(
			  								final NodoListaDobleCircular<T> inicioSubLista1,
											 final NodoListaDobleCircular<T> inicioSubLista2,
											 Comparator<T> comparator)
		{
			//Ambas sublistas no son vacias
			if(inicioSubLista1!=null && inicioSubLista2!=null){
				NodoListaDobleCircular<T> inicioListaOrdenada=null;
				NodoListaDobleCircular<T> subListaIntercaladaOrdenada=null;
				//Agarramos el menor de las listas como comienzo
				//Sabemos que la lista intercalada ahora es la intercalacion del siguiente de la sub-lista ordenada 1 y la lista 2 no la tocamos
				if(ComparatorUtils.esMenor(inicioSubLista1.dato,inicioSubLista2.dato,comparator)){
					inicioListaOrdenada=inicioSubLista1;
					subListaIntercaladaOrdenada=intercalarRecursivo(inicioSubLista1.sig,inicioSubLista2,comparator);

				}else{
					inicioListaOrdenada=inicioSubLista2;
					subListaIntercaladaOrdenada=intercalarRecursivo(inicioSubLista1,inicioSubLista2.sig,comparator);
				}
				if(subListaIntercaladaOrdenada!=null){
					inicioListaOrdenada.sig=subListaIntercaladaOrdenada;
					subListaIntercaladaOrdenada.ant=inicioListaOrdenada;
				}

				return inicioListaOrdenada;

			}else if(inicioSubLista1!=null){//La primer lista no es vacia
				return inicioSubLista1;
			}else if(inicioSubLista2!=null){//La segunda lista no es vacia,se las dejo por claridad
				return inicioSubLista2;
			}else {
				return null;
			}
		}

		private NodoListaDobleCircular<T> obtenerMitadDeLaLista()
		{
			//Metodo de hoare
			NodoListaDobleCircular<T> nodoMitad=this;
			NodoListaDobleCircular<T> nodoAvanzandoDeADos=this;
			// La idea es simple avanzamos de 2 en dos en la lista hasta llegar el fin
			// Mientras guardamos un puntero que avanza de a uno
			// Cuando terminamos la lista avanzando de 2 en 2, hemos hecho n (largo de lista)/2 iteraciones
			// Con lo que al avanzar el puntero de a 1 tenemos la mitad de la lista ahi.
			while(nodoAvanzandoDeADos!=null && nodoAvanzandoDeADos.sig!=null){
				nodoAvanzandoDeADos=nodoAvanzandoDeADos.sig.sig;
				nodoMitad=nodoMitad.sig;

			}
			return nodoMitad;
		}


	}

	private class  IteradorLista implements  Iterator<T>{
		NodoListaDobleCircular<T> comienzo;
		boolean visitoElcominezo;
		NodoListaDobleCircular<T> actual;

		public
		IteradorLista(final NodoListaDobleCircular<T> comienzo)
		{
			this.comienzo = comienzo;
			this.actual=comienzo;
		}

		@Override
		public
		boolean hasNext()
		{
			if(!visitoElcominezo){
				return actual!=null;
			}else{
				return actual!=comienzo;
			}
		}

		@Override
		public
		T next()
		{
			visitoElcominezo=true;
			var dato= actual.dato;
			actual=actual.sig;
			return dato;
		}
	}
	
	private NodoListaDobleCircular<T> inicio;
	
	
	@Override
	public Iterator<T> iterator() {

		return new IteradorLista(this.inicio);
	}

	@Override
	public void agregarFin(T dato) {
		if(esVacia()) {
			inicio=new NodoListaDobleCircular<>(dato);
			inicio.ant=inicio;
			inicio.sig=inicio;
		}else
		{
			inicio.agregarAntes(dato);
		}
		
	}

	@Override
	public void agregarComienzo(T dato) {
		if(esVacia()) {
			inicio=new NodoListaDobleCircular<>(dato);
			inicio.ant=inicio;
			inicio.sig=inicio;
		}else {
			inicio=inicio.agregarAntes(dato);
			
		}
		
	}

	@Override
	public T get(int n) {
		if(esVacia())return null;
		NodoListaDobleCircular<T> nodoActual=inicio;
		
		while(n>0) {
			n--;
			nodoActual=nodoActual.sig;
		}
		
		return nodoActual.dato;
		
		
	}

	@Override
	public T eliminarComienzo() {
		
		if(getLargo()==1) {
			T dato=this.inicio.dato;
			inicio=null;
			return dato;
		}else if(!esVacia()) {
NodoListaDobleCircular<T> nuevoComienzo=inicio.sig;
			T dato =inicio.eliminame();
			inicio=nuevoComienzo;
			return dato;
		}else {
			return null;
		}
	}

	@Override
	public T eliminarFin() {
		if(getLargo()==1) {
			T dato=this.inicio.dato;
			inicio=null;
			return dato;
		}else if(!esVacia()) {
			T dato =inicio.ant.eliminame();
			return dato;
		}else {
			return null;
		}
	}

	@Override
	public int getLargo() {
		
		if(esVacia())return 0;
		
		NodoListaDobleCircular<T> nodoActual=inicio;
		
		int largo=0;
		boolean visiteElComienzo=false;
		
		while(!visiteElComienzo || nodoActual!=inicio) {
			
			largo++;
			nodoActual=nodoActual.sig;
			visiteElComienzo=true;
		}
		
		return largo;
	}

	@Override
	public boolean esVacia() {

		return inicio==null;
	}

	@Override
	public void ordenar(Comparator<T> comparator) {
		// TODO Auto-generated method stub
		if(!esVacia()){
			//Rompemos los enlaces para hacer una lista doblemente enlazada simple (NO CIRCULAR)
			//Esto hace mas facil el algoritmo.
			this.inicio.ant.sig=null;
			this.inicio.ant=null;

			this.inicio=this.inicio.mergeSort(comparator);
			NodoListaDobleCircular<T> nodoFinal=this.inicio;
			while(nodoFinal.sig!=null){
				nodoFinal=nodoFinal.sig;
			}
			this.inicio.ant=nodoFinal;
			this.inicio.ant.sig=inicio;//Volvemos a hacerla circular
		}
	}

	@Override
	public boolean estaOrdenada(Comparator<T> comparador) {
		if(esVacia())return true;
		return inicio.estaOrdenado(inicio,comparador);
	}

	@Override
	public
	String toString()
	{
		boolean esComienzo=false;
		StringBuilder sb=new StringBuilder();
		for (T dato: this)
		{
			if(!esComienzo)sb.append(",");

			sb.append(dato);
			esComienzo=true;
		}
		return "["+sb.toString()+"]";
	}
}
