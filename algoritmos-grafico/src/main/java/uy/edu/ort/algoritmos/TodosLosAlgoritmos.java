package uy.edu.ort.algoritmos;

import uy.edu.ort.algoritmos.laberinto.AlgoritmoResolverLaberinto;
import uy.edu.ort.algoritmos.sorting.AlgoritmoBubbleSort;
import uy.edu.ort.algoritmos.sorting.AlgoritmoInsertSort;
import uy.edu.ort.algoritmos.sorting.AlgoritmoMergeSort;
import uy.edu.ort.algoritmos.sorting.AlgoritmoQuickSort;
import uy.edu.ort.algoritmos.sorting.AlgoritmoSelectionSort;

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
