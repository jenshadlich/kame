package de.jeha.kame.playground.page_rank.math;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author jns
 */
public class MatrixReader {

    public static double[][] readMatrixFromFile(String filename) throws IOException {
        return readMatrix(new FileInputStream(new File(filename)));
    }

    public static double[][] readMatrix(InputStream inputStream) throws IOException {
        double matrix[][] = null;
        int row = 0;
        for (String line : IOUtils.readLines(inputStream)) {
            String cols[] = line.split(" ");
            if (matrix == null) {
                matrix = new double[cols.length][cols.length];
            }
            for (int col = 0; col < cols.length; col++) {
                matrix[row][col] = Double.parseDouble(cols[col]);
            }
            row++;
        }
        return matrix;
    }

}
