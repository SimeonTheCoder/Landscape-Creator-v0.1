package kernels;

public class BlurPass implements Pass{

    @Override
    public double[][] pass(double[][] depth) {
        double[][] result = new double[500][500];

        for(int i = 1; i < 499; i ++) {
            for(int j = 1; j < 499; j ++) {
                double[] values = Kernel.getValues(depth, i, j);

                double sum = values[0] + values[1] + values[2];

                sum += values[3] + values[4] + values[5];
                sum += values[6] + values[7] + values[8];

                result[i][j] = sum / 9.;
            }
        }

        return result;
    }
}
