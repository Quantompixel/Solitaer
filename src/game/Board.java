package game;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Board extends GridPane {
    private final List<SolitaerButton> solitaerButtons = new ArrayList<>();
    private final List<ControlButton> controlButtons = new ArrayList<>();
    public final Map board;
    private SolitaerButton lastClicked;
    UndoRedoList<int[][]> moves = new UndoRedoList<>();

    public Board(Map map) {
        this.board = map;
        setStyle("-fx-background-color: #cad2c5");
        setAlignment(Pos.CENTER);
        generateBoard(board.map);
    }

    public Board(List<char[]> board, Map map) {
        this.board = map;
        setStyle("-fx-background-color: #cad2c5");
        setAlignment(Pos.CENTER);
        generateBoard(board);
    }

    public void setLastClicked(SolitaerButton lastClicked) {
        this.lastClicked = lastClicked;
    }

    public SolitaerButton getLastClicked() {
        return lastClicked;
    }

    public SolitaerButton getButtonByCords(int xPos, int yPos) {
        for (SolitaerButton current : solitaerButtons) {
            if (current.xPos == xPos && current.yPos == yPos) {
                return current;
            }
        }

        return solitaerButtons.get(0);
    }

    public List<SolitaerButton> getSolitaerButtons() {
        return solitaerButtons;
    }

    public void generateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int current = board[i][j];

                SolitaerButton btn;

                switch (current) {
                    case 'O':
                        btn = new SolitaerButton("", j, i, this, Tag.EMPTY);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                    case '#':
                        btn = new SolitaerButton("", j, i, this, Tag.FILLED);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                    case '.':
                        btn = new SolitaerButton("", j, i, this, Tag.WALL);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                }
            }
        }
        addControlButton();
    }

    public void generateBoard(List<char[]> board) {
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).length; j++) {
                int current = board.get(i)[j];

                SolitaerButton btn;

                switch (current) {
                    case 'O':
                        btn = new SolitaerButton("", j, i, this, Tag.EMPTY);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                    case '#':
                        btn = new SolitaerButton("", j, i, this, Tag.FILLED);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                    case '.':
                        btn = new SolitaerButton("", j, i, this, Tag.WALL);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                }
            }
        }
        addControlButton();
    }

    public void addControlButton() {
        int width = board.map[board.map.length - 1].length - 1;
        int height = board.map.length - 1;

        ControlButton resetButton = new ControlButton(this, Tag.RESET, "\u2B6F", "RESET");
        controlButtons.add(resetButton);
        add(resetButton, width, height);

        ControlButton redoButton = new ControlButton(this, Tag.REDO, "\u21B6", "REDO");
        controlButtons.add(redoButton);
        add(redoButton, width - 2, height);

        ControlButton undoButton = new ControlButton(this, Tag.UNDO, "\u21B7", "UNDO");
        controlButtons.add(undoButton);
        add(undoButton, width - 1, height);
    }

    public void resetBoard() {
        for (SolitaerButton current : solitaerButtons) {
            int i = board.map[current.yPos][current.xPos];

            switch (i) {
                case 'O':
                    current.setText("");
                    current.setTag(Tag.EMPTY);
                    break;
                case '#':
                    current.setText("");
                    current.setTag(Tag.FILLED);
                    break;
            }
        }
    }

    public void resetStyle() {
        for (SolitaerButton current : solitaerButtons) {
            current.setStyle(current.tag.style);
        }
    }

    public void undo() {
        if (moves.getPointer() == 0) {
            return;
        }

        for (int[] element : moves.undo()) {

            SolitaerButton btn = getButtonByCords(element[0], element[1]);
            if (btn.tag == Tag.EMPTY) {
                btn.setTag(Tag.FILLED);
            } else {
                btn.setTag(Tag.EMPTY);
            }


        }
    }

    public void redo() {
        if (moves.size() == moves.getPointer()) {
            return;
        }

        for (int[] element : moves.redo()) {

            SolitaerButton btn = getButtonByCords(element[0], element[1]);

            if (btn.tag == Tag.EMPTY) {
                getButtonByCords(element[0], element[1]).setTag(Tag.FILLED);
            } else {
                getButtonByCords(element[0], element[1]).setTag(Tag.EMPTY);
            }
        }
    }

    public boolean checkWin() {
        SolitaerButton winButton = null;

        for (SolitaerButton element : solitaerButtons) {
            if (element.tag == Tag.FILLED) {
                if (winButton == null) {
                    winButton = element;
                } else {
                    return false;
                }
            }
        }

        assert winButton != null;
        if ((winButton.xPos == board.winPos[0]) && (winButton.yPos == board.winPos[1])) {
            winButton.setText("WIN");
            winButton.setTextFill(Color.WHITE);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Board : " + board;
    }
}
