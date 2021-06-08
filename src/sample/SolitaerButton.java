package sample;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SolitaerButton extends Button implements PlayButton {
    Board parentBoard;
    int xPos;
    int yPos;
    Tag tag;
    Background background;
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public SolitaerButton(String text, int xPos, int yPos, Board board, Tag tag) {
        super(text);
        this.xPos = xPos;
        this.yPos = yPos;
        this.parentBoard = board;
        this.tag = tag;
    }

    public SolitaerButton(String text, int xPos, int yPos, Board board, Tag tag, Color color) {
        super(text);
        this.xPos = xPos;
        this.yPos = yPos;
        this.parentBoard = board;
        this.tag = tag;
        this.background = tag.background;
        init();
    }

    public SolitaerButton(int xPos, int yPos, Board board, Tag tag, Color color) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.parentBoard = board;
        this.tag = tag;
        this.background = tag.background;
        setBackground(background);
    }

    public SolitaerButton(int xPos, int yPos, Board board, Tag tag) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.parentBoard = board;
        setTag(tag);
    }

    public SolitaerButton() {
        this.xPos = 0;
        this.yPos = 0;
    }

    public void init() {
        setBackground(background);
        setPrefSize(screenSize.getHeight() / parentBoard.board.map.length, screenSize.getHeight() / parentBoard.board.map.length);
        setMinSize(30, 30);
        addListeners();
    }

    public void setTag(Tag tag) {
        this.tag = tag;
        background = tag.background;
        setBackground(background);
    }

    @Override
    public void onMouseEnter() {
        switch (tag) {
            case EMPTY:
                break;
            case FILLED:
                /*
                List<SolitaerButton> neighbours = getNeighbours();

                for (SolitaerButton current : neighbours) {
                    //current.setBackground(current.background..brighter());
                }
                break;
                */

            case WALL:

        }

    }

    @Override
    public void onMouseExit() {
        /*
        List<SolitaerButton> neighbours = getNeighbours();

        for (SolitaerButton current : neighbours) {
            current.setBackground(current.background);
        }
        */

    }

    @Override
    public void onMouseClick() {
        List<SolitaerButton> neighbours = getNeighbours();

        switch (tag) {
            case EMPTY:
                parentBoard.resetColors();

                //returns if the first ever clicked button is EMPTY which creates a nullpointer.
                if (parentBoard.getLastClicked() == null) {
                    return;
                }

                SolitaerButton lastClicked = parentBoard.getLastClicked();
                SolitaerButton middleButton = parentBoard.getButtonByCords((xPos + lastClicked.xPos) / 2, (yPos + lastClicked.yPos) / 2);

                for (SolitaerButton neighbour : neighbours) {

                    if (neighbour.equals(lastClicked) && middleButton.tag == Tag.FILLED) {
                        //create new move
                        Move move = new Move();

                        //state before the jump
                        move.setBefore(new SolitaerButton(lastClicked.xPos, lastClicked.yPos, parentBoard, Tag.FILLED), new SolitaerButton(middleButton.xPos, middleButton.yPos, parentBoard, Tag.FILLED), new SolitaerButton(xPos, yPos, parentBoard, Tag.EMPTY));

                        //update state of the pieces
                        middleButton.setTag(Tag.EMPTY);
                        lastClicked.setTag(Tag.EMPTY);
                        setTag(Tag.FILLED);

                        //state after the jump
                        move.setAfter(lastClicked, middleButton, this);

                        //push move on the stack
                        parentBoard.moves.insertWithPointer(move);
                        System.out.println(parentBoard.moves);;

                    }
                }

                break;
            case FILLED:
                parentBoard.resetColors();

                for (SolitaerButton current : neighbours) {
                    if (current.tag == Tag.EMPTY) {
                        current.setBackground(Tag.SELECTED.background);

                    }
                }

                parentBoard.setLastClicked(this);

                break;
            case WALL:

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

    public List<SolitaerButton> getNeighbours() {
        List<SolitaerButton> list = new ArrayList<>();

        /*
        for (int i = xPos-2; i <= xPos+2; i++) {
            for (int j = yPos-2; j <= yPos+2; j++) {
                SolitaerButton current = parentBoard.getButtonByCords(i, j);
                if (current == this) {
                    continue;
                }

                list.add(parentBoard.getButtonByCords(i, j));
            }
        }
        */
        /*
        for (int i = xPos-2; i <= xPos+2; i++) {
            SolitaerButton current = parentBoard.getButtonByCords(i, yPos);
            if (current == this) {
                continue;
            }
            list.add(current);
        }

         for (int i = yPos-2; i <= yPos+2; i++) {
            SolitaerButton current = parentBoard.getButtonByCords(xPos, i);
            if (current == this) {
                continue;
            }
            list.add(current);
        }

         */

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