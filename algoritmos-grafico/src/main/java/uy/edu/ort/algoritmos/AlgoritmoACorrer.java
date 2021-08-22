package uy.edu.ort.algoritmos;

import uy.edu.ort.ad.InterfazGraficaAlgoritmo;

public
interface AlgoritmoACorrer
{
    String nombre();

    void run(InterfazGraficaAlgoritmo it)
        throws
        Exception;
}
