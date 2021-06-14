package game;

public enum Map {
    ENGLISH(new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', 'O', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
    }, new int[]{5, 5}, "English Standard"),
    FRENCH(new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '#', '#', '#', '#', '#', '.', '.', '.'},
            {'.', '.', '#', '#', '#', 'O', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '#', '#', '#', '#', '#', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
    }, new int[]{6, 5}, "French Round"),
    GERMAN(new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', '#', 'O', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
    }, new int[]{6, 6}, "German Big Version"),
    COMBO(new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', 'O', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '#', '#', '#', '#', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
    }, new int[]{5, 6}, "English and German Combination"),
    DIAMOND(new char[][]{
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '#', '#', '#', '#', '#', '#', '#', '.', '.', '.'},
            {'.', '.', '#', '#', '#', '#', 'O', '#', '#', '#', '#', '.', '.'},
            {'.', '.', '.', '#', '#', '#', '#', '#', '#', '#', '.', '.', '.'},
            {'.', '.', '.', '.', '#', '#', '#', '#', '#', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '#', '#', '#', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '#', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'},
            {'.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.'}
    }, new int[]{6, 6}, "Diamond");

    public char[][] map;
    public int[] winPos;
    public String displayText;

    Map(char[][] map, int[] winPos, String displayText) {
        this.map = map;
        this.winPos = winPos;
        this.displayText = displayText;
    }
}
