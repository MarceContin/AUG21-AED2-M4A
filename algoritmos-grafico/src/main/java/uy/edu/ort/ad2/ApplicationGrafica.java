package uy.edu.ort.ad2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public
class ApplicationGrafica
    extends Application
{
    @Override
    public
    void start(Stage stage)
        throws
        IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationGrafica.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        scene.getRoot()
             .setStyle("-fx-font: 12px Arial;");
        stage.show();
    }

    public static
    void main(String[] args)
    {
        launch();
    }
}