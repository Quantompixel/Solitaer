package sample;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public enum Tag {
    FILLED(Color.BLACK, new Insets(0.1)),
    EMPTY(Color.GRAY, Insets.EMPTY),
    WALL(Color.WHITESMOKE, Insets.EMPTY),
    SELECTED(Color.LIGHTGRAY, new Insets(0.1)),

    RESET,
    UNDO,
    REDO;

    public Background background;

    Tag(Color color, Insets insets) {
        this.background = new Background(new BackgroundFill(color, new CornerRadii(50), insets));
    }

    Tag(){}

}
