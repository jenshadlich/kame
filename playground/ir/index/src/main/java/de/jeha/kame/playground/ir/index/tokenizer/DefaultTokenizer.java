package de.jeha.kame.playground.ir.index.tokenizer;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author jensh
 */
public class DefaultTokenizer implements Tokenizer {

    @Override
    public List<String> tokenize(String input) {
        return Arrays.asList(StringUtils.split(input));
    }

}
