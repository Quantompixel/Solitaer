package game;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SolitaerButton extends Button implements PlayButton {
    Board parentBoard;
    int xPos;
    int yPos;
    Tag tag;
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public SolitaerButton(String text, int xPos, int yPos, Board board, Tag tag) {
        super(text);
        this.xPos = xPos;
        this.yPos = yPos;
        this.parentBoard = board;
        this.tag = tag;
        init();
    }


    public void init() {
        setStyle(tag.style);
        setPrefSize(screenSize.getHeight() / parentBoard.board.map.length, screenSize.getHeight() / parentBoard.board.map.length);
        setMinSize(30, 30);
        addListeners();
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        setStyle(tag.style);
    }

    @Override
    public void onMouseEnter() {
//        if (tag == Tag.FILLED) setStyle("-fx-background-radius: 50%; " + "-fx-background-color: radial-gradient(center 40% 40% , radius 50% , #84A98C, #2f3e46);" + "-fx-background-insets: 0.5 0.5 0.5 0.5;");
    }

    @Override
    public void onMouseExit() {
//        if (tag == Tag.FILLED) setStyle(tag.style);
    }

    @Override
    public void onMouseClick() {
        List<SolitaerButton> neighbours = getNeighbours();

        switch (tag) {
            case EMPTY:
                parentBoard.resetStyle();

                //returns if the first ever clicked button is EMPTY which creates a nullpointer.
                if (parentBoard.getLastClicked() == null) {
                    return;
                }

                SolitaerButton lastClicked = parentBoard.getLastClicked();
                SolitaerButton middleButton = parentBoard.getButtonByCords((xPos + lastClicked.xPos) / 2, (yPos + lastClicked.yPos) / 2);

                for (SolitaerButton neighbour : neighbours) {

                    if (neighbour.equals(lastClicked) && middleButton.tag == Tag.FILLED) {
                        //update state of the pieces
                        middleButton.setTag(Tag.EMPTY);
                        lastClicked.setTag(Tag.EMPTY);
                        setTag(Tag.FILLED);

                        //add new move
                        parentBoard.moves.add(new int[][]{{lastClicked.xPos, lastClicked.yPos}, {middleButton.xPos, middleButton.yPos}, {xPos, yPos}});

                        //check for win
                        parentBoard.checkWin();
                    }
                }

                break;
            case FILLED:
                parentBoard.resetStyle();

                //effect
                setStyle("-fx-background-radius: 50%; " + "-fx-background-color: radial-gradient(center 40% 40% , radius 45% , #84A98C, #2f3e46);" + "-fx-background-insets: 0.1 0.1 0.1 0.1;");

                List<int[]> validMoves = getValidMoves();

                for (int[] ints : validMoves) {
                    parentBoard.getButtonByCords(ints[0], ints[1]).setStyle(Tag.HIGHLIGHTED.style);
                }

                parentBoard.setLastClicked(this);

                break;
            case WALL:
                parentBoard.resetStyle();

        }
    }

    @Override
    public void addListeners() {
        addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> onMouseEnter());
        addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> onMouseExit());
        addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> {
                    if (parentBoard.checkWin()) return;
                    onMouseClick();
                });
    }

    public List<int[]> getValidMoves() {
        List<int[]> positions = new ArrayList<>();


        if (parentBoard.getButtonByCords(xPos - 1, yPos).tag == Tag.FILLED &&
                parentBoard.getButtonByCords(xPos - 2, yPos).tag == Tag.EMPTY) {
            positions.add(new int[]{xPos - 2, yPos});
        }

        if (parentBoard.getButtonByCords(xPos + 1, yPos).tag == Tag.FILLED &&
                parentBoard.getButtonByCords(xPos + 2, yPos).tag == Tag.EMPTY) {
            positions.add(new int[]{xPos + 2, yPos});
        }


        if (parentBoard.getButtonByCords(xPos, yPos - 1).tag == Tag.FILLED &&
                parentBoard.getButtonByCords(xPos, yPos - 2).tag == Tag.EMPTY) {
            positions.add(new int[]{xPos, yPos - 2});
        }

        if (parentBoard.getButtonByCords(xPos, yPos + 1).tag == Tag.FILLED &&
                parentBoard.getButtonByCords(xPos, yPos + 2).tag == Tag.EMPTY) {
            positions.add(new int[]{xPos, yPos + 2});
        }

        return positions;

    }

    public List<SolitaerButton> getNeighbours() {
        List<SolitaerButton> list = new ArrayList<>();

        list.add(parentBoard.getButtonByCords(xPos - 2, yPos));
        list.add(parentBoard.getButtonByCords(xPos + 2, yPos));
        list.add(parentBoard.getButtonByCords(xPos, yPos - 2));
        list.add(parentBoard.getButtonByCords(xPos, yPos + 2));

        return list;
    }

    @Override
    public String toString() {
        return "SolitaerButton{" +
                "parentBoard=" + parentBoard +
                ", xPos=" + xPos +
                ", yPos=" + yPos +
                ", tag='" + tag + '\'' +
                '}';
    }
}