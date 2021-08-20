package uy.edu.ort.ad2;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.util.StringConverter;
import uy.edu.ort.ad2.algoritmos.AlgoritmoACorrer;
import uy.edu.ort.ad2.algoritmos.AlgoritmoInsertSort;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import uy.edu.ort.ad2.algoritmos.TodosLosAlgoritmos;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public
class AplicationController<executor>
    implements Initializable
{
    @FXML
    private Label welcomeText;

    @FXML
    private
    Canvas canvas;
    @FXML
    private
    ComboBox<AlgoritmoACorrer> algoritmoSeleccionado;

    private ExecutorService executor = Executors.newSingleThreadExecutor();
    @Override
    public
    void initialize(final URL location,
                    final ResourceBundle resources)
    {
        this.algoritmoSeleccionado.getItems()
                                  .addAll(TodosLosAlgoritmos.getInstance()
                                                            .getTodosLosAlgoritmos());

        this.algoritmoSeleccionado.getSelectionModel()
                                  .select(TodosLosAlgoritmos.getInstance()
                                                            .getDefault());
        this.algoritmoSeleccionado.setConverter(new StringConverter<AlgoritmoACorrer>()
        {
            @Override
            public
            String toString(final AlgoritmoACorrer object)
            {
                return object.nombre();
            }

            @Override
            public
            AlgoritmoACorrer fromString(final String string)
            {
                return TodosLosAlgoritmos.getInstance()
                                         .getTodosLosAlgoritmos()
                                         .stream()
                                         .filter(v -> v.nombre() == string)
                                         .findAny()
                                         .orElseGet(null);
            }
        });

    }

    @FXML
    protected
    void correrAlgoritmo()
    {

        Task t = new Task()
        {
            @Override
            protected
            Object call()
                throws
                Exception
            {
                //AlgoritmoACorrer aCorrer=new AlgoritmoABB();
                AlgoritmoACorrer aCorrer = algoritmoSeleccionado.getValue();
                try
                {
                    aCorrer.run(new InterfazGraficaAlgoritmoImpl(canvas.getGraphicsContext2D()));
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                return null;
            }
        };
        Executor.getExecutor()
                .submit(t);
    }
}