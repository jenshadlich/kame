package de.jeha.kame.playground.page_rank.math;

import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author jns
 */

public class MatrixReaderTest {

    private static final double EPSILON = .1e-5;

    @Test
    public void test() throws IOException {
        String matrixString = "0 1 1\n" + "0 0 0\n" + "1 1 0\n";

        double matrix[][] = MatrixReader.readMatrix(IOUtils.toInputStream(matrixString));

        Assert.assertEquals(0.0, matrix[0][0], EPSILON);
        Assert.assertEquals(1.0, matrix[0][1], EPSILON);
        Assert.assertEquals(1.0, matrix[0][2], EPSILON);

        Assert.assertEquals(0.0, matrix[1][0], EPSILON);
        Assert.assertEquals(0.0, matrix[1][1], EPSILON);
        Assert.assertEquals(0.0, matrix[1][2], EPSILON);

        Assert.assertEquals(1.0, matrix[2][0], EPSILON);
        Assert.assertEquals(1.0, matrix[2][1], EPSILON);
        Assert.assertEquals(0.0, matrix[2][2], EPSILON);
    }
}
