package de.jeha.kame.playground.page_rank.math;

/**
 * @author jns
 */
public class Matrix {

    public static double[] multiply(double vector[], double matrix[][]) {
        final int n = vector.length;
        double result[] = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i] += vector[j] * matrix[j][i];
            }
        }

        return result;
    }

    public static boolean equalsWithEps(double[] a, double[] b, double eps) {
        for (int i = 0; i < a.length; i++) {
            if (!equalsWithEps(a[i], b[i], eps))
                return false;
        }
        return true;
    }

    public static double EPSILON = .1e-12;

    public static boolean equalsWithEps(double a, double b) {
        return equalsWithEps(a, b, EPSILON);
    }

    public static boolean equalsWithEps(double a, double b, double epsilon) {
        return Math.abs(a - b) < epsilon;
    }

}
