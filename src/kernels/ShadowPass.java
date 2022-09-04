package kernels;

public class ShadowPass implements Pass{
    private static final double THRESHOLD = 1.5;

    @Override
    public double[][] pass(double[][] depth) {
        double[][] result = new double[500][500];

        for(int i = 1; i < 499; i ++) {
            for(int j = 1; j < 499; j ++) {
                double[] values = Kernel.getValues(depth, i, j);

                if(Math.abs(values[5] - values[3]) > THRESHOLD ||
                        Math.abs(values[1] - values[7]) > THRESHOLD ||
                        Math.abs(values[2] - values[6]) > THRESHOLD ||
                        Math.abs(values[0] - values[8]) > THRESHOLD) {
                    result[i][j] = 255;
                }
            }
        }

        return result;
    }
}
