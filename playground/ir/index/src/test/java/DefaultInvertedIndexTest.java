import de.jeha.kame.playground.ir.index.DefaultInvertedIndex;
import de.jeha.kame.playground.ir.index.InvertedIndex;
import de.jeha.kame.playground.ir.index.search.Position;
import junit.framework.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 */
public class DefaultInvertedIndexTest {

    @Test
    public void test() throws IOException {

        //InvertedIndex invertedIndex = new InvertedIndex(new File("src/test/resources/1.txt"));
        InvertedIndex invertedIndex = new DefaultInvertedIndex("to be or not to be");

        Assert.assertEquals(2, invertedIndex.count("to"));
        Assert.assertEquals(2, invertedIndex.count("be"));
        Assert.assertEquals(1, invertedIndex.count("or"));
        Assert.assertEquals(1, invertedIndex.count("not"));

        Assert.assertEquals(0, invertedIndex.first("to"));
        Assert.assertEquals(4, invertedIndex.last("to"));
        Assert.assertEquals(4, invertedIndex.next("to", 0));
        Assert.assertEquals(0, invertedIndex.prev("to", 4));
        Assert.assertEquals(4, invertedIndex.prev("to", 5));

        Assert.assertEquals(0, invertedIndex.next("to", Integer.MIN_VALUE));
        Assert.assertEquals(Integer.MAX_VALUE, invertedIndex.next("to", Integer.MAX_VALUE));
        Assert.assertEquals(4, invertedIndex.prev("to", Integer.MAX_VALUE));
        Assert.assertEquals(Integer.MIN_VALUE, invertedIndex.prev("to", Integer.MIN_VALUE));

        List<Integer> postings = new ArrayList<Integer>();
        int current = Integer.MIN_VALUE;
        while (current < Integer.MAX_VALUE) {
            current = invertedIndex.next("to", current);
            postings.add(current);
        }
        Assert.assertEquals(Arrays.asList(0, 4, Integer.MAX_VALUE), postings);
    }

    @Test
    public void testPhraseSearch() {
        DefaultInvertedIndex invertedIndex = new DefaultInvertedIndex("to be or not to be");

        Position p1 = invertedIndex.nextPhrase("to be", Integer.MIN_VALUE);

        Assert.assertEquals(0, p1.start);
        Assert.assertEquals(1, p1.end);

        Position p2 = invertedIndex.nextPhrase("to be", 1);

        Assert.assertEquals(4, p2.start);
        Assert.assertEquals(5, p2.end);

        Position p3 = invertedIndex.nextPhrase("to be", 4);

        Assert.assertEquals(Integer.MAX_VALUE, p3.start);
        Assert.assertEquals(Integer.MAX_VALUE, p3.end);
    }

    @Test
    public void testPhraseSearchFaust1() throws IOException {

        DefaultInvertedIndex invertedIndex = new DefaultInvertedIndex(new File("src/test/resources/gutenberg/faust1.txt"));

        Position p1 = invertedIndex.nextPhrase("Dunst und Nebel", Integer.MIN_VALUE);

        Assert.assertEquals(51, p1.start);
        Assert.assertEquals(53, p1.end);

        Position p2 = invertedIndex.nextPhrase("Arm und Geleit", Integer.MIN_VALUE);

        Assert.assertEquals(17012, p2.start);
        Assert.assertEquals(17014, p2.end);

    }
}
