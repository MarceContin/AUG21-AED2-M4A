package uy.edu.ort.ad2.algoritmos;

import uy.edu.ort.ad2.algoritmos.laberinto.AlgoritmoResolverLaberinto;

import java.util.List;

public
class TodosLosAlgoritmos
{
    private List<AlgoritmoACorrer> algoritmosACorrer = List.of(
        new AlgoritmoBubbleSort(),
        new AlgoritmoInsertSort(),
        new AlgoritmoMergeSort(),
        new AlgoritmoQuickSort(),
        new AlgoritmoSelectionSort(),
        new AlgoritmoResolverLaberinto());

    public
    AlgoritmoACorrer getDefault()
    {
        return algoritmosACorrer.get(algoritmosACorrer.size() - 1);
    }

    private static TodosLosAlgoritmos instancia;

    public static
    TodosLosAlgoritmos getInstance()
    {
        if (instancia == null)
        {
            instancia = new TodosLosAlgoritmos();
        }
        return instancia;
    }

    public
    List<AlgoritmoACorrer> getTodosLosAlgoritmos()
    {
        return algoritmosACorrer;
    }
}
