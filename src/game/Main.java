package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    private static int currentMap = 0;
    private static Board[] boards = new Board[Map.values().length];

    /*
        TODO:
            Undo
            Redo
            Speichern Laden
            Map rotation fixen
         */

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Solitaer");

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(boards[currentMap]);

        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setMinWidth(200);

        Button nextMap = new Button("next Map");

        nextMap.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> borderPane.setCenter(nextMap(boards[currentMap])));

        vBox.getChildren().addAll(nextMap);

        borderPane.setRight(vBox);

        stage.setScene(new Scene(borderPane, 900, 700));
        stage.show();
    }

    public static Board nextMap(Board usedBoard) {
        int mapCount = Map.values().length;

//        save previous board
        boards[currentMap] = usedBoard;

//        increment counter
        currentMap++;
        currentMap = (currentMap < mapCount) ? currentMap++ : 0;

//        add new boards to the list, or load old ones
        if (boards[currentMap] == null) {
            boards[currentMap] = new Board(Map.values()[currentMap]);
            return boards[currentMap];
        } else {
            return boards[currentMap];
        }

    }

    public static void main(String[] args) {
        boards[0] = new Board(Map.values()[0]);
        launch(args);
    }
}