
public class HanoiRecursiva {
	public static void main(String [] args) {
		hanoi(3,"A","B","C");
	}
	
	public static void hanoi(int n,String origen,String auxiliar,String destino) {
		//caso base
		if(n==0) {
			return;
		}
		//paso recursivo
		hanoi(n-1,origen,destino,auxiliar);//con n=3, resolvemos hanoi con dos discos y lo metemos
		move(origen,destino);
		hanoi(n-1,auxiliar,origen,destino);
		
	}

	private static  void move(String a, String c) {
		System.out.println("Muevo de "+a + " a "+c);
		
	}
}
