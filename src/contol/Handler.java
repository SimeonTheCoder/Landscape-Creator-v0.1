package contol;

import window.Window;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Handler {
    public static void handle(int key, Window window, Frame frame) {
        switch (key) {
            case 1: {
                int mouseX = MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x;
                int mouseY = MouseInfo.getPointerInfo().getLocation().y - frame.getLocation().y;

                for (int i = 0; i < window.BRUSH_SIZE; i++) {
                    for (int j = 0; j < window.BRUSH_SIZE; j++) {
                        if(mouseY + i < 500 & mouseX + j < 500 && mouseY + i >= 0 && mouseX + j >= 0) {
                            if (window.VIEW_MODE == 1 || window.VIEW_MODE == 2) {
                                window.landscape.depthMap[mouseY + i][mouseX + j] = window.CURSOR_DEPTH;
                            }

                            window.landscape.textureMap[mouseY + i][mouseX + j] = window.CURR_SUBSTANCE;
                        }
                    }
                }

                break;
            }

            case 2: {
                window.SAMPLE_DENSITY++;

                break;
            }

            case 3: {
                window.SAMPLE_DENSITY--;

                break;
            }

            case 4: {
                int mouseX = MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x;
                int mouseY = MouseInfo.getPointerInfo().getLocation().y - frame.getLocation().y;

                if(mouseX >= 0 && mouseX < 500 && mouseY >= 0 && mouseY <= 500) {
                    window.CURSOR_DEPTH = window.landscape.depthMap[mouseY][mouseX];
                }

                break;
            }

            case 5:
                window.VIEW_MODE = 1;

                break;

            case 6:
                window.VIEW_MODE = 2;

                break;

            case 7:
                window.VIEW_MODE = 3;

                break;

            case 8:
                window.CURR_SUBSTANCE ++;

                break;

            case 9:
                window.CURR_SUBSTANCE --;

                break;

            case 10: {
                File outputfile = new File("data/out/image.png");

                try {
                    ImageIO.write(window.lastFrame, "png", outputfile);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                File segmMap = new File("data/out/segm.png");
                File depthMap = new File("data/out/depth.png");

                BufferedImage segm = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
                BufferedImage depth = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);

                for (int i = 0; i < 500; i++) {
                    for (int j = 0; j < 500; j++) {
                        depth.setRGB(j, i, new Color((int) window.landscape.depthMap[i][j], 0, 0).getRGB());
                        segm.setRGB(j, i, window.colors[window.landscape.textureMap[i][j]].getRGB());
                    }
                }

                try {
                    ImageIO.write(segm, "png", segmMap);
                    ImageIO.write(depth, "png", depthMap);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                break;
            }

            case 11:
                window.BRUSH_SIZE --;

                break;

            case 12:
                window.BRUSH_SIZE ++;

                break;

            case 13:
                window.SHADOWS = !window.SHADOWS;

                break;

            case 14: {
                BufferedImage segm = null;

                try {
                    segm = ImageIO.read(new File("data/in/segm.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                BufferedImage depth = null;

                try {
                    depth = ImageIO.read(new File("data/in/depth.png"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                for(int i = 0; i < 500; i ++) {
                    for(int j = 0; j < 500; j ++) {
                        for(int k = 0; k < window.in_colors.length; k ++) {
                            if(new Color(segm.getRGB(j, i)).equals(window.in_colors[k])) {
                                window.landscape.textureMap[i][j] = k;

                                break;
                            }
                        }

                        window.landscape.depthMap[i][j] = new Color(depth.getRGB(j, i)).getRed();
                    }
                }

                break;
            }

            case 15: {
                int mouseX = MouseInfo.getPointerInfo().getLocation().x - frame.getLocation().x;
                int mouseY = MouseInfo.getPointerInfo().getLocation().y - frame.getLocation().y;

                for (int i = 0; i < window.BRUSH_SIZE; i++) {
                    for (int j = 0; j < window.BRUSH_SIZE; j++) {
                        if(mouseY + i < 500 & mouseX + j < 500 && mouseY + i >= 0 && mouseX + j >= 0) {
                            window.landscape.textureMap[mouseY + i][mouseX + j] = window.CURR_SUBSTANCE;
                        }
                    }
                }

                break;
            }

            default:

                break;
        }
    }
}
