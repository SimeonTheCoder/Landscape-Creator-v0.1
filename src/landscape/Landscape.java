package landscape;

import generator.Generator;
import loader.Loader;
import math.Vec2;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Landscape {
    public static final int SIZE_WIDTH = 500;
    public static final int SIZE_HEIGHT = 500;

    public int[][] textureMap;
    public double[][] depthMap;
    public Vec2[][] uv;

    public String[] textures;
    public BufferedImage[] textureImages;

    public Landscape(String[] textures) {
        this.textureImages = new BufferedImage[textures.length];

        this.textureMap = new int[SIZE_HEIGHT][SIZE_WIDTH];

        this.depthMap = new double[SIZE_HEIGHT][SIZE_WIDTH];
        this.uv = new Vec2[SIZE_HEIGHT][SIZE_WIDTH];

        this.textures = textures;

        this.textureMap = Generator.generateTextureMap(SIZE_WIDTH, SIZE_HEIGHT);
        this.depthMap = Generator.generateDepthMap(SIZE_WIDTH, SIZE_HEIGHT);
        this.uv = Generator.generateUv(SIZE_WIDTH, SIZE_HEIGHT, this.depthMap);

        for(int i = 0; i < textures.length; i ++) {
            try {
                textureImages[i] = Loader.load(textures[i]);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
