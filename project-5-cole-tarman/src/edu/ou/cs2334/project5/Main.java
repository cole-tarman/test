package edu.ou.cs2334.project5;

import edu.ou.cs2334.project5.presenters.NonogramMakerPresenter;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Parameters params = getParameters();
        int numRows = Integer.parseInt(params.getNamed().getOrDefault("rows", "10"));
        int numCols = Integer.parseInt(params.getNamed().getOrDefault("cols", "10"));
        int cellLength = Integer.parseInt(params.getNamed().getOrDefault("cellSize", "20"));

        NonogramMakerPresenter presenter = new NonogramMakerPresenter(numRows, numCols, cellLength);
        Scene scene = new Scene(presenter.getPane());
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("Nonogram Maker");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); 
        primaryStage.show(); 
    }
}