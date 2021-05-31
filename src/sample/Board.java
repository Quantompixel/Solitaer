package sample;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Board extends GridPane{
    private final List<SolitaerButton> solitaerButtons = new ArrayList<>();
    private final List<ControlButton> controlButtons = new ArrayList<>();
    public final Map board;
    private SolitaerButton lastClicked;
    Stack<char[][]> moves = new Stack();

    public Board(Map map) {
        this.board = map;
        generateBoard(board.map);
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

    public void addControlButton() {
        int width = board.map[board.map.length-1].length-1;
        int height = board.map.length-1;

        ControlButton resetButton = new ControlButton(this, Tag.RESET, "\u2B6F", "RESET");
        controlButtons.add(resetButton);
        add(resetButton, width,height);

        ControlButton redoButton = new ControlButton(this, Tag.REDO,"\u21B7","REDO");
        controlButtons.add(redoButton);
        add(redoButton, width-1,height);

        ControlButton undoButton = new ControlButton(this, Tag.UNDO,"\u21B6","UNDO");
        controlButtons.add(undoButton);
        add(undoButton, width-2,height);
    }

    public void generateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int current = board[i][j];

                SolitaerButton btn;

                switch (current) {
                    case 'O':
                        btn = new SolitaerButton("", j, i, this, Tag.EMPTY, Color.GREEN);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                    case '#':
                        btn = new SolitaerButton("", j, i, this, Tag.FILLED, Color.GRAY);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                    case '.':
                        btn = new SolitaerButton("", j, i, this, Tag.WALL, Color.WHITE);
                        solitaerButtons.add(btn);
                        add(btn, btn.xPos, btn.yPos);
                        break;
                }
            }
        }
        //addListeners(solitaerButtons);
        addControlButton();
    }

    public void resetBoard() {
        for (SolitaerButton current : solitaerButtons) {
            int i = board.map[current.yPos][current.xPos];

            switch (i) {
                case 'O':
                    current.tag = Tag.EMPTY;
                    current.background = Tag.EMPTY.background;
                    current.setEmpty();
                    break;
                case '#':
                    current.tag = Tag.FILLED;
                    current.background = Tag.FILLED.background;
                    current.setFilled();
                    break;
            }
        }
    }

    public void resetColors() {
        for (SolitaerButton current : solitaerButtons) {
            current.setBackground(current.background);
        }
    }

    @Override
    public String toString() {
        return "Board";
    }
}
