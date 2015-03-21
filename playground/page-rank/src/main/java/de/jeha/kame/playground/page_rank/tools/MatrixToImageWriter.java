package de.jeha.kame.playground.page_rank.tools;

import de.jeha.kame.playground.page_rank.math.MatrixReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

/**
 * @author jns
 */
public class MatrixToImageWriter {

    private final double[][] matrix;
    private final String filename;

    public MatrixToImageWriter(double[][] matrix, String filename) {
        this.matrix = matrix;
        this.filename = filename;
    }

    public static void main(String... args) throws IOException {

        new MatrixToImageWriter(
                MatrixReader.readMatrixFromFile("page-rank/src/main/resources/adj_matrix_01.txt"),
                "d:\\tmp\\matrix.png"
        ).write();
    }

    public void write() throws IOException {
        int width = matrix.length;
        int height = matrix.length;

        int[] data = new int[width * height];

        // create the binary mapping
        byte BLACK = (byte) 0, WHITE = (byte) 1;
        byte[] map = {BLACK, WHITE};
        IndexColorModel icm = new IndexColorModel(1, map.length, map, map, map);

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
        WritableRaster raster = image.getRaster();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                raster.setSample(i, j, 0, matrix[i][j]);
            }
        }

        ImageIO.write(image, "png", new File(filename));
    }

}
