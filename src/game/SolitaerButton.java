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
    }

    @Override
    public void onMouseExit() {
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
                        //update state of the pieces
                        middleButton.setTag(Tag.EMPTY);
                        lastClicked.setTag(Tag.EMPTY);
                        setTag(Tag.FILLED);

                        //add new move
                        parentBoard.moves.add(new int[][]{{lastClicked.xPos,lastClicked.yPos},{middleButton.xPos, middleButton.yPos},{xPos,yPos}});

                        //check for win
                        parentBoard.checkWin();
                    }
                }

                break;
            case FILLED:
                parentBoard.resetColors();

                for (SolitaerButton current : neighbours) {
                    if (current.tag == Tag.EMPTY) {
                        current.setStyle(Tag.SELECTED.style);

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