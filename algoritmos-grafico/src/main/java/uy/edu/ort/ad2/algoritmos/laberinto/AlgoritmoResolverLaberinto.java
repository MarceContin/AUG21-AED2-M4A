package uy.edu.ort.ad2.algoritmos.laberinto;

import java.util.Stack;

import uy.edu.ort.ad2.InterfazGraficaAlgoritmo;
import uy.edu.ort.ad2.algoritmos.AlgoritmoACorrer;
import uy.edu.ort.ad2.algoritmos.laberinto.LaberintoReader;
import javafx.scene.paint.Color;

public
class AlgoritmoResolverLaberinto
    implements AlgoritmoACorrer
{

    @Override
    public
    String nombre()
    {
        // TODO Auto-generated method stub
        return "Resolver laberinto";
    }

    private
    class Celda
    {
        int fila;
        int columna;

        public
        Celda(int fila,
              int columna)
        {
            super();
            this.fila = fila;
            this.columna = columna;
        }
    }

    @Override
    public
    void run(InterfazGraficaAlgoritmo it)
        throws
        Exception
    {

        int[][] laberinto = LaberintoReader.leerLaberinto("/Users/marcosperez/Downloads/maze.png");

        it.imprimirMatriz(laberinto);
        it.dibujarMatrizBinaria("laberinto", laberinto, Color.BLACK, Color.WHITE);
        boolean[][] visitados = new boolean[laberinto.length][laberinto[0].length];

        Stack<Celda> caminoEncontrado = new Stack<>();
        boolean encontroElCamino = resolverLaberintoPulido(1, 0, caminoEncontrado, laberinto, visitados, it);
        System.out.println("Encontro el camino " + encontroElCamino);
    }

    //Este es el codigo que se vio en clase, agrego una version con comentarios a futuro
    private
    boolean resolverLaberinto(int filaArranque,
                              int columnaDeArranque,
                              Stack<Celda> caminoEncontrado,
                              int[][] laberinto,
                              boolean[][] visitados,
                              InterfazGraficaAlgoritmo it)
    {
        // TODO Auto-generated method stub
        if (!esValido(filaArranque, columnaDeArranque, laberinto) || visitados[filaArranque][columnaDeArranque])
        {
            return false;
        }
        caminoEncontrado.push(new Celda(filaArranque, columnaDeArranque));
        dibujarEstadoAlgoritmo(caminoEncontrado, laberinto, it);
        visitados[filaArranque][columnaDeArranque] = true;
        if (esSalida(filaArranque, columnaDeArranque, laberinto))
        {
            System.out.println("Fin");
            return true;
        }

        boolean huboRespuesta = resolverLaberinto(filaArranque + 1, columnaDeArranque, caminoEncontrado, laberinto, visitados, it);
        if (huboRespuesta)
        {
            return huboRespuesta;
        }
        huboRespuesta = resolverLaberinto(filaArranque + 1, columnaDeArranque, caminoEncontrado, laberinto, visitados, it);
        if (huboRespuesta)
        {
            return huboRespuesta;
        }
        huboRespuesta = resolverLaberinto(filaArranque, columnaDeArranque + 1, caminoEncontrado, laberinto, visitados, it);
        if (huboRespuesta)
        {
            return huboRespuesta;
        }
        huboRespuesta = resolverLaberinto(filaArranque, columnaDeArranque - 1, caminoEncontrado, laberinto, visitados, it);
        if (huboRespuesta)
        {
            return huboRespuesta;
        }
        huboRespuesta = resolverLaberinto(filaArranque - 1, columnaDeArranque, caminoEncontrado, laberinto, visitados, it);
        if (!huboRespuesta)
        {
            caminoEncontrado.pop();
        }
        return huboRespuesta;//Le agregue esto simplemente para que no siga evaluando caminos si ya encontro la salida
    }

    //Version un poco mas pulida
    private
    boolean resolverLaberintoPulido(int filaArranque,
                                    int columnaDeArranque,
                                    Stack<Celda> caminoEncontrado,
                                    int[][] laberinto,
                                    boolean[][] visitados,
                                    InterfazGraficaAlgoritmo it)
    {
        // Si ya no encontre la solucion en esa celda por mas que intente de nuevo no voy a encontrar la salida
        // Podriamos implementarlo con la pila se les ocurre
        if (!esValido(filaArranque, columnaDeArranque, laberinto) || visitados[filaArranque][columnaDeArranque])
        {
            return false;
        }
        //Si la (fila,columna) fue valida entonces la agregamos al camino, usamos un stack para poder sacar y poner de manera sencilla
        caminoEncontrado.push(new Celda(filaArranque, columnaDeArranque));
        //Este metodo nada mas se encarga de dibujar en que estado estamos
        dibujarEstadoAlgoritmo(caminoEncontrado, laberinto, it);
        //Cuando lo agregamos al camino decimos que ya lo visitamos
        visitados[filaArranque][columnaDeArranque] = true;

        //Preguntamos si es una salida del algoritmo
        if (esSalida(filaArranque, columnaDeArranque, laberinto))
        {
            System.out.println("Fin");
            return true;
        }

        //ACA ESTA BUENO QUE EXPERIMENTEN QUE PASA CON EL ORDEN DE LAS LLAMADAS.
        // Siempre viendo como se mueve el algoritmo en el tablero.
        // Aqui llegamos cuando no es una salida y empezamos a probar opciones, buscamos la salida en las cuatro posibles direcciones
        // Primero probamos para abajo
        boolean encontramosElCamino = resolverLaberinto(filaArranque + 1, columnaDeArranque, caminoEncontrado, laberinto, visitados, it);
        if (encontramosElCamino)
        {
            return encontramosElCamino;//Si encontramos el camino no hay nada mas que hacer
        }
        //Probamos a la izquierda
        encontramosElCamino = resolverLaberinto(filaArranque, columnaDeArranque - 1, caminoEncontrado, laberinto, visitados, it);
        if (encontramosElCamino)
        {
            return encontramosElCamino;
        }
        //Probamos hacia arriba
        encontramosElCamino = resolverLaberinto(filaArranque - 1, columnaDeArranque, caminoEncontrado, laberinto, visitados, it);
        if (encontramosElCamino)
        {
            return encontramosElCamino;//Si encontramos el camino no hay nada mas que hacer
        }

        // Probamos a la derecha de la celda en la que estamos
        encontramosElCamino = resolverLaberinto(filaArranque, columnaDeArranque + 1, caminoEncontrado, laberinto, visitados, it);

        if (!encontramosElCamino)
        {// Si no encontramos la salida entonces lo sacamos del camino que evaluamos
            caminoEncontrado.pop();
        }
        return encontramosElCamino;
    }

    private
    boolean resolverLaberinto2(int i,
                               int j,
                               Stack<Celda> caminoEncontrado,
                               int[][] laberinto,
                               boolean[][] visitados,
                               InterfazGraficaAlgoritmo it)
    {
        if (!esValido(i, j, laberinto))
        {
            return false;
        }
        visitados[i][j] = true;
        caminoEncontrado.push(new Celda(i, j));
        dibujarEstadoAlgoritmo(caminoEncontrado, laberinto, it);
        if (esSalida(i, j, laberinto) && j != 0)
        {
            System.out.println("Fin");
            //it.sleep(2000000);
            return true;
        }
        else
        {
            for (int iD = -1; iD <= 1; iD++)
            {
                for (int jD = -1; jD <= 1; jD++)
                {
                    if ((iD == 0 || jD == 0) && esValido(i + iD, j + jD, laberinto) && !visitados[i + iD][j + jD])
                    {
                        if (resolverLaberinto(i + iD, j + jD, caminoEncontrado, laberinto, visitados, it))
                        {
                            return true;
                        }
                    }
                }
            }
        }

        caminoEncontrado.pop();
        //System.out.println("Camino no encontrado"+i+","+j);
        return false;
    }

    private
    void dibujarEstadoAlgoritmo(Stack<Celda> caminoEncontrado,
                                int[][] laberinto,
                                InterfazGraficaAlgoritmo it)
    {
        it.dibujarMatrizBinaria("laberinto", laberinto, Color.WHITE, Color.BLACK);
        for (Celda cel : caminoEncontrado)
        {

            it.dibujarCeldaDeMatriz("", laberinto, cel.fila, cel.columna, Color.GREEN);
        }
        it.sleep(50);
    }

    private
    boolean esValido(int fila,
                     int col,
                     int[][] laberinto)
    {
        return fila >= 0 && fila < laberinto.length && col >= 0 && col < laberinto[0].length && laberinto[fila][col] == 0;
    }

    private
    boolean esSalida(int i,
                     int j,
                     int[][] laberinto)
    {
        return i == laberinto.length - 1 || j == laberinto[i].length - 1;
    }
}
