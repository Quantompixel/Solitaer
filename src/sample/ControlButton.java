package sample;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;

import java.awt.*;

public class ControlButton extends Button implements PlayButton{
    final Board parentBoard;
    Tag tag;
    String text;
    String tooltip;
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public ControlButton(Board parentBoard, Tag tag, String text, String tooltip) {
        super(text);
        this.parentBoard = parentBoard;
        this.tag = tag;
        this.text = text;
        this.tooltip = tooltip;
        init();
    }

    public void init() {
        setTooltip(new Tooltip(tooltip));
        setPrefSize(screenSize.getHeight()/ this.parentBoard.board.map.length,screenSize.getHeight()/ this.parentBoard.board.map.length);
        setMinSize(30,30);
        addListeners();
    }

    @Override
    public void onMouseEnter() {

    }

    @Override
    public void onMouseExit() {

    }

    @Override
    public void onMouseClick() {
        switch (tag) {
            case RESET:
                parentBoard.resetBoard();
                parentBoard.moves.reset();
                System.out.println(parentBoard.moves);;
                break;
            case REDO:
                parentBoard.redo();
                break;
            case UNDO:
                parentBoard.undo();
        }
    }

    @Override
    public void addListeners() {
        addEventHandler(MouseEvent.MOUSE_ENTERED,
                e -> onMouseEnter());
        addEventHandler(MouseEvent.MOUSE_EXITED,
                e -> onMouseExit());
        addEventHandler(MouseEvent.MOUSE_CLICKED,
                e -> onMouseClick());
    }
}
