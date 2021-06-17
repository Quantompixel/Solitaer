package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Main extends Application {

    private static int currentMap = 0;
    private static Board[] boards = new Board[Map.values().length];
    private static BorderPane borderPane = new BorderPane();

    /*
    TODO:
        Speichern Laden
     */

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Solitaer");

        borderPane.setCenter(boards[currentMap]);

//        Sidebar
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setMinWidth(200);
        vBox.setStyle("-fx-effect: dropshadow(gaussian, #425057, 30, 0, 0, 0); -fx-background-color: #b9c8b7");
        vBox.setAlignment(Pos.TOP_CENTER);

//        Map cycler
        HBox mapButtons = new HBox();
        Label mapLabel = new Label(Map.values()[currentMap].displayText);
        mapLabel.setStyle("-fx-font-size: 20pt");
        Button nextMap = new Button("\u21A0");
        nextMap.setStyle(Tag.MAP.style);
        Button previousMap = new Button("\u219E");
        previousMap.setStyle(Tag.MAP.style);
        mapButtons.setAlignment(Pos.CENTER);
        mapButtons.getChildren().addAll(previousMap, mapLabel, nextMap);

//        Save and Load
        HBox saveLoadButtons = new HBox();
        Button save = new Button("Save");
        Button load = new Button("Load");

        saveLoadButtons.getChildren().addAll(save, load);

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> save(boards[currentMap]));


//        EventHandlers
        nextMap.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            borderPane.setCenter(nextMap(boards[currentMap]));
            mapLabel.setText(Map.values()[currentMap].displayText);
        });

        previousMap.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            borderPane.setCenter(previousMap(boards[currentMap]));
            mapLabel.setText(Map.values()[currentMap].displayText);
        });


        vBox.getChildren().addAll(mapButtons,saveLoadButtons);

        borderPane.setRight(vBox);

        stage.setScene(new Scene(borderPane, 900, 700));
        stage.show();
    }

//    public static void load() {
//        try (BufferedReader in = Files.newBufferedReader(Paths.get(srcFile), StandardCharsets.UTF_8))
//        {
//            String line;
//            while ((line = in.readLine()) != null) {
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    public static void save(Board board) {
        try (BufferedWriter out = Files.newBufferedWriter(Paths.get("./res/game.txt"), StandardCharsets.UTF_8)) {
            Map map = board.board;
            int xLength = map.map.length;
            int yLength = map.map[0].length;
            StringBuilder res = new StringBuilder();
            List<SolitaerButton> buttonList = board.getSolitaerButtons();

            for (int i = 0; i < yLength; i++) {
                for (int j = 0; j < xLength; j++) {
                    res.append(buttonList.get(i * xLength + j).tag.symbol);
                }
                res.append(System.lineSeparator());
            }

            out.write(res.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Board previousMap(Board usedBoard) {
        int mapCount = Map.values().length;

//        save previous board
        boards[currentMap] = usedBoard;

//        change counter
        if (currentMap > 0) {
            currentMap--;
        } else {
            currentMap = mapCount - 1;
        }

//        create new board if there is none
        if (boards[currentMap] == null) {
            boards[currentMap] = new Board(Map.values()[currentMap]);
        }
        return boards[currentMap];

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
        }
        return boards[currentMap];

    }

    public static void main(String[] args) {
        boards[0] = new Board(Map.values()[0]);
        launch(args);
    }
}