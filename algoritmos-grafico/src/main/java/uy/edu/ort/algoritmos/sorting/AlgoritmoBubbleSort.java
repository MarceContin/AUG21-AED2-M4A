package uy.edu.ort.algoritmos.sorting;

import uy.edu.ort.ad.InterfazGraficaAlgoritmo;
import javafx.scene.paint.Color;
import uy.edu.ort.algoritmos.AlgoritmoACorrer;

public
class AlgoritmoBubbleSort
    implements AlgoritmoACorrer
{

    private InterfazGraficaAlgoritmo it;

    @Override
    public
    String nombre()
    {
        return "Bublle sort";
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

        bubbleSort(it, datosIniciales);
        it.sleep(500);
        it.dibujarArray("mergesort", datosIniciales);
    }

    private
    void bubbleSort(final InterfazGraficaAlgoritmo it,
                    final int[] datos)
    {
        for (int i = 0; i < datos.length; i++)
        {
            for (int j = 0; j < datos.length - i - 1; j++)
            {
                dibujarMascara(it, datos, j, Color.YELLOW);
                if (datos[j] > datos[j + 1])
                {
                    dibujarMascara(it, datos, j, Color.RED);
                    it.sleep(500);
                    swap(datos, j, j + 1);
                    dibujarMascara(it, datos, j, Color.GREEN);
                }
            }

            it.sleep(1000);
        }
    }

    private
    void dibujarMascara(final InterfazGraficaAlgoritmo it,
                        final int[] datos,
                        final int j,
                        final Color color)
    {
        it.dibujarArray("selectionSort", datos);
        it.dibujarCeldaArray("selectionSort", datos, j, color);
        it.dibujarCeldaArray("selectionSort", datos, j + 1, color);
        it.sleep(200);
    }
}
