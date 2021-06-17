package game;

public enum Tag {
    FILLED('#',"-fx-background-radius: 50%; " + "-fx-background-color: radial-gradient(center 40% 40% , radius 50% , #84A98C, #2f3e46);" + "-fx-background-insets: 2.0 2.0 2.0 2.0;"),
    EMPTY('O',"-fx-background-radius: 50%; " + "-fx-background-color:#a7bea9;" + "-fx-background-insets: 3.0 3.0 3.0 3.0;"),
    WALL('.',"-fx-background-radius: 50%; " + "-fx-background-color:#cad2c5;"),
    HIGHLIGHTED("-fx-background-radius: 50%; " + "-fx-background-color:#84A98C;" + "-fx-background-insets: 3.0 3.0 3.0 3.0;"),

    RESET("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;"),
    UNDO("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;"),
    REDO("-fx-background-radius: 10%;"+ "-fx-font-size: 15pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;"),

    MAP("-fx-background-radius: 10%;"+ "-fx-font-size: 14pt;" + "-fx-background-color: #6B917E;"  + "-fx-background-insets: 2.0 2.0 2.0 2.0;");

    public String style;
    public char symbol;

    Tag(char symbol, String style) {
        this.style = style;
        this.symbol = symbol;
    }

    Tag(String style) {
        this.style = style;
    }
}
