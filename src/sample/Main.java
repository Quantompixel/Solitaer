package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main extends Application {

    private static int currentMap = 0;
    private static Board[] boards = new Board[Map.values().length];

    /*
        TODO:
            setPrefSize
            Undo
            Redo
            Speichern Laden
            Map rotation fixen
         */

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Solitaer");

        BorderPane borderPane = new BorderPane();

        Board board = new Board(Map.ENGLISH);
        System.out.println(Arrays.toString(Map.values()));


        borderPane.setCenter(board);

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setMinWidth(200);
        Button nextMap = new Button("next Map");
        nextMap.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> borderPane.setCenter(nextMap()));

        Button previousMap = new Button("previous Map");
        vBox.getChildren().addAll(nextMap, previousMap);

        borderPane.setRight(vBox);

        stage.setScene(new Scene(borderPane, 900, 700));
        stage.show();
    }

    public static Board nextMap() {
        int mapCount = Map.values().length-1;
        Board currentBoard;

        if (currentMap >= mapCount) {
            currentMap = 0;
        } else {
            currentMap++;
        }

        currentBoard = boards[currentMap];

        if (currentBoard == null) {
            currentBoard = new Board(Map.values()[currentMap]);
            boards[currentMap] = currentBoard;
        }

        System.out.println(Arrays.toString(boards));
        return currentBoard;
    }

    public static Map previousMap(int currentMap) {
        int mapCount = Map.values().length-1;
        if (mapCount < 0) {
            currentMap = mapCount;
        } else {
            currentMap--;
        }

        return Map.values()[currentMap];
    }


    public static void main(String[] args) {
        launch(args);
    }
}