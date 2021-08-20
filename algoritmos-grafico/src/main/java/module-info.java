module uy.edu.ort.ad2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;

    opens uy.edu.ort.ad2 to javafx.fxml;
    exports uy.edu.ort.ad2;
    exports uy.edu.ort.ad2.algoritmos;
    opens uy.edu.ort.ad2.algoritmos to javafx.fxml;
    exports uy.edu.ort.ad2.algoritmos.laberinto;
    opens uy.edu.ort.ad2.algoritmos.laberinto to javafx.fxml;
}