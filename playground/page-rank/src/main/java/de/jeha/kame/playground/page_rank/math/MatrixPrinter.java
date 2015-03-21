package de.jeha.kame.playground.page_rank.math;

import java.io.PrintStream;

/**
 * @author jns
 */
public class MatrixPrinter {

    private final int decimalPlaces;
    private final String separator;
    private final PrintStream printStream;

    public MatrixPrinter(int decimalPlaces, String separator) {
        this.decimalPlaces = decimalPlaces;
        this.separator = separator;
        this.printStream = System.out;
    }

    public MatrixPrinter(int decimalPlaces) {
        this(decimalPlaces, "\t");
    }

    public void print(double matrix[][]) {
        for (double vector[] : matrix) {
            print(vector);
        }
    }

    public void print(double vector[]) {
        for (int i = 0; i < vector.length; i++) {
            if (i > 0) {
                printStream.print(separator);
            }
            printStream.printf("%1." + decimalPlaces + "f", vector[i]);
        }
        printStream.println();
    }

}
