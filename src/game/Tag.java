package game;

public enum Tag {
    FILLED("-fx-background-radius: 50%; " + "-fx-background-color: radial-gradient(center 40% 40% , radius 50% , #84A98C, #2f3e46);"),
    EMPTY("-fx-background-radius: 50%; " + "-fx-background-color:#a7bea9"),
    SELECTED("-fx-background-radius: 50%; " + "-fx-background-color:#84A98C"),
    WALL("-fx-background-radius: 50%; " + "-fx-background-color:#cad2c5"),

    RESET("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;"),
    UNDO("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;"),
    REDO("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;");

    public String style;

    Tag(String style) {
        this.style = style;
    }
}
