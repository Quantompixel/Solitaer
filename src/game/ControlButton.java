package game;

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
        setStyle(tag.style);
        addListeners();
    }

    @Override
    public void onMouseEnter() {
        setStyle("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #75a77f;"  + "-fx-background-insets: 0.5 0.5 0.5 0.5;");
        //#5b7d78
    }

    @Override
    public void onMouseExit() {
        setStyle(tag.style);
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
