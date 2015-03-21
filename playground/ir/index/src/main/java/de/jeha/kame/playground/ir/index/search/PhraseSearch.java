package de.jeha.kame.playground.ir.index.search;

/**
 * @author jensh
 */
public interface PhraseSearch {

    Position nextPhrase(String phrase, int position);

}
