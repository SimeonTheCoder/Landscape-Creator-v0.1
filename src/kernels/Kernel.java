package kernels;

public class Kernel {
    public static double[] getValues(double[][] map, int i, int j) {
        double[] values = new double[9];

        values[0] = map[i-1][j-1];
        values[1] = map[i-1][j];
        values[2] = map[i-1][j+1];

        values[3] = map[i][j-1];
        values[4] = map[i][j];
        values[5] = map[i][j+1];

        values[6] = map[i+1][j-1];
        values[7] = map[i+1][j];
        values[8] = map[i+1][j+1];

        return values;
    }
}
