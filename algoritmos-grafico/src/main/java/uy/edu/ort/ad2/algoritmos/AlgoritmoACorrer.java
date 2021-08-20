package uy.edu.ort.ad2.algoritmos;

import uy.edu.ort.ad2.InterfazGraficaAlgoritmo;

public
interface AlgoritmoACorrer
{
    String nombre();

    void run(InterfazGraficaAlgoritmo it)
        throws
        Exception;
}
