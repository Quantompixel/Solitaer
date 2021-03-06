package game;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main extends Application {

    private static int currentMap = 0;
    private static Board[] boards = new Board[Map.values().length];
    private static BorderPane borderPane = new BorderPane();
    private static File homeDirectory;


    /*
    TODO:
        Show winning pos
     */
    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Solitaer");

        borderPane.setCenter(boards[currentMap]);

//        Sidebar
        VBox vBox = new VBox();
        vBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        vBox.setMinWidth(200);
        vBox.setStyle("-fx-background-color: #b9c8b7");
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(10, 10, 10, 10));
        vBox.setSpacing(40);

//        Map cycler
        HBox mapButtons = new HBox();
        Label mapLabel = new Label(Map.values()[currentMap].displayText);
        mapLabel.setStyle("-fx-font-size: 20pt");
        Button nextMap = new Button("\u276F");
        nextMap.setStyle("-fx-text-alignment: center;" + Tag.MAP.style);
        Button previousMap = new Button("\u276E");
        previousMap.setStyle(Tag.MAP.style);
        mapButtons.setAlignment(Pos.CENTER);
        mapButtons.getChildren().addAll(previousMap, mapLabel, nextMap);

//        Save and Load
        HBox saveLoadButtons = new HBox();
        Button save = new Button("Save");
        Button load = new Button("Load");
        save.setStyle(Tag.MAP.style);
        load.setStyle(Tag.MAP.style);
        saveLoadButtons.setAlignment(Pos.CENTER);
        saveLoadButtons.getChildren().addAll(save, load);

        save.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> save());
        load.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> load());

//        Wiki
        Text wikiText = new Text();
        wikiText.setText("Peg solitaire (or Solo Noble) is a board game for one player involving movement of pegs on a board with holes.\n" +
                "The standard game fills the entire board with pegs except for the central hole. The objective is," +
                " making valid moves, to empty the entire board except for a solitary peg in the central hole."
        );
        wikiText.setWrappingWidth(200);

//        Explanation
        Text explain = new Text();
        explain.setText("To perform moves a marble has to be clicked and then an empty field. All viable moves are highlighted with a darker green.\n" +
                "Also plays can be undone with the Buttons in the bottom left corner\n" +
                "Have Fun");
        explain.setWrappingWidth(200);
        explain.setStyle("-fx-font-weight: bold");

//        EventHandlers
        nextMap.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            borderPane.setCenter(nextMap(boards[currentMap]));
            mapLabel.setText(Map.values()[currentMap].displayText);
        });

        previousMap.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            borderPane.setCenter(previousMap(boards[currentMap]));
            mapLabel.setText(Map.values()[currentMap].displayText);
        });


        vBox.getChildren().addAll(mapButtons, saveLoadButtons, wikiText, explain);

        borderPane.setRight(vBox);

        //icon
        Image icon = new Image("file:res/icon.png");
        stage.getIcons().add(icon);

        stage.setScene(new Scene(borderPane, 900, 700));
        stage.show();
    }

    public static void chooseDirectory() {
        if (homeDirectory != null) {
            return;
        }

        DirectoryChooser dirChooser = new DirectoryChooser();
        dirChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        homeDirectory = dirChooser.showDialog(null);
    }

    public static File chooseFile() {
        FileChooser fileChooser = new FileChooser();

        if (homeDirectory == null) {
            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        } else {
            fileChooser.setInitialDirectory(homeDirectory);
        }

        return fileChooser.showOpenDialog(null);
    }

    public static String generateFileName() {
        StringBuilder res = new StringBuilder();

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
        res.append(formatter.format(date)).append('_');

        for (int i = 0; i < 5; i++) {
            res.append((char) (Math.random() * ('z' - 'a') + 'a'));
        }

        return res.toString();
    }

    public static void load() {
        File load = chooseFile();
        homeDirectory = load.getParentFile();

        try (BufferedReader in = Files.newBufferedReader(Paths.get(load.getPath()), StandardCharsets.UTF_8)) {
            int counter = 0;
            while (true) {
                if (counter > Map.values().length - 1) {
                    break;
                }

                List<char[]> board = new ArrayList<>();
                boolean firstIteration = true;
                String mapName = "";

                while (true) {
                    String line = in.readLine();

                    if (line == null) {
                        break;
                    }
                    if (line.equals("")) {
                        //-> new board begins
                        break;
                    }
                    if (firstIteration) {
                        //read out the map on the first iteration
                        mapName = line;
                        firstIteration = false;
                        continue;
                    }

                    char[] cur = new char[line.length()];

                    //add row
                    for (int i = 0; i < line.length(); i++) {
                        cur[i] = line.charAt(i);
                    }
                    board.add(cur);
                }

                //add new map
                if (board.size() < 1) {
                    break;
                }
                Board newBoard = new Board(board, Map.valueOf(mapName));
                boards[counter] = newBoard;
                counter++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //update borderpane
        borderPane.setCenter(boards[currentMap]);
        boards[currentMap].checkWin();

    }

    public static void save() {
        chooseDirectory();
        String fileName = generateFileName();

        try (BufferedWriter out = Files.newBufferedWriter(Paths.get(homeDirectory + "/" + fileName), StandardCharsets.UTF_8)) {
            for (int i = 0; i < boards.length; i++) {
                //only save boards which have been changed
                if (boards[i] == null) {
                    break;
                }

                //calculates how many lines are read out
                Map map = boards[i].board;
                int xLength = map.map.length;
                int yLength = map.map[0].length;

                StringBuilder res = new StringBuilder();
                List<SolitaerButton> buttonList = boards[i].getSolitaerButtons();

                //add map type
                res.append(map).append(System.lineSeparator());
                for (int j = 0; j < yLength; j++) {
                    for (int k = 0; k < xLength; k++) {
                        res.append(buttonList.get(j * xLength + k).tag.symbol);
                    }
                    res.append(System.lineSeparator());
                }

                out.write(res + System.lineSeparator());
            }


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