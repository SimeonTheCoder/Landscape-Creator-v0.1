package generator;

import math.Vec2;

public class Generator {
    public static int[][] generateTextureMap(int w, int h) {
        return new int[h][w];
    }

    public static double[][] generateDepthMap(int w, int h) {
        double gradient_step = 255. / h;

        double[][] depthMap = new double[h][w];

        for(int i = 0; i < h; i ++) {
            for(int j = 0; j < w; j ++) {
                depthMap[i][j] = Math.max(0, Math.min(255, (h - i) * gradient_step));
            }
        }

        return depthMap;
    }

    public static Vec2[][] generateUv(int w, int h, double[][] depthMap) {
        Vec2[][] uvMap = new Vec2[h][w];

        for(int i = 0; i < h; i ++) {
            for(int j = 0; j < w; j ++) {
//                uvMap[i][j] = new Vec2(j / (w + .0), i / (h + .0));
                //uvMap[i][j] = new Vec2(j / (w + .0) * depthMap[i][j] / 50., i / (h + .0) * depthMap[i][j] / 50.);
                uvMap[i][j] = new Vec2(j / (w + .0) * depthMap[i][j] / 500, i / (h + .0));
            }
        }

        return uvMap;
    }
}
