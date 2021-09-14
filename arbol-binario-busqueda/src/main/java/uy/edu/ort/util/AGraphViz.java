package uy.edu.ort.util;

import java.util.List;
import java.util.function.Function;

public
class AGraphViz
{

    public static <Nodo> void imprimirArbol(Nodo raiz,
                                            Function<Nodo,String> getNombre,
                                            Function<Nodo, List<Nodo>> obtenerHijos,
                                            int nivelMaximo){

        StringBuilder sb=new StringBuilder();
        sb.append("digraph A{\n");
        imprimirNodo(raiz,"A",sb,getNombre,obtenerHijos,0,nivelMaximo);



        sb.append("}");
        System.out.println(sb.toString());


    }

    private static
    <Nodo>
    void imprimirNodo(final Nodo nodo,
                      final String clavePadre,
                      final StringBuilder sb,
                      final Function<Nodo, String> getNombre,
                      final Function<Nodo, List<Nodo>> obtenerHijos,
                      final int nivel,
                      final int nivelMaximo)
    {

        sb.append(String.format("%s[label=\"%s\"];\n",clavePadre,getNombre.apply(nodo)));
        List<Nodo> hijos=obtenerHijos.apply(nodo);
        if(nivel>=nivelMaximo)return;
        for (int i = 0; i < hijos.size(); i++)
        {
            if(hijos.get(i)!=null){
                imprimirNodo(hijos.get(i),clavePadre+"_"+i, sb, getNombre, obtenerHijos, nivel+1, nivelMaximo);
                sb.append(String.format("%s -> %s;\n",clavePadre,clavePadre+"_"+i) );
            }else {
            	sb.append(String.format("%s -> %s;\n",clavePadre,clavePadre+"_"+i) );
            	sb.append(String.format("%s[label=\"%s\"];\n",clavePadre+"_"+i,"null"));
            }

        }
    }
}
