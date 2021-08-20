package uy.edu.ort.ad2.algoritmos;

import uy.edu.ort.ad2.InterfazGraficaAlgoritmo;
import javafx.scene.paint.Color;

public
class AlgoritmoMergeSort
    implements AlgoritmoACorrer
{

    private InterfazGraficaAlgoritmo it;

    @Override
    public
    String nombre()
    {
        return "Quick sort";
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
        mergeSort(datosIniciales, 0, datosIniciales.length - 1);
        it.sleep(500);
        it.dibujarArray("mergesort", datosIniciales);
    }

    private
    void mergeSort(final int[] datos,
                   final int inicio,
                   final int fin)
    {

        //Un array de un elemento esta ordenado
        if (inicio >= fin)
        {
            return;
        }
        int mid = (inicio + fin) / 2;
        dibujarPreSort(datos, inicio, fin);

        mergeSort(datos, inicio, mid);
        mergeSort(datos, mid + 1, fin);
        dibujarPreUnion(datos, inicio, fin);
        int[] unionOrdenada = new int[fin - inicio + 1];
        int i = inicio;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= fin)
        {

            if (datos[i] <= datos[j])
            {
                unionOrdenada[k] = datos[i];
                k++;
                i++;
            }
            else if (datos[i] >= datos[j])
            {
                unionOrdenada[k] = datos[j];
                k++;
                j++;
            }
        }
        while (i <= mid)
        {
            unionOrdenada[k] = datos[i];
            i++;
            k++;
        }
        while (j <= fin)
        {
            unionOrdenada[k] = datos[j];
            j++;
            k++;
        }
        for (int c = 0; c < unionOrdenada.length; c++)
        {
            datos[inicio + c] = unionOrdenada[c];
        }
        dibujarPostUnion(datos, inicio, fin);
    }

    private
    void dibujarPostUnion(final int[] datos,
                          final int inicio,
                          final int fin)
    {
        dibujarEstadoConColor(datos, inicio, fin, Color.GREEN);
    }

    private
    void dibujarPreUnion(final int[] datos,
                         final int inicio,
                         final int fin)

    {
        dibujarEstadoConColor(datos, inicio, fin, Color.YELLOW);
    }

    private
    void dibujarPreSort(final int[] datos,
                        final int inicio,
                        final int fin)
    {

        dibujarEstadoConColor(datos, inicio, fin, Color.RED);
    }

    private
    void dibujarEstadoConColor(final int[] datos,
                               final int inicio,
                               final int fin,
                               Color color)
    {
        it.dibujarArray("mergesort", datos);
        for (int i = inicio; i <= fin; i++)
        {
            it.dibujarCeldaArray("mergesort", datos, i, color);
        }
        it.sleep(1500);
    }
}
