package de.jeha.kame.playground.ir.index.search;

import de.jeha.kame.playground.ir.index.InvertedIndex;
import de.jeha.kame.playground.ir.index.tokenizer.Tokenizer;

import java.util.List;

/**
 * @author jensh
 */
public class DefaultPhraseSearch implements PhraseSearch {

    private final InvertedIndex index;
    private final Tokenizer tokenizer;

    public DefaultPhraseSearch(InvertedIndex index, Tokenizer tokenizer) {
        this.index = index;
        this.tokenizer = tokenizer;
    }

    @Override
    public Position nextPhrase(String phrase, int position) {
        List<String> ts = tokenizer.tokenize(phrase);
        int v = position;
        for (String t : ts) {
            v = index.next(t, v);
        }
        if (v == Integer.MAX_VALUE) {
            return Position.NOT_FOUND;
        }
        int u = v;
        for (int i = ts.size() - 2; i >= 0; i--) {
            u = index.prev(ts.get(i), u);
        }
        if (v - u == ts.size() - 1) {
            return new Position(u, v);
        }

        return nextPhrase(phrase, u);
    }
}
