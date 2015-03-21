package de.jeha.kame.playground.page_rank;

import de.jeha.kame.playground.page_rank.math.Matrix;
import de.jeha.kame.playground.page_rank.math.MatrixPrinter;
import de.jeha.kame.playground.page_rank.math.MatrixReader;
import org.apache.commons.math3.util.Precision;

import java.io.IOException;

/**
 * @author jns
 */
public class PageRank {

    private static final double DEFAULT_EPS = .1e-15;

    private final double[][] adjacencyMatrix;
    private final double alpha;
    private final int maxIterations;
    private final double eps;

    public PageRank(double[][] adjacencyMatrix, double alpha, int maxIterations) {
        this(adjacencyMatrix, alpha, maxIterations, DEFAULT_EPS);
    }

    public PageRank(double[][] adjacencyMatrix, double alpha, int maxIterations, double eps) {
        this.adjacencyMatrix = adjacencyMatrix;
        this.alpha = alpha;
        this.maxIterations = maxIterations;
        this.eps = eps;
    }

    public static void main(String... args) throws IOException {
        final MatrixPrinter printer = new MatrixPrinter(15);

        //final double adjacencyMatrix[][] = new AdjacencyMatrixGenerator(1000).generate();
        final double adjacencyMatrix[][] = MatrixReader.readMatrixFromFile("page-rank/src/main/resources/adj_matrix_01.txt");

        double pageRank[] = new PageRank(adjacencyMatrix, 0.85, 1000).compute();
        printer.print(pageRank);
    }

    public double[] compute() {

        final double[][] a = adjacencyMatrix;
        final int dim = a.length;

        // f vector
        double f[] = new double[dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                f[i] += a[i][j];
            }
        }

        // hyperlink matrix
        double h[][] = new double[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                h[i][j] = a[i][j];
                if (!Precision.equals(f[i], 0.0)) {
                    h[i][j] /= f[i];
                }
            }
        }

        // "normalize"
        double piT[] = new double[dim];
        for (int i = 0; i < dim; i++) {
            piT[i] = 1.0 / dim;
        }

        // iterate
        double piT_1[] = piT;
        for (int i = 0; i < 10; i++) {
            //piT_1 = new BlockRealMatrix(h).preMultiply(piT_1);
            piT_1 = Matrix.multiply(piT_1, h);
        }

        // s matrix
        double s[][] = new double[dim][dim];

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                s[i][j] = h[i][j];
                if (Precision.equals(f[i], 0.0)) {
                    s[i][j] = piT[i];
                }
            }
        }

        // Google matrix
        double g[][] = new double[dim][dim];
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                g[i][j] = s[i][j] * alpha + (1.0 - alpha) * piT[i];
            }
        }

        // iterate
        piT_1 = piT;
        for (int i = 0; i < maxIterations; i++) {
            System.out.println("i = " + i);
            piT_1 = Matrix.multiply(piT, g);

            if (Matrix.equalsWithEps(piT, piT_1, eps)) {
                break;
            }

            piT = piT_1;
        }

        return piT_1;
    }

}
