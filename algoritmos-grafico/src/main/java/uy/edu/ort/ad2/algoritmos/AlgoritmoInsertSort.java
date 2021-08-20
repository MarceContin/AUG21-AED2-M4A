package uy.edu.ort.ad2.algoritmos;

import uy.edu.ort.ad2.InterfazGraficaAlgoritmo;
import javafx.scene.paint.Color;

public
class AlgoritmoInsertSort
    implements AlgoritmoACorrer
{
    private InterfazGraficaAlgoritmo it;

    @Override
    public
    String nombre()
    {
        return "Insert sort";
    }

    @Override
    public
    void run(final InterfazGraficaAlgoritmo it)
        throws
        Exception
    {
        int[] datosIniciales = new int[]{2000, 23, 21, 42, 14, 52, 25, 522, 14353, 322, 1233};
        this.it = it;
        it.dibujarArray("selectionSort", datosIniciales);
        it.sleep(1000);
        insertionSort(datosIniciales);
        it.dibujarArray("selectionSort", datosIniciales);
    }

    public
    void insertionSort(int[] datos)
    {
        for (int i = 0; i < datos.length; i++)
        {
            int j = i - 1;
            int valorAInsertar = datos[i];

            dibujarMascara(it, datos, j, Color.YELLOW);

            //Hacemos lugar para que inserte
            while (j >= 0 && datos[j] > valorAInsertar)
            {
                datos[j + 1] = datos[j];
                j--;
                dibujarMascara(it, datos, j, Color.YELLOW);
            }
            datos[j + 1] = valorAInsertar;//si no pasa nada se inserta en la posicion en la que estaba
        }
    }

    private
    void dibujarMascara(final InterfazGraficaAlgoritmo it,
                        final int[] datos,
                        final int j,
                        final Color color)
    {
        if (j < 0)
        {
            return;
        }
        it.dibujarArray("selectionSort", datos);
        it.dibujarCeldaArray("selectionSort", datos, j, color);
        it.dibujarCeldaArray("selectionSort", datos, j + 1, color);
        it.sleep(200);
    }
}
