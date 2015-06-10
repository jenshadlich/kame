package de.jeha.kame.playground.page.rank.tools;

import de.jeha.kame.playground.page.rank.math.MatrixPrinter;

import java.util.Random;

/**
 * @author jns
 */
public class AdjacencyMatrixGenerator {

    private final int dim;
    private static final Random GENERATOR = new Random();

    public AdjacencyMatrixGenerator(int dim) {
        this.dim = dim;
    }

    public static void main(String... args) {

        final double m[][] = new AdjacencyMatrixGenerator(100).generate();

        new MatrixPrinter(0, " ").print(m);
    }

    public double[][] generate() {
        final double m[][] = new double[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                m[i][j] = GENERATOR.nextInt(2);
            }
        }

        return m;
    }

}
