package de.jeha.kame.playground.ir.index.search;

/**
 * @author jensh
 */
public class Position {

    public static final Position NOT_FOUND = new Position(Integer.MAX_VALUE, Integer.MAX_VALUE);

    public int start;
    public int end;

    public Position(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
