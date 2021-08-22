package uy.edu.ort.algoritmos.sorting;

import uy.edu.ort.ad.InterfazGraficaAlgoritmo;
import javafx.scene.paint.Color;
import uy.edu.ort.algoritmos.AlgoritmoACorrer;

public
class AlgoritmoSelectionSort
    implements AlgoritmoACorrer
{

    private InterfazGraficaAlgoritmo it;

    @Override
    public
    String nombre()
    {
        return "SelectionSort";
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

    @Override
    public
    void run(final InterfazGraficaAlgoritmo it)
        throws
        Exception
    {
        int[] datosIniciales = new int[]{2000, 23, 21, 42, 14, 52, 25, 522, 14353, 322, 1233};
        this.it = it;
        it.dibujarArray("mergesort", datosIniciales);

        selectionSort(it, datosIniciales);
        it.sleep(500);
        it.dibujarArray("mergesort", datosIniciales);
    }

    private
    void selectionSort(final InterfazGraficaAlgoritmo it,
                       final int[] datos)
    {
        for (int i = 0; i < datos.length; i++)
        {
            int min = datos[i];
            int minIdx = i;
            dibujarPosibleMinimo(it, datos, i, Color.YELLOW);
            for (int j = i; j < datos.length; j++)
            {
                if (datos[j] < min)
                {
                    min = datos[j];
                    minIdx = j;
                    dibujarPosibleMinimo(it, datos, j, Color.YELLOW);
                    it.sleep(200);
                }
            }
            swap(datos, minIdx, i);
            dibujarPosibleMinimo(it, datos, i, Color.GREEN);
            it.sleep(1000);
        }
    }

    private
    void dibujarPosibleMinimo(final InterfazGraficaAlgoritmo it,
                              final int[] datosIniciales,
                              final int i,
                              final Color yellow)
    {
        it.dibujarArray("selectionSort", datosIniciales);
        it.dibujarCeldaArray("selectionSort", datosIniciales, i, yellow);
    }
}
