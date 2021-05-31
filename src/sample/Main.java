package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.Arrays;


public class Main extends Application {
    /*
    TODO:
        setPrefSize
        Undo
        Redo
        Speichern Laden

     */


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Solitaer");

        BorderPane borderPane = new BorderPane();

        Board board = new Board(Map.DIAMOND);
//        System.out.println(Arrays.toString(Map.values()));

        borderPane.setCenter(board);

        stage.setScene(new Scene(borderPane, 700, 700));

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}