package de.jeha.kame.playground.page.rank;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author jns
 */

public class PageRankTest {

    @Test
    public void test() throws IOException {

        double adjacencyMatrix[][] = {
                {0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 1},
                {0, 0, 0, 1, 0, 1},
                {0, 0, 0, 1, 0, 0}
        };

        double pageRank[] = new PageRank(adjacencyMatrix, 0.9, 100).compute();

        double expected[] = {
                0.037211965078002040,
                0.053957349363102960,
                0.041505653356233047,
                0.375080815109835040,
                0.205998331877427800,
                0.286245885215400400
        };

        for (int i = 0; i < adjacencyMatrix.length; i++) {
            Assert.assertEquals("pageRank[" + i + "]", expected[i], pageRank[i], .1e-12);
        }
    }
}
