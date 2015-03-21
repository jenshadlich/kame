package de.jeha.kame.playground.ir.index;

import de.jeha.kame.playground.ir.index.search.Position;

/**
 *
 */
public interface InvertedIndex {

    int first(String t);

    int last(String t);

    int next(String t, int current);

    int prev(String t, int current);

    int count(String t);

    Position nextPhrase(String input, int position);

}
