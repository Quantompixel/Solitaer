package sample;

import java.util.Collection;

public class Move {

    SolitaerButton[] before = new SolitaerButton[3];
    SolitaerButton[] after = new SolitaerButton[3];

    public Move() {
    }

    /**
     * Set the original state of the Piece
     *
     * @param jumper: A filled piece which is moved to make a move
     * @param middle: A filled piece which gets jumped over
     * @param land:   An empty piece which the moved piece lands on
     */
    public void setBefore(SolitaerButton jumper, SolitaerButton middle, SolitaerButton land) {
        before[0] = jumper;
        before[1] = middle;
        before[2] = land;
    }

    /**
     * Set to the changed state of the piece
     *
     * @param jumper: A filled piece which is moved to make a move
     * @param middle: A filled piece which gets jumped over
     * @param land:   An empty piece which the moved piece lands on
     */
    public void setAfter(SolitaerButton jumper, SolitaerButton middle, SolitaerButton land) {
        after[0] = jumper;
        after[1] = middle;
        after[2] = land;
    }

    @Override
    public String toString() {
//        return "{" + before[0].tag + " -> " + after[0].tag + " : " + before[1].tag + " -> " + after[1].tag + " : " + before[2].tag + " -> " + after[2].tag + "}";
//        return "{" + '#' + "}";

        return "{" + before[0].tag.toString().charAt(0) + "->" + after[0].tag.toString().charAt(0) + " : " + before[1].tag.toString().charAt(0) + "->" + after[1].tag.toString().charAt(0) + " : " + before[2].tag.toString().charAt(0) + "->" + after[2].tag.toString().charAt(0) + "}";
    }
}
