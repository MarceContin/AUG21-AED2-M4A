import java.util.Iterator;
import java.util.Stack;
import java.util.function.Consumer;

public class Main {

	
	public static void main(String[] args) {
		
		Stack<Integer> a=new Stack<>();
		Stack<Integer> b=new Stack<>();
		Stack<Integer> c=new Stack<>();
		int nMax=3;
		for(int i=nMax;i>=1;i--) {
			a.push(i);
		}
		System.out.println("Estado inicial");
		imprimirEstadoHanoi(a.size(), a, b, c);
		hanoiStack(a.size(), a.size(), a, b, c,()->imprimirEstadoHanoi(nMax, a, b, c));
	}
	
	public static void hanoi(int n ,String a,String b,String c) {
		if(n==0){return; };
		hanoi(n-1,a,c,b);
		System.out.println("Mover disco de "+a +"-> "+c);
		hanoi(n-1,c,a,b);
		
	}
	public static String centerText(String text, int len){
	    String out = String.format("%"+len+"s%s%"+len+"s", "",text,"");
	    float mid = (out.length()/2);
	    float start = mid - (len/2);
	    float end = start + len; 
	    return out.substring((int)start, (int)end);
	}
	
	public static void hanoiStack(int nMax,
			int n,
			Stack<Integer> a,
			Stack<Integer> b,
			Stack<Integer> c,
			Runnable imprimirEstado) {
		if(n==0){return; };
		hanoiStack(nMax,n-1,a,c,b,imprimirEstado);
		
		System.out.println("Mover disco de "+a +"-> "+c);
		Integer aDisc=a.pop();
		c.push(aDisc);
		imprimirEstado.run();
		
		
		hanoiStack(nMax,n-1,b,a,c,imprimirEstado);
		
	}

	private static void imprimirEstadoHanoi(int nMax, Stack<Integer> a, Stack<Integer> b, Stack<Integer> c) {
		int[] [] niveles=new int[nMax][3];
		rellenarNiveles(nMax,niveles,a,0);
		rellenarNiveles(nMax,niveles,b,1);
		rellenarNiveles(nMax,niveles,c,2);
		for (int i = 0; i < niveles.length; i++) {
			for (int j = 0; j < niveles[i].length; j++) {
				int valorDisco=niveles[i][j];
			System.out.print(	centerText(valorDisco+"",nMax));
			System.out.print("\t");
				
			}
			System.out.println();
		}
		
		
		System.out.println();
	}

	private static void rellenarNiveles(int nMax, int[][] niveles, Stack<Integer> a, int nivCol) {
		int idx=0;
		//System.out.println("peek"+a.peek());
		for (Integer disco : a) {
			
			niveles[nMax-idx-1][nivCol]=disco;
			idx++;
		}
		
		
		
		
	}

}
