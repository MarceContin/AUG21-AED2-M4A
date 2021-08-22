module uy.edu.ort.ad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens uy.edu.ort.ad to javafx.fxml;
    exports uy.edu.ort.ad;
    exports uy.edu.ort.algoritmos;
    opens uy.edu.ort.algoritmos to javafx.fxml;
    exports uy.edu.ort.algoritmos.laberinto;
    opens uy.edu.ort.algoritmos.laberinto to javafx.fxml;
    exports uy.edu.ort.algoritmos.sorting;
    opens uy.edu.ort.algoritmos.sorting to javafx.fxml;

}