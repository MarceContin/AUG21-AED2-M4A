package uy.ort.edu.ae2.abb;

import org.junit.jupiter.api.Test;

public class TestArbol {

	@Test
	public void testInsertar() {
		
		Animal firulais=new Animal(20210913,0, "Firulais");
		Animal nilo=new Animal(20210913,1, "Nilo");
		Animal bigotes=new Animal(20210901,0, "Sr. Bigotes");
		Animal salamandra=new Animal(20210801,0, "Salamander");
		Animal sasha=new Animal(20200801,0, "Sasha");
		Animal bolaNieve=new Animal(20200822,0, "Bola de nieve");
		
		ArbolAbb abb=new ArbolAbb();
		
		abb.insertar(firulais);
		abb.insertar(nilo);
		abb.insertar(salamandra);
		abb.insertar(bigotes);
		
		abb.insertar(sasha);
		abb.insertar(bolaNieve);
		
		
		abb.imprimirGraphViz();
		
	
	}
}
