package uy.edu.ort.algoritmos.sorting;

import uy.edu.ort.ad.InterfazGraficaAlgoritmo;
import javafx.scene.paint.Color;
import uy.edu.ort.algoritmos.AlgoritmoACorrer;

public
class AlgoritmoQuickSort
    implements AlgoritmoACorrer
{

    @Override
    public
    String nombre()
    {
        return "Quick sort";
    }

    private
    void swap(int[] datos,
              int a,
              int b)
    {
        int aux = datos[a];
        datos[a] = datos[b];
        datos[b] = aux;
    }

    //ponemos el pivote al final y tenemos dos punteros i,j. i es el ultimo valor que es <= pivote. j se mueve por todo
    //el intervalo [inicio,fin] haciendo los swaps cuando vemos que un dato es <= pivote
    int partition_loMuto(int[] datos,
                         int inicio,
                         int fin,
                         InterfazGraficaAlgoritmo it)
    {
        int pivoteIdx = seleccionarPivoteBasico(datos, inicio, fin);
        swap(datos, pivoteIdx, fin);
        pivoteIdx = fin;
        it.sleep(1000);
        it.dibujarArray("quicksort", datos);
        for (int i = inicio; i <= fin; i++)
        {
            it.dibujarCeldaArray("quicksort", datos, i, Color.GREEN);
        }
        it.dibujarCeldaArray("quicksort", datos, pivoteIdx, Color.RED);
        int pivote = datos[pivoteIdx];
        int i = inicio - 1;

        for (int j = inicio; j <= fin; j++)
        {
            if (datos[j] <= pivote)
            {
                i++;
                swap(datos, i, j);
            }
        }
        //swap(datos,pivoteIdx,i);
        return i;
    }

    //
    int partition_hoare(int[] datos,
                        int inicio,
                        int fin,
                        InterfazGraficaAlgoritmo it)
    {
        int i = inicio;
        int j = fin;
        int pivoteIndex = seleccionarPivoteMejorDeTres(datos, inicio, fin);
        int pivote = datos[pivoteIndex];
        dibujarParticion(datos, inicio, fin, it, pivoteIndex, false);

        while (i < j)
        {

            while (datos[i] < pivote)
            {
                i++;
            }
            while (datos[j] > pivote)
            {
                j--;
            }
            if (i < j)
            {
                swap(datos, i, j);
            }
        }
        dibujarParticion(datos, inicio, fin, it, i, true);

        return i;
    }

    private
    void dibujarParticion(final int[] datos,
                          final int inicio,
                          final int fin,
                          final InterfazGraficaAlgoritmo it,
                          final int pivoteIndex,
                          boolean despuesParticion)
    {
        if (despuesParticion)
        {
            it.animarArray("quicksort", datos, 1);
            it.sleep(2000);
        }
        else
        {
            it.dibujarArray("quicksort", datos);
        }

        for (int h = inicio; h <= fin; h++)
        {
            it.dibujarCeldaArray("quicksort", datos, h, despuesParticion ? Color.LIGHTGREEN : Color.YELLOW);
        }

        it.dibujarCeldaArray("quicksort", datos, pivoteIndex, despuesParticion ? Color.GREEN : Color.RED);
        it.sleep(2000);
    }

    int seleccionarPivoteBasico(int[] datos,
                                int inicio,
                                int fin)
    {
        return inicio;
    }

    int seleccionarPivoteRandom(int[] datos,
                                int inicio,
                                int fin)
    {

        return (int) Math.round(Math.random() * (fin - inicio)) + inicio;
    }

    int seleccionarPivoteMejorDeTres(int[] datos,
                                     int inicio,
                                     int fin)
    {
        int pivote1 = datos[inicio];
        int pivote2 = datos[fin];
        int pivote3 = datos[(inicio + fin) / 2];//Acordarse que en la aritmetica con int esto se "redondea para abajo"

        if ((pivote1 <= pivote2 && pivote2 <= pivote3) ||
            (pivote3 <= pivote2 && pivote2 <= pivote1))
        {
            return (inicio + fin) / 2;//El pivote 2 es la mediana
        }
        else if ((pivote2 <= pivote1 && pivote1 <= pivote3) ||
                 (pivote3 <= pivote1 && pivote1 <= pivote2))
        {
            return inicio;
        }
        else
        {
            return fin;
        }
    }

    void quickSort(int[] datos,
                   int inicio,
                   int fin,
                   InterfazGraficaAlgoritmo it)
    {
        if (inicio < fin)
        {

            //int posicionPivoteFinal=partition_loMuto(datos,inicio,fin,it);
            int posicionPivoteFinal = partition_hoare(datos, inicio, fin, it);
            quickSort(datos, inicio, posicionPivoteFinal - 1, it);
            quickSort(datos, posicionPivoteFinal + 1, fin, it);
        }
    }

    @Override
    public
    void run(InterfazGraficaAlgoritmo it)
        throws
        InterruptedException
    {

        int[] datosIniciales = new int[]{2000, 23, 21, 42, 14, 52, 25, 522, 14353, 322, 1233};

        it.dibujarArray("quicksort", datosIniciales);
        quickSort(datosIniciales, 0, datosIniciales.length - 1, it);
    }
}
