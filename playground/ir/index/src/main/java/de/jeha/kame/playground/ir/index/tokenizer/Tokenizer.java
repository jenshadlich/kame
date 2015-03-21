package de.jeha.kame.playground.ir.index.tokenizer;

import java.util.List;

/**
 * @author jensh
 */
public interface Tokenizer {
    List<String> tokenize(String input);
}
