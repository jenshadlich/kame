package de.jeha.kame.playground.ir.index;


import de.jeha.kame.playground.ir.index.search.DefaultPhraseSearch;
import de.jeha.kame.playground.ir.index.search.PhraseSearch;
import de.jeha.kame.playground.ir.index.search.Position;
import de.jeha.kame.playground.ir.index.tokenizer.DefaultTokenizer;
import de.jeha.kame.playground.ir.index.tokenizer.Tokenizer;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jensh
 */
public class DefaultInvertedIndex implements InvertedIndex {

    private Map<String, List<Integer>> index = new HashMap<String, List<Integer>>();
    private Tokenizer tokenizer = new DefaultTokenizer();
    private PhraseSearch phraseSearch = new DefaultPhraseSearch(this, tokenizer);

    public DefaultInvertedIndex(String input) {
        int i = 0;
        for (String token : tokenizer.tokenize(input)) {
            if (!index.containsKey(token)) {
                index.put(token, new ArrayList<Integer>());
            }
            index.get(token).add(i++);
        }
    }

    public DefaultInvertedIndex(File inputFile) throws IOException {
        int i = 0;
        for (String line : FileUtils.readLines(inputFile)) {
            for (String token : tokenizer.tokenize(line)) {
                if (!index.containsKey(token)) {
                    index.put(token, new ArrayList<Integer>());
                }
                index.get(token).add(i++);
            }
        }
    }

    @Override
    public int first(String t) {
        return index.get(t).get(0);
    }

    @Override
    public int last(String t) {
        return index.get(t).get(index.get(t).size() - 1);
    }

    @Override
    public int next(String t, int current) {
        if (current == Integer.MIN_VALUE) {
            return first(t);
        }
        if (current == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }

        boolean next = false;
        for (int i : index.get(t)) {
            if (i > current) {
                next = true;
            }
            if (next) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    @Override
    public int prev(String t, int current) {
        if (current == Integer.MAX_VALUE) {
            return last(t);
        }
        if (current == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        int prev = Integer.MIN_VALUE;
        for (int i : index.get(t)) {
            if (i < current) {
                prev = i;
            } else {
                break;
            }
        }
        return prev;
    }

    @Override
    public int count(String t) {
        return index.get(t).size();
    }

    @Override
    public Position nextPhrase(String phrase, int position) {
        return phraseSearch.nextPhrase(phrase, position);
    }

}
